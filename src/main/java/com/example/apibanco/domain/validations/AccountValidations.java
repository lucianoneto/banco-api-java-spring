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

    public void checkInactiveClientAccount(HashMap<String, String> invalidFields, Long account_id){
        checkExistsClientAccount(invalidFields, account_id);
        if(!accountRepository.getReferenceById(account_id).getClient().getActive())
            invalidFields.put("/idConta", messageSource.getMessage("account.inactive", null, Locale.US));
        if (!invalidFields.isEmpty())
            throw new BusinessException(messageSource.getMessage("general.error", null, Locale.US), invalidFields);
    }

    public void checkActiveClientAccount(HashMap<String, String> invalidFields, Long account_id){
        checkExistsClientAccount(invalidFields, account_id);
        if(accountRepository.getReferenceById(account_id).getClient().getActive())
            invalidFields.put("/idConta", messageSource.getMessage("account.active", null, Locale.US));
        if (!invalidFields.isEmpty())
            throw new BusinessException(messageSource.getMessage("general.error", null, Locale.US), invalidFields);
    }

    private void checkExistsClientAccount(HashMap<String, String> invalidFields, Long account_id){
        if (accountRepository.findById(account_id).isEmpty())
            invalidFields.put("/idAccount", messageSource.getMessage("account.not.exist", null, Locale.US));
        if (!invalidFields.isEmpty())
            throw new BusinessException(messageSource.getMessage("general.error", null, Locale.US), invalidFields);
    }
}
