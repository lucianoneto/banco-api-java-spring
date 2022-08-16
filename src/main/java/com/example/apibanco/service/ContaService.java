package com.example.apibanco.service;

import com.example.apibanco.model.Cliente;
import com.example.apibanco.model.Conta;
import com.example.apibanco.model.input.ExtratoInput;
import com.example.apibanco.repository.ContaRepository;
import com.example.apibanco.repository.transactions.*;
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

    private TransferenciaRecebidaRepository transferenciaRecebidaRepository;
    private TransferenciaEnviadaRepository transferenciaEnviadaRepository;

    @Transactional
    public void salvarConta(Cliente cliente) {
        contaRepository.save(Conta.builder()
                .saldo(0F)
                .dataCriacao(Utils.dateNow())
                .horaCriacao(Utils.timeNow())
                .cliente(cliente)
                .build());
    }

    public ExtratoInput mostrarExtrato(Long cliente_id) {
        return ExtratoInput.builder()
                .conta(contaRepository.getById(cliente_id))
                .depositos(depositoRepository.getByConta_Id(cliente_id))
                .saques(saqueRepository.getByConta_Id(cliente_id))
                .transferenciasEnviadas(transferenciaEnviadaRepository.getByContaOrigemId(cliente_id))
                .transferenciasRecebidas(transferenciaRecebidaRepository.getByContaDestinoId(cliente_id))
                .build();
    }

    public boolean verificaObjeto(Long cliente_id) {
        return contaRepository.findById(cliente_id).isPresent();
    }

}
