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

    public void checkTransaction(HashMap<String, String> camposInvalidos, Float valor, Long conta_id) {
        accountValidations.checkInactiveClientAccount(camposInvalidos,conta_id);
        if (valor < 1)
            camposInvalidos.put("valor", messageSource.getMessage("valor.invalid", null, Locale.US));
        if (!camposInvalidos.isEmpty())
            throw new BusinessException(messageSource.getMessage("general.error", null, Locale.US), camposInvalidos);
    }

    public void checkWithdraw(HashMap<String, String> camposInvalidos, Float valor, Long conta_id) {
        checkTransaction(camposInvalidos, valor, conta_id);
        if (accountRepository.getReferenceById(conta_id).getBalance() < valor)
            camposInvalidos.put("valorSaque", messageSource.getMessage("valor.enough", null, Locale.US));
        if (!camposInvalidos.isEmpty())
            throw new BusinessException(messageSource.getMessage("general.error", null, Locale.US), camposInvalidos);
    }

    public void checkTransfer(HashMap<String, String> camposInvalidos, Float valor, Long contaOrigem_id, Long contaDestino_id) {
        checkTransaction(camposInvalidos, valor, contaOrigem_id);
        accountValidations.checkInactiveClientAccount(camposInvalidos, contaDestino_id);
        if (accountRepository.getReferenceById(contaOrigem_id).getBalance() < valor)
            camposInvalidos.put("valorTransferencia", messageSource.getMessage("valor.enough", null, Locale.US));
        if (!camposInvalidos.isEmpty())
            throw new BusinessException(messageSource.getMessage("general.error", null, Locale.US), camposInvalidos);
    }

}
