package com.example.apibanco.domain.service.transactions;

import com.example.apibanco.domain.model.Conta;
import com.example.apibanco.domain.model.transactions.Saque;
import com.example.apibanco.domain.repository.ContaRepository;
import com.example.apibanco.domain.repository.transactions.SaqueRepository;
import com.example.apibanco.domain.utils.Utils;
import com.example.apibanco.domain.validations.TransactionsValidations;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;

@Service
@AllArgsConstructor
public class SaqueService {
    private ContaRepository contaRepository;
    private SaqueRepository saqueRepository;
    private TransactionsValidations transactionsValidations;

    @Transactional
    public Saque salvarSaque(Long conta_id, Float valorSaque) {
        HashMap<String, String> camposInvalidos = new HashMap<>();

        transactionsValidations.verificaSaque(camposInvalidos, valorSaque, conta_id);

        Conta conta = contaRepository.getReferenceById(conta_id);
        Saque saque = Saque.builder()
                .valor(valorSaque)
                .data(Utils.dateNow())
                .horario(Utils.timeNow())
                .conta(conta)
                .build();

        conta.setSaldo(conta.getSaldo() - saque.getValor());

        saqueRepository.save(saque);
        contaRepository.save(conta);

        return saque;

    }
}
