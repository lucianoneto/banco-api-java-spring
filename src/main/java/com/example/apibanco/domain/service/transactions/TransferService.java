package com.example.apibanco.domain.service.transactions;

import com.example.apibanco.api.model.TransferSentOutput;
import com.example.apibanco.domain.model.Account;
import com.example.apibanco.domain.model.transactions.Transfer;
import com.example.apibanco.domain.repository.AccountRepository;
import com.example.apibanco.domain.repository.transactions.TransferRepository;
import com.example.apibanco.domain.utils.Utils;
import com.example.apibanco.domain.validations.TransactionsValidations;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;


@Service
@AllArgsConstructor
public class TransferService {

    private AccountRepository accountRepository;
    private TransferRepository transferRepository;
    private TransactionsValidations transactionsValidations;
    private ModelMapper modelMapper;

    @Transactional
    public TransferSentOutput saveTransfer(Long idOriginAccount, Long idDestinyAccount, Float value) {
        HashMap<String, String> invalidFields = new HashMap<>();

        transactionsValidations.checkTransfer(invalidFields, value, idOriginAccount, idDestinyAccount);

        Account accountOrigem = accountRepository.getReferenceById(idOriginAccount);
        Account accountDestino = accountRepository.getReferenceById(idDestinyAccount);

        Transfer transfer = Transfer.builder()
                .value(value)
                .date(Utils.dateNow())
                .time(Utils.timeNow())
                .destinyAccount(accountDestino)
                .originAccount(accountOrigem)
                .build();

        accountOrigem.setBalance(accountOrigem.getBalance() - transfer.getValue());
        accountRepository.save(accountOrigem);

        accountDestino.setBalance(accountDestino.getBalance() + transfer.getValue());
        accountRepository.save(accountDestino);

        transferRepository.save(transfer);

        return modelMapper.map(transfer, TransferSentOutput.class);

    }
}
