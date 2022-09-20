package com.example.apibanco.domain.validations;

import com.example.apibanco.api.exception.BusinessException;
import com.example.apibanco.domain.repository.ClientRepository;
import com.example.apibanco.domain.repository.ManagerRepository;
import com.example.apibanco.domain.utils.MessagesConstants;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.Map;
import java.util.Objects;

@Component
@AllArgsConstructor
public class ManagerValidations {

    private ManagerRepository managerRepository;
    private ClientRepository clientRepository;
    private MessageSource messageSource;

    public void checkInactiveManager(Map<String, String> invalidFields, Long managerId, Long newManagerId) {
        checkExistsManager(invalidFields, managerId);
        checkExistsNewManager(invalidFields, newManagerId);

        if (Boolean.FALSE.equals(managerRepository.getReferenceById(managerId).getActive()))
            invalidFields.put(MessagesConstants.ID_MANAGER, messageSource.getMessage(MessagesConstants.MANAGER_INACTIVE, null, Locale.US));
        if (Boolean.FALSE.equals(managerRepository.getReferenceById(newManagerId).getActive()))
            invalidFields.put(MessagesConstants.ID_NEW_MANAGER, messageSource.getMessage(MessagesConstants.MANAGER_INACTIVE, null, Locale.US));
        if (managerId.equals(newManagerId))
            invalidFields.put(MessagesConstants.ID_MANAGER, messageSource.getMessage(MessagesConstants.MANAGER_EQUALS, null, Locale.US));
        if (!invalidFields.isEmpty())
            throw new BusinessException(messageSource.getMessage(MessagesConstants.GENERAL_ERROR, null, Locale.US), invalidFields);
    }

    public void checkActiveManager(Map<String, String> invalidFields, Long managerId) {
        checkExistsManager(invalidFields, managerId);

        if (Boolean.TRUE.equals(managerRepository.getReferenceById(managerId).getActive()))
            invalidFields.put(MessagesConstants.ID_MANAGER, messageSource.getMessage(MessagesConstants.MANAGER_ACTIVE, null, Locale.US));
        if (!invalidFields.isEmpty())
            throw new BusinessException(messageSource.getMessage(MessagesConstants.GENERAL_ERROR, null, Locale.US), invalidFields);
    }

    public void checkManagerClientRelationship(Map<String, String> invalidFields, Long managerId, Long clientId) {
        checkExistsManager(invalidFields, managerId);

        if (Boolean.FALSE.equals(managerRepository.getReferenceById(managerId).getActive()))
            invalidFields.put(MessagesConstants.ID_MANAGER, messageSource.getMessage(MessagesConstants.MANAGER_INACTIVE, null, Locale.US));
        if (!Objects.equals(clientRepository.getReferenceById(clientId).getManager().getId(), managerId))
            invalidFields.put(MessagesConstants.ID_CLIENT, messageSource.getMessage(MessagesConstants.MANAGER_CLIENT_RELATIONSHIP, null, Locale.US));
        if (!invalidFields.isEmpty())
            throw new BusinessException(messageSource.getMessage(MessagesConstants.GENERAL_ERROR, null, Locale.US), invalidFields);
    }

    public void checkExistsManager(Map<String, String> invalidFields, Long managerId) {
        if (managerRepository.findById(managerId).isEmpty())
            invalidFields.put(MessagesConstants.ID_MANAGER, messageSource.getMessage(MessagesConstants.MANAGER_NOT_EXIST, null, Locale.US));
        if (!invalidFields.isEmpty())
            throw new BusinessException(messageSource.getMessage(MessagesConstants.GENERAL_ERROR, null, Locale.US), invalidFields);
    }

    private void checkExistsNewManager(Map<String, String> invalidFields, Long newManagerId) {
        if (managerRepository.findById(newManagerId).isEmpty())
            invalidFields.put(MessagesConstants.ID_NEW_MANAGER, messageSource.getMessage(MessagesConstants.MANAGER_NOT_EXIST, null, Locale.US));
        if (!invalidFields.isEmpty())
            throw new BusinessException(messageSource.getMessage(MessagesConstants.GENERAL_ERROR, null, Locale.US), invalidFields);
    }

    public void checkInvalidFields(Map<String, String> invalidFields, String cpf, String email) {
        if (managerRepository.existsByCpf(cpf))
            invalidFields.put(MessagesConstants.CPF, messageSource.getMessage(MessagesConstants.CPF_REGISTERED, null, Locale.US));
        if (managerRepository.existsByEmail(email))
            invalidFields.put(MessagesConstants.EMAIL, messageSource.getMessage(MessagesConstants.EMAIL_REGISTERED, null, Locale.US));
        if (!invalidFields.isEmpty())
            throw new BusinessException(messageSource.getMessage(MessagesConstants.GENERAL_ERROR, null, Locale.US), invalidFields);
    }
}
