package com.example.apibanco.domain.service;

import com.example.apibanco.api.exception.BusinessException;
import com.example.apibanco.api.model.AccountOutput;
import com.example.apibanco.api.model.ExtratoInput;
import com.example.apibanco.api.model.TransferSentOutput;
import com.example.apibanco.api.model.TransferReceivedOutput;
import com.example.apibanco.domain.model.Account;
import com.example.apibanco.domain.model.Client;
import com.example.apibanco.domain.repository.AccountRepository;
import com.example.apibanco.domain.repository.ManagerRepository;
import com.example.apibanco.domain.repository.transactions.DepositRepository;
import com.example.apibanco.domain.repository.transactions.WithdrawRepository;
import com.example.apibanco.domain.repository.transactions.TransferenciaRepository;
import com.example.apibanco.domain.utils.Utils;
import com.example.apibanco.domain.validations.AccountValidations;
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
    private TransferenciaRepository transferenciaRepository;
    private ManagerRepository managerRepository;
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

    public ExtratoInput showBankStatement(Long conta_id) {
        HashMap<String, String> camposInvalidos = new HashMap<>();

        accountValidations.checkInactiveClientAccount(camposInvalidos, conta_id);

        Account account = accountRepository.getReferenceById(conta_id);

        return ExtratoInput.builder()
                .account(modelMapper.map(account, AccountOutput.class))
                .deposits(depositRepository.getByAccount_Id(conta_id))
                .withdraws(withdrawRepository.getByAccount_Id(conta_id))
                .transferSentOutputs(transferenciaRepository.getByOriginAccountId(conta_id)
                        .stream()
                        .map(transferenciaEnviada -> modelMapper.map(transferenciaEnviada, TransferSentOutput.class))
                        .collect(Collectors.toList()))
                .transferReceivedOutputs(transferenciaRepository.getByDestinyAccountId(conta_id)
                        .stream()
                        .map(transferenciaRecebida -> modelMapper.map(transferenciaRecebida, TransferReceivedOutput.class))
                        .collect(Collectors.toList()))
                .build();
    }

    public List<Account> showAccountsByManager(Long gerente_id) {
        HashMap<String, String> camposInvalidos = new HashMap<>();
        if (managerRepository.findById(gerente_id).isEmpty())
            camposInvalidos.put("/idGerente", "Gerente does not exist in the database");
        if (!camposInvalidos.isEmpty())
            throw new BusinessException("One or more fields are invalid.", camposInvalidos);
        return accountRepository.findAccountByClient_Manager_Id(gerente_id);
    }

    public List<Account> showAllAccounts(){
        return accountRepository.findAll();
    }

}
