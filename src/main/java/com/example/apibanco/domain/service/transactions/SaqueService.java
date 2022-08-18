package com.example.apibanco.domain.service.transactions;

import com.example.apibanco.domain.model.Conta;
import com.example.apibanco.domain.model.transactions.Saque;
import com.example.apibanco.domain.repository.ContaRepository;
import com.example.apibanco.domain.repository.transactions.SaqueRepository;
import com.example.apibanco.domain.utils.TransactionsUtils;
import com.example.apibanco.domain.utils.Utils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;

@Service
@AllArgsConstructor
public class SaqueService {
    private ContaRepository contaRepository;
    private SaqueRepository saqueRepository;
    private TransactionsUtils transactionsUtils;

    @Transactional
    public Saque salvarSaque(Long cliente_id, Float valorSaque) {
        Conta conta = contaRepository.getById(cliente_id);
        HashMap<String, String> camposInvalidos = new HashMap<>();
        transactionsUtils.verificaSaque(camposInvalidos, valorSaque, cliente_id);
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
