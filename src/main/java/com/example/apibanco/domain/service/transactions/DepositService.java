package com.example.apibanco.domain.service.transactions;

import com.example.apibanco.domain.model.Account;
import com.example.apibanco.domain.model.transactions.Deposit;
import com.example.apibanco.domain.repository.AccountRepository;
import com.example.apibanco.domain.repository.transactions.DepositRepository;
import com.example.apibanco.domain.validations.TransactionsValidations;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;


@Service
@AllArgsConstructor
public class DepositService {

    private AccountRepository accountRepository;
    private DepositRepository depositRepository;
    private TransactionsValidations transactionsValidations;

    @Transactional
    public Deposit saveDeposit(Long accountId, float depositValue) {
        Map<String, String> invalidFields = new HashMap<>();

        transactionsValidations.checkTransaction(invalidFields, depositValue, accountId);

        Account account = accountRepository.getReferenceById(accountId);
        Deposit deposit = new Deposit()
                .setValue(depositValue)
                .setDate(LocalDate.now())
                .setTime(LocalTime.now())
                .setAccount(account);

        account.setBalance(deposit.getValue() + account.getBalance());

        depositRepository.save(deposit);
        accountRepository.save(account);

        return deposit;

    }
}
