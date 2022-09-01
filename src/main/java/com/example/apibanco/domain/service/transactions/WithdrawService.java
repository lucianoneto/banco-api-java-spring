package com.example.apibanco.domain.service.transactions;

import com.example.apibanco.domain.model.Account;
import com.example.apibanco.domain.model.transactions.Withdraw;
import com.example.apibanco.domain.repository.AccountRepository;
import com.example.apibanco.domain.repository.transactions.WithdrawRepository;
import com.example.apibanco.domain.utils.Utils;
import com.example.apibanco.domain.validations.TransactionsValidations;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;

@Service
@AllArgsConstructor
public class WithdrawService {

    private AccountRepository accountRepository;
    private WithdrawRepository withdrawRepository;
    private TransactionsValidations transactionsValidations;

    @Transactional
    public Withdraw saveWithdraw(Long account_id, Float withdrawValue) {
        HashMap<String, String> invalidFields = new HashMap<>();

        transactionsValidations.checkWithdraw(invalidFields, withdrawValue, account_id);

        Account account = accountRepository.getReferenceById(account_id);
        Withdraw withdraw = Withdraw.builder()
                .value(withdrawValue)
                .date(Utils.dateNow())
                .time(Utils.timeNow())
                .account(account)
                .build();

        account.setBalance(account.getBalance() - withdraw.getValue());

        withdrawRepository.save(withdraw);
        accountRepository.save(account);

        return withdraw;

    }
}
