package com.example.apibanco.domain.service;

import com.example.apibanco.domain.model.Client;
import com.example.apibanco.domain.model.Manager;
import com.example.apibanco.domain.repository.ClientRepository;
import com.example.apibanco.domain.repository.ManagerRepository;
import com.example.apibanco.domain.validations.ManagerValidations;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.List;


@Service
@AllArgsConstructor
public class ManagerService {

    private ManagerRepository managerRepository;
    private ManagerValidations managerValidations;
    private ClientRepository clientRepository;

    @Transactional
    public Manager saveManager(Manager manager) {
        Map<String, String> invalidFields = new HashMap<>();

        managerValidations.checkInvalidFields(invalidFields, manager.getCpf(), manager.getEmail());
        manager.setActive(true);
        return managerRepository.save(manager);
    }

    public Manager inactivateManager(Long managerId, Long newManagerId) {
        Map<String, String> invalidFields = new HashMap<>();
        managerValidations.checkInactiveManager(invalidFields, managerId, newManagerId);

        managerRepository.getReferenceById(managerId).setActive(false);

        Manager novoManager = managerRepository.getReferenceById(newManagerId);

        List<Client> linkedClients = clientRepository.findClientsByManagerId(managerId);

        if (!linkedClients.isEmpty()) {
            linkedClients.forEach(client -> client.setManager(novoManager));
            clientRepository.saveAll(linkedClients);
        }
        return managerRepository.save(managerRepository.getReferenceById(managerId));
    }

    public Manager activateManager(Long managerId) {
        Map<String, String> invalidFields = new HashMap<>();
        managerValidations.checkActiveManager(invalidFields, managerId);
        managerRepository.getReferenceById(managerId).setActive(true);

        return managerRepository.save(managerRepository.getReferenceById(managerId));
    }


}
