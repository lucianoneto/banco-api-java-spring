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

    public Manager inactivateManager(Long gerente_id, Long novoGerente_id){
        HashMap<String, String> camposInvalidos = new HashMap<>();
        managerValidations.checkInactiveManager(camposInvalidos, gerente_id, novoGerente_id);

        managerRepository.getReferenceById(gerente_id).setActive(false);

        Manager novoManager = managerRepository.getReferenceById(novoGerente_id);

        List<Client> clientesVinculados = clientRepository.findClientsByManager_Id(gerente_id);

        if (!clientesVinculados.isEmpty()) {
            clientesVinculados.forEach(cliente -> cliente.setManager(novoManager));
            clientRepository.saveAll(clientesVinculados);
        }
        return managerRepository.save(managerRepository.getReferenceById(gerente_id));
    }

    public Manager activateManager(Long gerente_id){
        HashMap<String, String> camposInvalidos = new HashMap<>();
        managerValidations.checkActiveManager(camposInvalidos, gerente_id);
        managerRepository.getReferenceById(gerente_id).setActive(true);

        return managerRepository.save(managerRepository.getReferenceById(gerente_id));
    }


}
