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
import java.util.List;


@Service
@AllArgsConstructor
public class ManagerService {

    private ManagerRepository managerRepository;
    private ManagerValidations managerValidations;
    private ClientRepository clientRepository;

    @Transactional
    public Manager saveManager(Manager manager) {
        HashMap<String, String> camposInvalidos = new HashMap<>();

        managerValidations.checkInvalidFields(camposInvalidos, manager.getCpf(), manager.getEmail());
        manager.setActive(true);
        return managerRepository.save(manager);
    }

    public Manager inactivateManager(Long manager_id, Long newManager_id){
        HashMap<String, String> camposInvalidos = new HashMap<>();
        managerValidations.checkInactiveManager(camposInvalidos, manager_id, newManager_id);

        managerRepository.getReferenceById(manager_id).setActive(false);

        Manager novoManager = managerRepository.getReferenceById(newManager_id);

        List<Client> linkedClients = clientRepository.findClientsByManager_Id(manager_id);

        if (!linkedClients.isEmpty()) {
            linkedClients.forEach(client -> client.setManager(novoManager));
            clientRepository.saveAll(linkedClients);
        }
        return managerRepository.save(managerRepository.getReferenceById(manager_id));
    }

    public Manager activateManager(Long manager_id){
        HashMap<String, String> camposInvalidos = new HashMap<>();
        managerValidations.checkActiveManager(camposInvalidos, manager_id);
        managerRepository.getReferenceById(manager_id).setActive(true);

        return managerRepository.save(managerRepository.getReferenceById(manager_id));
    }


}
