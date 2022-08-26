package com.example.apibanco.domain.validations;

import com.example.apibanco.api.exception.BusinessException;
import com.example.apibanco.domain.repository.ClientRepository;
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

    public void checkInvalidFields(HashMap<String, String> camposInvalidos, Long gerente_id, String cpf, String email) {
        managerValidations.checkExistsManager(camposInvalidos, gerente_id);

        if (clientRepository.existsByCpf(cpf))
            camposInvalidos.put("cpf", messageSource.getMessage("cpf.registered", null, Locale.US));
        if (clientRepository.existsByEmail(email))
            camposInvalidos.put("e-mail", messageSource.getMessage("email.registered", null, Locale.US));
        if (!camposInvalidos.isEmpty())
            throw new BusinessException("One or more fields are invalid.", camposInvalidos);
    }

    public void checkInvalidFields(HashMap<String, String> camposInvalidos, Long gerente_id, String email) {
        managerValidations.checkExistsManager(camposInvalidos, gerente_id);

        if (clientRepository.existsByEmail(email))
            camposInvalidos.put("e-mail", messageSource.getMessage("email.registered", null, Locale.US));
        if (!camposInvalidos.isEmpty())
            throw new BusinessException(messageSource.getMessage("general.error", null, Locale.US), camposInvalidos);
    }
}