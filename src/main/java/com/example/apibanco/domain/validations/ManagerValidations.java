package com.example.apibanco.domain.validations;

import com.example.apibanco.api.exception.BusinessException;
import com.example.apibanco.domain.repository.ClientRepository;
import com.example.apibanco.domain.repository.ManagerRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Locale;
import java.util.Objects;

@Component
@AllArgsConstructor
public class ManagerValidations {

    private ManagerRepository managerRepository;
    private ClientRepository clientRepository;
    private final MessageSource messageSource;

    public void checkInactiveManager(HashMap<String, String> invalidFields, Long manager_id, Long newManager_id){
        checkExistsManager(invalidFields, manager_id);
        checkExistsNewManager(invalidFields, newManager_id);

        if(!managerRepository.getReferenceById(manager_id).getActive())
            invalidFields.put("/idManager", messageSource.getMessage("manager.inactive", null, Locale.US));
        if(!managerRepository.getReferenceById(newManager_id).getActive())
            invalidFields.put("/idNewManager", messageSource.getMessage("manager.inactive", null, Locale.US));
        if(manager_id.equals(newManager_id))
            invalidFields.put("/idManager", messageSource.getMessage("managers.equals", null, Locale.US));
        if (!invalidFields.isEmpty())
            throw new BusinessException(messageSource.getMessage("general.error", null, Locale.US), invalidFields);
    }

    public void checkActiveManager(HashMap<String, String> invalidFields, Long manager_id){
        checkExistsManager(invalidFields, manager_id);

        if(managerRepository.getReferenceById(manager_id).getActive())
            invalidFields.put("/idManager", messageSource.getMessage("manager.active", null, Locale.US));
        if (!invalidFields.isEmpty())
            throw new BusinessException(messageSource.getMessage("general.error", null, Locale.US), invalidFields);
    }

    public void checkManagerClientRelationship(HashMap<String, String> invalidFields, Long manager_id, Long client_id){
        checkExistsManager(invalidFields, manager_id);

        if(!managerRepository.getReferenceById(manager_id).getActive())
            invalidFields.put("/idManager", messageSource.getMessage("manager.inactive", null, Locale.US));
        if(!Objects.equals(clientRepository.getReferenceById(client_id).getManager().getId(), manager_id))
            invalidFields.put("/idClient", messageSource.getMessage("manager.client.relationship", null, Locale.US));
        if (!invalidFields.isEmpty())
            throw new BusinessException(messageSource.getMessage("general.error", null, Locale.US), invalidFields);
    }

    public void checkExistsManager(HashMap<String, String> invalidFields, Long manager_id){
        if (managerRepository.findById(manager_id).isEmpty())
            invalidFields.put("/idManager", messageSource.getMessage("manager.not.exist", null, Locale.US));
        if (!invalidFields.isEmpty())
            throw new BusinessException(messageSource.getMessage("general.error", null, Locale.US), invalidFields);
    }

    private void checkExistsNewManager(HashMap<String, String> invalidFields, Long newManager_id){
        if (managerRepository.findById(newManager_id).isEmpty())
            invalidFields.put("/idNewManager", messageSource.getMessage("manager.not.exist", null, Locale.US));
        if (!invalidFields.isEmpty())
            throw new BusinessException(messageSource.getMessage("general.error", null, Locale.US), invalidFields);
    }

    public void checkInvalidFields(HashMap<String, String> invalidFields, String cpf, String email) {
        if (managerRepository.existsByCpf(cpf))
            invalidFields.put("cpf", messageSource.getMessage("cpf.registered", null, Locale.US));
        if (managerRepository.existsByEmail(email))
            invalidFields.put("e-mail", messageSource.getMessage("email.registered", null, Locale.US));
        if (!invalidFields.isEmpty())
            throw new BusinessException(messageSource.getMessage("general.error", null, Locale.US), invalidFields);
    }
}
