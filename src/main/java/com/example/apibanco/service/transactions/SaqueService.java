package com.example.apibanco.service.transactions;

import com.example.apibanco.exception.NegocioException;
import com.example.apibanco.model.Conta;
import com.example.apibanco.model.transactions.Saque;
import com.example.apibanco.repository.ContaRepository;
import com.example.apibanco.repository.transactions.SaqueRepository;
import com.example.apibanco.utils.Utils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;

@Service
@AllArgsConstructor
public class SaqueService {
    private ContaRepository contaRepository;
    private SaqueRepository saqueRepository;

    @Transactional
    public String salvarSaque(Long cliente_id, Float valorSaque) {
        Conta conta = contaRepository.getById(cliente_id);
        HashMap<String, String> camposInvalidos = new HashMap<>();

        if (valorSaque < 1 || conta.getSaldo() < valorSaque) {
            camposInvalidos.put("valorSaque", "Invalid value of bank draft.");
            throw new NegocioException("One or more fields are invalid.", camposInvalidos);
        }
        Saque saque = Saque.builder()
                .valor(valorSaque)
                .data(Utils.dateNow())
                .horario(Utils.timeNow())
                .conta(conta)
                .build();

        conta.setSaldo(conta.getSaldo() - saque.getValor());
        saqueRepository.save(saque);
        contaRepository.save(conta);

        return "Saldo atual: " + conta.getSaldo();

    }
}
