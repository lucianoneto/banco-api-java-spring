package com.example.apibanco.service;

import com.example.apibanco.model.Conta;
import com.example.apibanco.model.input.ClienteInput;
import com.example.apibanco.model.input.ExtratoInput;
import com.example.apibanco.repository.ContaRepository;
import com.example.apibanco.repository.transactions.DepositoRepository;
import com.example.apibanco.repository.transactions.SaqueRepository;
import com.example.apibanco.repository.transactions.TransferenciaRepository;
import com.example.apibanco.utils.Utils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@AllArgsConstructor
public class ContaService {
    private ContaRepository contaRepository;
    private DepositoRepository depositoRepository;
    private SaqueRepository saqueRepository;

    private TransferenciaRepository transferenciaRepository;

    @Transactional
    public void salvarConta(Conta conta, ClienteInput clienteInput) {
        conta.setSaldo(0F);
        conta.setDataCriacao(Utils.dateNow());
        conta.setHoraCriacao(Utils.timeNow());
        conta.setCliente(clienteInput.getCliente());

        contaRepository.save(conta);
    }

    public ExtratoInput mostrarExtrato(Long cliente_id) {
        return ExtratoInput.builder()
                .conta(contaRepository.getById(cliente_id))
                .depositos(depositoRepository.getByConta_Id(cliente_id))
                .saques(saqueRepository.getByConta_Id(cliente_id))
                .transferenciasOrigem(transferenciaRepository.getByContaOrigemId(cliente_id))
                .transferenciasDestino(transferenciaRepository.getByContaDestinoId(cliente_id))
                .build();
    }

    public boolean verificaObjeto(Long cliente_id) {
        return contaRepository.findById(cliente_id).isPresent();
    }

}
