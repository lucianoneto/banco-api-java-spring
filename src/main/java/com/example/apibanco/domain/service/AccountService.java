package com.example.apibanco.domain.service;

import com.example.apibanco.api.model.AccountOutput;
import com.example.apibanco.api.model.StatementInput;
import com.example.apibanco.api.model.TransferReceivedOutput;
import com.example.apibanco.api.model.TransferSentOutput;
import com.example.apibanco.domain.model.Account;
import com.example.apibanco.domain.model.Client;
import com.example.apibanco.domain.repository.AccountRepository;
import com.example.apibanco.domain.repository.transactions.DepositRepository;
import com.example.apibanco.domain.repository.transactions.TransferRepository;
import com.example.apibanco.domain.repository.transactions.WithdrawRepository;
import com.example.apibanco.domain.validations.AccountValidations;
import com.example.apibanco.domain.validations.ManagerValidations;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class AccountService {

    private AccountRepository accountRepository;
    private DepositRepository depositRepository;
    private WithdrawRepository withdrawRepository;
    private TransferRepository transferRepository;
    ManagerValidations managerValidations;
    private ModelMapper modelMapper;
    private AccountValidations accountValidations;

    @Transactional
    public void saveAccount(Client client) {
        accountRepository.save(new Account()
                .setBalance(0)
                .setCreationDate(LocalDate.now())
                .setCreationTime(LocalTime.now())
                .setClient(client));
    }

    public StatementInput showBankStatement(Long accountId) {
        Map<String, String> invalidFields = new HashMap<>();

        accountValidations.checkInactiveClientAccount(invalidFields, accountId);

        Account account = accountRepository.getReferenceById(accountId);

        return StatementInput.builder()
                .account(modelMapper.map(account, AccountOutput.class))
                .deposits(depositRepository.getByAccountId(accountId))
                .withdraws(withdrawRepository.getByAccountId(accountId))
                .transferSentOutputs(transferRepository.getByOriginAccountId(accountId)
                        .stream()
                        .map(transferSent -> modelMapper.map(transferSent, TransferSentOutput.class))
                        .collect(Collectors.toList()))
                .transferReceivedOutputs(transferRepository.getByDestinyAccountId(accountId)
                        .stream()
                        .map(transferReceived -> modelMapper.map(transferReceived, TransferReceivedOutput.class))
                        .collect(Collectors.toList()))
                .build();
    }

    public List<Account> showAccountsByManager(Long managerId) {
        Map<String, String> invalidFields = new HashMap<>();
        managerValidations.checkExistsManager(invalidFields, managerId);
        return accountRepository.findAccountByClientManagerId(managerId);
    }

    public List<Account> showAllAccounts() {
        return accountRepository.findAll();
    }

}
