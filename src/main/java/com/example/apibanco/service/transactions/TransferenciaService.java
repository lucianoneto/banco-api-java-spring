package com.example.apibanco.service.transactions;

import com.example.apibanco.exception.NegocioException;
import com.example.apibanco.model.Conta;
import com.example.apibanco.model.transactions.Transferencia;
import com.example.apibanco.repository.ContaRepository;
import com.example.apibanco.repository.transactions.TransferenciaRepository;
import com.example.apibanco.utils.Utils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;


@Service
@AllArgsConstructor
public class TransferenciaService {
    private ContaRepository contaRepository;
    private TransferenciaRepository transferenciaRepository;

    @Transactional
    public String salvarTransferencia(Long idOrigem, Long idDestino, Float valorTransferencia) {
        Conta contaOrigem = contaRepository.getById(idOrigem);
        Conta contaDestino = contaRepository.getById(idDestino);

        HashMap<String, String> camposInvalidos = new HashMap<>();
        if (valorTransferencia < 1 || contaOrigem.getSaldo() < valorTransferencia) {
            camposInvalidos.put("valorTransferencia", "Invalid value of bank transfer.");
            throw new NegocioException("One or more fields are invalid.", camposInvalidos);
        }

        Transferencia transferencia = Transferencia.builder()
                .valor(valorTransferencia)
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
        return "Saldo atual: " + contaOrigem.getSaldo();

    }
}
