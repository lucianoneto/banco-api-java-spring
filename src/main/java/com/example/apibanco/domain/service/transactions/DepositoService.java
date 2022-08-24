package com.example.apibanco.domain.service.transactions;

import com.example.apibanco.domain.model.Conta;
import com.example.apibanco.domain.model.transactions.Deposito;
import com.example.apibanco.domain.repository.ContaRepository;
import com.example.apibanco.domain.repository.transactions.DepositoRepository;
import com.example.apibanco.domain.utils.Utils;
import com.example.apibanco.domain.validations.TransactionsValidations;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;


@Service
@AllArgsConstructor
public class DepositoService {
    private ContaRepository contaRepository;
    private DepositoRepository depositoRepository;
    private TransactionsValidations transactionsValidations;

    @Transactional
    public Deposito salvarDeposito(Long conta_id, Float valorDeposito) {
        HashMap<String, String> camposInvalidos = new HashMap<>();

        transactionsValidations.verificaTransacao(camposInvalidos, valorDeposito, conta_id);

        Conta conta = contaRepository.getReferenceById(conta_id);
        Deposito deposito = Deposito.builder()
                .valor(valorDeposito)
                .data(Utils.dateNow())
                .horario(Utils.timeNow())
                .conta(conta)
                .build();

        conta.setSaldo(deposito.getValor() + conta.getSaldo());

        depositoRepository.save(deposito);
        contaRepository.save(conta);

        return deposito;

    }
}
