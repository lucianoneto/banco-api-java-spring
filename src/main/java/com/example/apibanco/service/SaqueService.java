package com.example.apibanco.service;

import com.example.apibanco.model.Conta;
import com.example.apibanco.model.transactions.Saque;
import com.example.apibanco.repository.ContaRepository;
import com.example.apibanco.repository.SaqueRepository;
import com.example.apibanco.utils.Utils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class SaqueService {
    private ContaRepository contaRepository;
    private SaqueRepository saqueRepository;

    @Transactional
    public String salvarSaque(Long cliente_id, Float valorSaque, Saque saque) {
        Conta conta = contaRepository.getById(cliente_id);
        if (conta.getSaldo() >= valorSaque) {
            saque.setValor(valorSaque);
            saque.setData(Utils.dateNow());
            saque.setHorario(Utils.timeNow());
            saque.setConta(conta);
            conta.setSaldo(conta.getSaldo() - saque.getValor());
            saqueRepository.save(saque);
            contaRepository.save(conta);

            return "Saldo atual: " + conta.getSaldo();
        }
        return "Saldo inválido, o valor na conta é menor do que o requisitado";
    }
}
