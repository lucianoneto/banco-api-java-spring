package com.example.apibanco.domain.validations;

import com.example.apibanco.api.exception.BusinessException;
import com.example.apibanco.domain.repository.AccountRepository;
import com.example.apibanco.domain.utils.MessagesConstants;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.Map;

@Component
@AllArgsConstructor
public class TransactionsValidations {

    private AccountRepository accountRepository;
    private AccountValidations accountValidations;
    private MessageSource messageSource;

    public void checkTransaction(Map<String, String> invalidFields, float value, Long accountId) {
        accountValidations.checkInactiveClientAccount(invalidFields, accountId);
        if (value < 1)
            invalidFields.put(MessagesConstants.VALUE, messageSource.getMessage(MessagesConstants.VALUE_INVALID, null, Locale.US));
        if (!invalidFields.isEmpty())
            throw new BusinessException(messageSource.getMessage(MessagesConstants.GENERAL_ERROR, null, Locale.US), invalidFields);
    }

    public void checkWithdraw(Map<String, String> invalidFields, float value, Long accountId) {
        checkTransaction(invalidFields, value, accountId);
        if (accountRepository.getReferenceById(accountId).getBalance() < value)
            invalidFields.put(MessagesConstants.VALUE_WITHDRAW, messageSource.getMessage(MessagesConstants.VALUE_ENOUGH, null, Locale.US));
        if (!invalidFields.isEmpty())
            throw new BusinessException(messageSource.getMessage(MessagesConstants.GENERAL_ERROR, null, Locale.US), invalidFields);
    }

    public void checkTransfer(Map<String, String> invalidFields, float value, Long originAccountId, Long destinyAccountId) {
        checkTransaction(invalidFields, value, originAccountId);
        accountValidations.checkInactiveClientAccount(invalidFields, destinyAccountId);
        if (accountRepository.getReferenceById(originAccountId).getBalance() < value)
            invalidFields.put(MessagesConstants.VALUE_TRANSFER, messageSource.getMessage(MessagesConstants.VALUE_ENOUGH, null, Locale.US));
        if (!invalidFields.isEmpty())
            throw new BusinessException(messageSource.getMessage(MessagesConstants.GENERAL_ERROR, null, Locale.US), invalidFields);
    }

}
