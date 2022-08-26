package com.example.apibanco.domain.service;

import com.example.apibanco.api.exception.NegocioException;
import com.example.apibanco.api.model.ContaOutput;
import com.example.apibanco.api.model.ExtratoInput;
import com.example.apibanco.api.model.TransferenciaEnviadaOutput;
import com.example.apibanco.api.model.TransferenciaRecebidaOutput;
import com.example.apibanco.domain.model.Cliente;
import com.example.apibanco.domain.model.Conta;
import com.example.apibanco.domain.repository.ContaRepository;
import com.example.apibanco.domain.repository.GerenteRepository;
import com.example.apibanco.domain.repository.transactions.DepositoRepository;
import com.example.apibanco.domain.repository.transactions.SaqueRepository;
import com.example.apibanco.domain.repository.transactions.TransferenciaRepository;
import com.example.apibanco.domain.utils.Utils;
import com.example.apibanco.domain.validations.ContaValidations;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class ContaService {

    private ContaRepository contaRepository;
    private DepositoRepository depositoRepository;
    private SaqueRepository saqueRepository;
    private TransferenciaRepository transferenciaRepository;
    private GerenteRepository gerenteRepository;
    private ModelMapper modelMapper;
    private ContaValidations contaValidations;

    @Transactional
    public void salvarConta(Cliente cliente) {
        contaRepository.save(Conta.builder()
                .saldo(0F)
                .dataCriacao(Utils.dateNow())
                .horaCriacao(Utils.timeNow())
                .cliente(cliente)
                .build());
    }

    public ExtratoInput mostrarExtrato(Long conta_id) {
        HashMap<String, String> camposInvalidos = new HashMap<>();

        contaValidations.verificaContaClienteInativa(camposInvalidos, conta_id);

        Conta conta = contaRepository.getReferenceById(conta_id);

        return ExtratoInput.builder()
                .conta(modelMapper.map(conta, ContaOutput.class))
                .depositos(depositoRepository.getByConta_Id(conta_id))
                .saques(saqueRepository.getByConta_Id(conta_id))
                .transferenciasEnviadas(transferenciaRepository.getByContaOrigemId(conta_id)
                        .stream()
                        .map(transferenciaEnviada -> modelMapper.map(transferenciaEnviada, TransferenciaEnviadaOutput.class))
                        .collect(Collectors.toList()))
                .transferenciasRecebidas(transferenciaRepository.getByContaDestinoId(conta_id)
                        .stream()
                        .map(transferenciaRecebida -> modelMapper.map(transferenciaRecebida, TransferenciaRecebidaOutput.class))
                        .collect(Collectors.toList()))
                .build();
    }

    public List<Conta> mostrarContasPorGerente(Long gerente_id) {
        HashMap<String, String> camposInvalidos = new HashMap<>();
        if (gerenteRepository.findById(gerente_id).isEmpty())
            camposInvalidos.put("/idGerente", "Gerente does not exist in the database");
        if (!camposInvalidos.isEmpty())
            throw new NegocioException("One or more fields are invalid.", camposInvalidos);
        return contaRepository.findContaByCliente_Gerente_Id(gerente_id);
    }

    public List<Conta> mostrarTodasContas(){
        return contaRepository.findAll();
    }

}
