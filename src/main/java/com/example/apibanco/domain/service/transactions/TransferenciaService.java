package com.example.apibanco.domain.service.transactions;

import com.example.apibanco.api.model.TransferSentOutput;
import com.example.apibanco.domain.model.Account;
import com.example.apibanco.domain.model.transactions.Transfer;
import com.example.apibanco.domain.repository.AccountRepository;
import com.example.apibanco.domain.repository.transactions.TransferenciaRepository;
import com.example.apibanco.domain.utils.Utils;
import com.example.apibanco.domain.validations.TransactionsValidations;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;


@Service
@AllArgsConstructor
public class TransferenciaService {

    private AccountRepository accountRepository;
    private TransferenciaRepository transferenciaRepository;
    private TransactionsValidations transactionsValidations;
    private ModelMapper modelMapper;

    @Transactional
    public TransferSentOutput saveTransfer(Long idContaOrigem, Long idContaDestino, Float valor) {
        HashMap<String, String> camposInvalidos = new HashMap<>();

        transactionsValidations.checkTransfer(camposInvalidos, valor, idContaOrigem, idContaDestino);

        Account accountOrigem = accountRepository.getReferenceById(idContaOrigem);
        Account accountDestino = accountRepository.getReferenceById(idContaDestino);

        Transfer transfer = Transfer.builder()
                .value(valor)
                .date(Utils.dateNow())
                .time(Utils.timeNow())
                .destinyAccount(accountDestino)
                .originAccount(accountOrigem)
                .build();

        accountOrigem.setBalance(accountOrigem.getBalance() - transfer.getValue());
        accountRepository.save(accountOrigem);

        accountDestino.setBalance(accountDestino.getBalance() + transfer.getValue());
        accountRepository.save(accountDestino);

        transferenciaRepository.save(transfer);

        return modelMapper.map(transfer, TransferSentOutput.class);

    }
}
