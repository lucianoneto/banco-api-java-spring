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
public class TransactionsValidations {

    private AccountRepository accountRepository;
    private AccountValidations accountValidations;
    private final MessageSource messageSource;

    public void checkTransaction(HashMap<String, String> invalidFields, Float value, Long account_id) {
        accountValidations.checkInactiveClientAccount(invalidFields,account_id);
        if (value < 1)
            invalidFields.put("value", messageSource.getMessage("value.invalid", null, Locale.US));
        if (!invalidFields.isEmpty())
            throw new BusinessException(messageSource.getMessage("general.error", null, Locale.US), invalidFields);
    }

    public void checkWithdraw(HashMap<String, String> invalidFields, Float value, Long account_id) {
        checkTransaction(invalidFields, value, account_id);
        if (accountRepository.getReferenceById(account_id).getBalance() < value)
            invalidFields.put("valueWithdraw", messageSource.getMessage("value.enough", null, Locale.US));
        if (!invalidFields.isEmpty())
            throw new BusinessException(messageSource.getMessage("general.error", null, Locale.US), invalidFields);
    }

    public void checkTransfer(HashMap<String, String> invalidFields, Float value, Long originAccount_id, Long destinyAccount_id) {
        checkTransaction(invalidFields, value, originAccount_id);
        accountValidations.checkInactiveClientAccount(invalidFields, destinyAccount_id);
        if (accountRepository.getReferenceById(originAccount_id).getBalance() < value)
            invalidFields.put("valueTransfer", messageSource.getMessage("value.enough", null, Locale.US));
        if (!invalidFields.isEmpty())
            throw new BusinessException(messageSource.getMessage("general.error", null, Locale.US), invalidFields);
    }

}
