package com.example.apibanco.domain.validations;

import com.example.apibanco.api.exception.BusinessException;
import com.example.apibanco.domain.repository.AccountRepository;
import com.example.apibanco.domain.utils.MessagesConstants;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Locale;

@Component
@AllArgsConstructor
public class AccountValidations {

    private AccountRepository accountRepository;
    private MessageSource messageSource;


    public void checkInactiveClientAccount(HashMap<String, String> invalidFields, Long accountId) {
        checkExistsClientAccount(invalidFields, accountId);
        if (Boolean.FALSE.equals(accountRepository.getReferenceById(accountId).getClient().getActive()))
            invalidFields.put(MessagesConstants.ID_ACCOUNT, messageSource.getMessage(MessagesConstants.ACCOUNT_INACTIVE, null, Locale.US));
        if (!invalidFields.isEmpty())
            throw new BusinessException(messageSource.getMessage(MessagesConstants.GENERAL_ERROR, null, Locale.US), invalidFields);
    }

    public void checkActiveClientAccount(HashMap<String, String> invalidFields, Long accountId) {
        checkExistsClientAccount(invalidFields, accountId);
        if (Boolean.TRUE.equals(accountRepository.getReferenceById(accountId).getClient().getActive()))
            invalidFields.put(MessagesConstants.ID_ACCOUNT, messageSource.getMessage(MessagesConstants.ACCOUNT_ACTIVE, null, Locale.US));
        if (!invalidFields.isEmpty())
            throw new BusinessException(messageSource.getMessage(MessagesConstants.GENERAL_ERROR, null, Locale.US), invalidFields);
    }

    private void checkExistsClientAccount(HashMap<String, String> invalidFields, Long accountId) {
        if (accountRepository.findById(accountId).isEmpty())
            invalidFields.put(MessagesConstants.ID_ACCOUNT, messageSource.getMessage(MessagesConstants.ACCOUNT_NOT_EXIST, null, Locale.US));
        if (!invalidFields.isEmpty()) {
            throw new BusinessException(messageSource.getMessage(MessagesConstants.GENERAL_ERROR, null, Locale.US), invalidFields);
        }
    }
}
