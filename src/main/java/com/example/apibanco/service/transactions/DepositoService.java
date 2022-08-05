package com.example.apibanco.service.transactions;

import com.example.apibanco.model.Conta;
import com.example.apibanco.model.transactions.Deposito;
import com.example.apibanco.repository.ContaRepository;
import com.example.apibanco.repository.transactions.DepositoRepository;
import com.example.apibanco.utils.Utils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.sql.Time;


@Service
@AllArgsConstructor
public class DepositoService {
    private ContaRepository contaRepository;

    private DepositoRepository depositoRepository;

    @Transactional
    public String salvarDeposito(Long cliente_id, Float valorDeposito, Deposito deposito) {
        Conta conta = contaRepository.getById(cliente_id);
        deposito.setValor(valorDeposito);
        deposito.setData((Date) Utils.dateNow());
        deposito.setHorario((Time) Utils.timeNow());
        deposito.setConta(conta);
        conta.setSaldo(deposito.getValor() + conta.getSaldo());
        depositoRepository.save(deposito);
        contaRepository.save(conta);

        return "Saldo atual: " + conta.getSaldo();

    }
}
