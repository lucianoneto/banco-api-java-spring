package com.example.apibanco.domain.service.transactions;

import com.example.apibanco.domain.model.Conta;
import com.example.apibanco.domain.model.transactions.Deposito;
import com.example.apibanco.domain.repository.ContaRepository;
import com.example.apibanco.domain.repository.transactions.DepositoRepository;
import com.example.apibanco.domain.utils.TransactionsUtils;
import com.example.apibanco.domain.utils.Utils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;


@Service
@AllArgsConstructor
public class DepositoService {
    private ContaRepository contaRepository;
    private DepositoRepository depositoRepository;
    private TransactionsUtils transactionsUtils;

    @Transactional
    public Deposito salvarDeposito(Long cliente_id, Float valorDeposito) {
        HashMap<String, String> camposInvalidos = new HashMap<>();
        transactionsUtils.verificaTransacao(camposInvalidos, valorDeposito, cliente_id);
        Conta conta = contaRepository.getById(cliente_id);
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
