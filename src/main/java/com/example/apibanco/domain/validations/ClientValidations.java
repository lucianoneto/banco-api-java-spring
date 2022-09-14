package com.example.apibanco.domain.validations;

import com.example.apibanco.api.exception.BusinessException;
import com.example.apibanco.domain.repository.ClientRepository;
import com.example.apibanco.domain.utils.MessagesConstants;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Locale;

@Component
@AllArgsConstructor
public class ClientValidations {
    ClientRepository clientRepository;
    ManagerValidations managerValidations;
    private final MessageSource messageSource;

    public void checkInvalidFields(HashMap<String, String> invalidFields, Long manager_id, String cpf, String email) {
        managerValidations.checkExistsManager(invalidFields, manager_id);

        if (clientRepository.existsByCpf(cpf))
            invalidFields.put(MessagesConstants.CPF, messageSource.getMessage(MessagesConstants.CPF_REGISTERED, null, Locale.US));
        if (clientRepository.existsByEmail(email))
            invalidFields.put(MessagesConstants.EMAIL, messageSource.getMessage(MessagesConstants.EMAIL_REGISTERED, null, Locale.US));
        if (!invalidFields.isEmpty())
            throw new BusinessException(MessagesConstants.GENERAL_ERROR, invalidFields);
    }

    public void checkInvalidFields(HashMap<String, String> invalidFields, Long manager_id, String email) {
        managerValidations.checkExistsManager(invalidFields, manager_id);

        if (clientRepository.existsByEmail(email))
            invalidFields.put(MessagesConstants.EMAIL, messageSource.getMessage(MessagesConstants.EMAIL_REGISTERED, null, Locale.US));
        if (!invalidFields.isEmpty())
            throw new BusinessException(messageSource.getMessage(MessagesConstants.GENERAL_ERROR, null, Locale.US), invalidFields);
    }
}
