package com.example.apibanco.domain.service;

import com.example.apibanco.api.model.ClientInput;
import com.example.apibanco.api.model.ClientPatchInput;
import com.example.apibanco.domain.assembler.ClientPatchInputAssembler;
import com.example.apibanco.domain.model.Client;
import com.example.apibanco.domain.model.Manager;
import com.example.apibanco.domain.repository.ClientRepository;
import com.example.apibanco.domain.repository.ManagerRepository;
import com.example.apibanco.domain.validations.AccountValidations;
import com.example.apibanco.domain.validations.ClientValidations;
import com.example.apibanco.domain.validations.ManagerValidations;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


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

    public Client saveClient(ClientInput clientInput, Long managerId) {
        Map<String, String> invalidFields = new HashMap<>();

        Client client = modelMapper.map(clientInput, Client.class);
        Manager manager = managerRepository.getReferenceById(managerId);
        clientValidations.checkInvalidFields(invalidFields, managerId, client.getCpf(), client.getEmail());

        client.setManager(manager);
        client.setActive(true);

        accountService.saveAccount(client);

        return clientRepository.save(client);
    }

    public Client updateClient(ClientPatchInput clientPatchInput, Long managerId, Long clientId) {
        Map<String, String> invalidFields = new HashMap<>();

        accountValidations.checkInactiveClientAccount(invalidFields, clientId);
        managerValidations.checkManagerClientRelationship(invalidFields, managerId, clientId);
        clientValidations.checkInvalidFields(invalidFields, managerId, clientPatchInput.getEmail());

        Client client = clientRepository.getReferenceById(clientId);

        client = clientPatchInputAssembler.merge(clientPatchInput, client);

        return clientRepository.save(client);
    }

    public Client inactivateClient(Long managerId, Long clientId) {

        Map<String, String> invalidFields = new HashMap<>();

        accountValidations.checkInactiveClientAccount(invalidFields, clientId);
        managerValidations.checkManagerClientRelationship(invalidFields, managerId, clientId);

        Client client = clientRepository.getReferenceById(clientId);

        client.setActive(false);

        return clientRepository.save(client);
    }

    public Client activateClient(Long managerId, Long clientId) {
        Map<String, String> invalidFields = new HashMap<>();

        accountValidations.checkActiveClientAccount(invalidFields, clientId);
        managerValidations.checkManagerClientRelationship(invalidFields, managerId, clientId);

        Client client = clientRepository.getReferenceById(clientId);

        client.setActive(true);

        return clientRepository.save(client);
    }


}
