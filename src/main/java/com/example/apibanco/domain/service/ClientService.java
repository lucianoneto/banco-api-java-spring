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

    public Client saveClient(ClientInput clientInput, Long manager_id) {
        HashMap<String, String> invalidFields = new HashMap<>();

        Client client = modelMapper.map(clientInput, Client.class);
        Manager manager = managerRepository.getReferenceById(manager_id);
        clientValidations.checkInvalidFields(invalidFields, manager_id, client.getCpf(), client.getEmail());

        client.setManager(manager);
        client.setActive(true);

        accountService.saveAccount(client);

        return clientRepository.save(client);
    }

    public Client updateClient(ClientPatchInput clientPatchInput, Long manager_id, Long client_id) {
        HashMap<String, String> invalidFields = new HashMap<>();

        accountValidations.checkInactiveClientAccount(invalidFields, client_id);
        managerValidations.checkManagerClientRelationship(invalidFields, manager_id, client_id);
        clientValidations.checkInvalidFields(invalidFields, manager_id, clientPatchInput.getEmail());

        Client client = clientRepository.getReferenceById(client_id);

        client = clientPatchInputAssembler.merge(clientPatchInput, client);

        return clientRepository.save(client);
    }
    public Client inactivateClient(Long manager_id, Long client_id){

        HashMap<String, String> invalidFields = new HashMap<>();

        accountValidations.checkInactiveClientAccount(invalidFields, client_id);
        managerValidations.checkManagerClientRelationship(invalidFields, manager_id, client_id);

        Client client = clientRepository.getReferenceById(client_id);

        client.setActive(false);

        return clientRepository.save(client);
    }

    public Client activateClient(Long manager_id, Long client_id){
        HashMap<String, String> invalidFields = new HashMap<>();

        accountValidations.checkActiveClientAccount(invalidFields, client_id);
        managerValidations.checkManagerClientRelationship(invalidFields, manager_id, client_id);

        Client client = clientRepository.getReferenceById(client_id);

        client.setActive(true);

        return clientRepository.save(client);
    }



}
