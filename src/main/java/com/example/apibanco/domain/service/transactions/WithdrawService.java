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
    public Withdraw saveWithdraw(Long conta_id, Float valorSaque) {
        HashMap<String, String> camposInvalidos = new HashMap<>();

        transactionsValidations.checkWithdraw(camposInvalidos, valorSaque, conta_id);

        Account account = accountRepository.getReferenceById(conta_id);
        Withdraw saque = Withdraw.builder()
                .value(valorSaque)
                .date(Utils.dateNow())
                .time(Utils.timeNow())
                .account(account)
                .build();

        account.setBalance(account.getBalance() - saque.getValue());

        withdrawRepository.save(saque);
        accountRepository.save(account);

        return saque;

    }
}
