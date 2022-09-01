package com.example.apibanco.domain.service.transactions;

import com.example.apibanco.domain.model.Account;
import com.example.apibanco.domain.model.transactions.Deposit;
import com.example.apibanco.domain.repository.AccountRepository;
import com.example.apibanco.domain.repository.transactions.DepositRepository;
import com.example.apibanco.domain.utils.Utils;
import com.example.apibanco.domain.validations.TransactionsValidations;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;


@Service
@AllArgsConstructor
public class DepositService {

    private AccountRepository accountRepository;
    private DepositRepository depositRepository;
    private TransactionsValidations transactionsValidations;

    @Transactional
    public Deposit saveDeposit(Long account_id, Float depositValue) {
        HashMap<String, String> invalidFields = new HashMap<>();

        transactionsValidations.checkTransaction(invalidFields, depositValue, account_id);

        Account account = accountRepository.getReferenceById(account_id);
        Deposit deposit = Deposit.builder()
                .value(depositValue)
                .date(Utils.dateNow())
                .time(Utils.timeNow())
                .account(account)
                .build();

        account.setBalance(deposit.getValue() + account.getBalance());

        depositRepository.save(deposit);
        accountRepository.save(account);

        return deposit;

    }
}
