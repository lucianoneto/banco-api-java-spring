package com.example.apibanco.domain.validations;

import com.example.apibanco.api.exception.BusinessException;
import com.example.apibanco.domain.repository.ClientRepository;
import com.example.apibanco.domain.utils.MessagesConstants;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.Map;

@Component
@AllArgsConstructor
public class ClientValidations {
    ClientRepository clientRepository;
    ManagerValidations managerValidations;
    private MessageSource messageSource;

    public void checkInvalidFields(Map<String, String> invalidFields, Long managerId, String cpf, String email) {
        managerValidations.checkExistsManager(invalidFields, managerId);

        if (clientRepository.existsByCpf(cpf))
            invalidFields.put(MessagesConstants.CPF, messageSource.getMessage(MessagesConstants.CPF_REGISTERED, null, Locale.US));
        if (clientRepository.existsByEmail(email))
            invalidFields.put(MessagesConstants.EMAIL, messageSource.getMessage(MessagesConstants.EMAIL_REGISTERED, null, Locale.US));
        if (!invalidFields.isEmpty())
            throw new BusinessException(MessagesConstants.GENERAL_ERROR, invalidFields);
    }

    public void checkInvalidFields(Map<String, String> invalidFields, Long managerId, String email) {
        managerValidations.checkExistsManager(invalidFields, managerId);

        if (clientRepository.existsByEmail(email))
            invalidFields.put(MessagesConstants.EMAIL, messageSource.getMessage(MessagesConstants.EMAIL_REGISTERED, null, Locale.US));
        if (!invalidFields.isEmpty())
            throw new BusinessException(messageSource.getMessage(MessagesConstants.GENERAL_ERROR, null, Locale.US), invalidFields);
    }
}
