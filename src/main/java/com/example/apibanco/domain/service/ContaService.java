package com.example.apibanco.domain.service;

import com.example.apibanco.api.exception.NegocioException;
import com.example.apibanco.api.model.ExtratoInput;
import com.example.apibanco.api.model.TransferenciaEnviadaOutput;
import com.example.apibanco.api.model.TransferenciaRecebidaOutput;
import com.example.apibanco.domain.model.Cliente;
import com.example.apibanco.domain.model.Conta;
import com.example.apibanco.domain.repository.ContaRepository;
import com.example.apibanco.domain.repository.transactions.DepositoRepository;
import com.example.apibanco.domain.repository.transactions.SaqueRepository;
import com.example.apibanco.domain.repository.transactions.TransferenciaRepository;
import com.example.apibanco.domain.utils.Utils;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class ContaService {
    private ContaRepository contaRepository;
    private DepositoRepository depositoRepository;
    private SaqueRepository saqueRepository;
    private TransferenciaRepository transferenciaRepository;
    private ModelMapper modelMapper;

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
        HashMap<String, String> camposInvalidos = new HashMap<>();
        verificaCliente(camposInvalidos, cliente_id);
        return ExtratoInput.builder()
                .conta(contaRepository.getById(cliente_id))
                .depositos(depositoRepository.getByConta_Id(cliente_id))
                .saques(saqueRepository.getByConta_Id(cliente_id))
                .transferenciasEnviadas(transferenciaRepository.getByContaOrigemId(cliente_id)
                        .stream()
                        .map(transferenciaEnviada -> modelMapper.map(transferenciaEnviada, TransferenciaEnviadaOutput.class))
                        .collect(Collectors.toList()))
                .transferenciasRecebidas(transferenciaRepository.getByContaDestinoId(cliente_id)
                        .stream()
                        .map(transferenciaRecebida -> modelMapper.map(transferenciaRecebida, TransferenciaRecebidaOutput.class))
                        .collect(Collectors.toList()))
                .build();
    }

    private void verificaCliente(HashMap<String, String> camposInvalidos, Long cliente_id) {
        if (contaRepository.findById(cliente_id).isEmpty())
            camposInvalidos.put("/idCliente", "Cliente does not exist in the database");
        if (!camposInvalidos.isEmpty())
            throw new NegocioException("One or more fields are invalid.", camposInvalidos);

    }

}
