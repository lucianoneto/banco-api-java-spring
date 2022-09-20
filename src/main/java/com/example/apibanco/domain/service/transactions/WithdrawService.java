package com.example.apibanco.domain.service.transactions;

import com.example.apibanco.domain.model.Account;
import com.example.apibanco.domain.model.transactions.Withdraw;
import com.example.apibanco.domain.repository.AccountRepository;
import com.example.apibanco.domain.repository.transactions.WithdrawRepository;
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
public class WithdrawService {

    private AccountRepository accountRepository;
    private WithdrawRepository withdrawRepository;
    private TransactionsValidations transactionsValidations;

    @Transactional
    public Withdraw saveWithdraw(Long accountId, float withdrawValue) {
        Map<String, String> invalidFields = new HashMap<>();

        transactionsValidations.checkWithdraw(invalidFields, withdrawValue, accountId);

        Account account = accountRepository.getReferenceById(accountId);
        Withdraw withdraw = new Withdraw()
                .setValue(withdrawValue)
                .setDate(LocalDate.now())
                .setTime(LocalTime.now())
                .setAccount(account);

        account.setBalance(account.getBalance() - withdraw.getValue());

        withdrawRepository.save(withdraw);
        accountRepository.save(account);

        return withdraw;

    }
}
