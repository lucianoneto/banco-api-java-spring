package com.example.apibanco.domain.service;

import com.example.apibanco.api.model.ClientInput;
import com.example.apibanco.api.model.ClientPatchInput;
import com.example.apibanco.domain.assembler.ClientPatchInputAssembler;
import com.example.apibanco.domain.model.Client;
import com.example.apibanco.domain.model.Manager;
import com.example.apibanco.domain.repository.ClientRepository;
import com.example.apibanco.domain.repository.ManagerRepository;
import com.example.apibanco.domain.validations.ClientValidations;
import com.example.apibanco.domain.validations.AccountValidations;
import com.example.apibanco.domain.validations.ManagerValidations;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.HashMap;


@Service
@AllArgsConstructor
public class ClientService {

    private ClientRepository clientRepository;
    private AccountService accountService;
    private ManagerRepository managerRepository;
    private ModelMapper modelMapper;
    private ManagerValidations managerValidations;
    private AccountValidations accountValidations;
    private ClientPatchInputAssembler clientPatchInputAssembler;
    private ClientValidations clientValidations;

    public Client saveClient(ClientInput clientInput, Long gerente_id) {
        HashMap<String, String> camposInvalidos = new HashMap<>();

        Client client = modelMapper.map(clientInput, Client.class);
        Manager manager = managerRepository.getReferenceById(gerente_id);
        clientValidations.checkInvalidFields(camposInvalidos, gerente_id, client.getCpf(), client.getEmail());

        client.setManager(manager);
        client.setActive(true);

        accountService.saveAccount(client);

        return clientRepository.save(client);
    }

    public Client updateClient(ClientPatchInput clientPatchInput, Long gerente_id, Long cliente_id) {
        HashMap<String, String> camposInvalidos = new HashMap<>();

        accountValidations.checkInactiveClientAccount(camposInvalidos, cliente_id);
        managerValidations.checkManagerClientRelationship(camposInvalidos, gerente_id, cliente_id);
        clientValidations.checkInvalidFields(camposInvalidos, gerente_id, clientPatchInput.getEmail());

        Client client = clientRepository.getReferenceById(cliente_id);

        client = clientPatchInputAssembler.merge(clientPatchInput, client);

        return clientRepository.save(client);
    }
    public Client inactivateClient(Long gerente_id, Long cliente_id){

        HashMap<String, String> camposInvalidos = new HashMap<>();

        accountValidations.checkInactiveClientAccount(camposInvalidos, cliente_id);
        managerValidations.checkManagerClientRelationship(camposInvalidos, gerente_id, cliente_id);

        Client client = clientRepository.getReferenceById(cliente_id);

        client.setActive(false);

        return clientRepository.save(client);
    }

    public Client activateClient(Long gerente_id, Long cliente_id){
        HashMap<String, String> camposInvalidos = new HashMap<>();

        accountValidations.checkActiveClientAccount(camposInvalidos, cliente_id);
        managerValidations.checkManagerClientRelationship(camposInvalidos, gerente_id, cliente_id);

        Client client = clientRepository.getReferenceById(cliente_id);

        client.setActive(true);

        return clientRepository.save(client);
    }



}
