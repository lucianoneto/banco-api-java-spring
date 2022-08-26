package com.example.apibanco.domain.validations;

import com.example.apibanco.api.exception.BusinessException;
import com.example.apibanco.domain.repository.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Locale;

@Component
@AllArgsConstructor
public class AccountValidations {

    private AccountRepository accountRepository;
    private final MessageSource messageSource;

    public void checkInactiveClientAccount(HashMap<String, String> camposInvalidos, Long conta_id){
        checkExistsClientAccount(camposInvalidos, conta_id);
        if(!accountRepository.getReferenceById(conta_id).getClient().getActive())
            camposInvalidos.put("/idConta", messageSource.getMessage("conta.inactive", null, Locale.US));
        if (!camposInvalidos.isEmpty())
            throw new BusinessException(messageSource.getMessage("general.error", null, Locale.US), camposInvalidos);
    }

    public void checkActiveClientAccount(HashMap<String, String> camposInvalidos, Long conta_id){
        checkExistsClientAccount(camposInvalidos, conta_id);
        if(accountRepository.getReferenceById(conta_id).getClient().getActive())
            camposInvalidos.put("/idConta", messageSource.getMessage("conta.active", null, Locale.US));
        if (!camposInvalidos.isEmpty())
            throw new BusinessException(messageSource.getMessage("general.error", null, Locale.US), camposInvalidos);
    }

    private void checkExistsClientAccount(HashMap<String, String> camposInvalidos, Long conta_id){
        if (accountRepository.findById(conta_id).isEmpty())
            camposInvalidos.put("/idConta", messageSource.getMessage("conta.not.exist", null, Locale.US));
        if (!camposInvalidos.isEmpty())
            throw new BusinessException(messageSource.getMessage("general.error", null, Locale.US), camposInvalidos);
    }
}
