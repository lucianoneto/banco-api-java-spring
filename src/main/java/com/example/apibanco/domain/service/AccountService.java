package com.example.apibanco.domain.service;

import com.example.apibanco.api.model.AccountOutput;
import com.example.apibanco.api.model.StatementInput;
import com.example.apibanco.api.model.TransferSentOutput;
import com.example.apibanco.api.model.TransferReceivedOutput;
import com.example.apibanco.domain.model.Account;
import com.example.apibanco.domain.model.Client;
import com.example.apibanco.domain.repository.AccountRepository;
import com.example.apibanco.domain.repository.transactions.DepositRepository;
import com.example.apibanco.domain.repository.transactions.WithdrawRepository;
import com.example.apibanco.domain.repository.transactions.TransferRepository;
import com.example.apibanco.domain.utils.Utils;
import com.example.apibanco.domain.validations.AccountValidations;
import com.example.apibanco.domain.validations.ManagerValidations;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
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
        accountRepository.save(Account.builder()
                .balance(0F)
                .creationDate(Utils.dateNow())
                .creationTime(Utils.timeNow())
                .client(client)
                .build());
    }

    public StatementInput showBankStatement(Long account_id) {
        HashMap<String, String> invalidFields = new HashMap<>();

        accountValidations.checkInactiveClientAccount(invalidFields, account_id);

        Account account = accountRepository.getReferenceById(account_id);

        return StatementInput.builder()
                .account(modelMapper.map(account, AccountOutput.class))
                .deposits(depositRepository.getByAccount_Id(account_id))
                .withdraws(withdrawRepository.getByAccount_Id(account_id))
                .transferSentOutputs(transferRepository.getByOriginAccountId(account_id)
                        .stream()
                        .map(transferSent -> modelMapper.map(transferSent, TransferSentOutput.class))
                        .collect(Collectors.toList()))
                .transferReceivedOutputs(transferRepository.getByDestinyAccountId(account_id)
                        .stream()
                        .map(transferReceived -> modelMapper.map(transferReceived, TransferReceivedOutput.class))
                        .collect(Collectors.toList()))
                .build();
    }

    public List<Account> showAccountsByManager(Long manager_id) {
        HashMap<String, String> invalidFields = new HashMap<>();
        managerValidations.checkExistsManager(invalidFields, manager_id);
        return accountRepository.findAccountByClient_Manager_Id(manager_id);
    }

    public List<Account> showAllAccounts(){
        return accountRepository.findAll();
    }

}
