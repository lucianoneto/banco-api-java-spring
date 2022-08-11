package com.example.apibanco.service.transactions;

import com.example.apibanco.exception.NegocioException;
import com.example.apibanco.model.Conta;
import com.example.apibanco.model.transactions.Deposito;
import com.example.apibanco.repository.ContaRepository;
import com.example.apibanco.repository.transactions.DepositoRepository;
import com.example.apibanco.utils.Utils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;


@Service
@AllArgsConstructor
public class DepositoService {
    private ContaRepository contaRepository;
    private DepositoRepository depositoRepository;

    @Transactional
    public String salvarDeposito(Long cliente_id, Float valorDeposito) {
        HashMap<String, String> camposInvalidos = new HashMap<>();
        if (valorDeposito < 1) {
            camposInvalidos.put("valorDeposito", "Invalid deposit amount.");
            throw new NegocioException("One or more fields are invalid.", camposInvalidos);
        }
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

        return "Saldo atual: " + conta.getSaldo();

    }
}
