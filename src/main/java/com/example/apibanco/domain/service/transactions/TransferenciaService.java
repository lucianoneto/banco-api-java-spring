package com.example.apibanco.domain.service.transactions;

import com.example.apibanco.api.model.TransferenciaEnviadaOutput;
import com.example.apibanco.domain.model.Conta;
import com.example.apibanco.domain.model.transactions.Transferencia;
import com.example.apibanco.domain.repository.ContaRepository;
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
    private ContaRepository contaRepository;
    private TransferenciaRepository transferenciaRepository;
    private TransactionsValidations transactionsValidations;
    private ModelMapper modelMapper;

    @Transactional
    public TransferenciaEnviadaOutput salvarTransferencia(Long idContaOrigem, Long idContaDestino, Float valor) {
        HashMap<String, String> camposInvalidos = new HashMap<>();

        transactionsValidations.verificaTransferencia(camposInvalidos, valor, idContaOrigem, idContaDestino);

        Conta contaOrigem = contaRepository.getReferenceById(idContaOrigem);
        Conta contaDestino = contaRepository.getReferenceById(idContaDestino);

        Transferencia transferencia = Transferencia.builder()
                .valor(valor)
                .data(Utils.dateNow())
                .horario(Utils.timeNow())
                .contaDestino(contaDestino)
                .contaOrigem(contaOrigem)
                .build();

        contaOrigem.setSaldo(contaOrigem.getSaldo() - transferencia.getValor());
        contaRepository.save(contaOrigem);

        contaDestino.setSaldo(contaDestino.getSaldo() + transferencia.getValor());
        contaRepository.save(contaDestino);

        transferenciaRepository.save(transferencia);

        return modelMapper.map(transferencia, TransferenciaEnviadaOutput.class);

    }
}
