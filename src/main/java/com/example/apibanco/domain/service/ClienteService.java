package com.example.apibanco.domain.service;

import com.example.apibanco.api.exception.NegocioException;
import com.example.apibanco.api.model.ClienteInput;
import com.example.apibanco.api.model.ClientePatchInput;
import com.example.apibanco.domain.assembler.ClientePatchInputAssembler;
import com.example.apibanco.domain.model.Cliente;
import com.example.apibanco.domain.model.Gerente;
import com.example.apibanco.domain.repository.ClienteRepository;
import com.example.apibanco.domain.repository.GerenteRepository;
import com.example.apibanco.domain.validations.ClienteValidations;
import com.example.apibanco.domain.validations.ContaValidations;
import com.example.apibanco.domain.validations.GerenteValidations;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.HashMap;


@Service
@AllArgsConstructor
public class ClienteService {

    private ClienteRepository clienteRepository;
    private ContaService contaService;
    private GerenteRepository gerenteRepository;
    private ModelMapper modelMapper;
    private GerenteValidations gerenteValidations;
    private ContaValidations contaValidations;
    private ClientePatchInputAssembler clientePatchInputAssembler;
    private ClienteValidations clienteValidations;

    public Cliente salvarCliente(ClienteInput clienteInput, Long gerente_id) {
        HashMap<String, String> camposInvalidos = new HashMap<>();

        Cliente cliente = modelMapper.map(clienteInput, Cliente.class);
        Gerente gerente = gerenteRepository.getReferenceById(gerente_id);
        clienteValidations.verificaCamposInvalidos(camposInvalidos, gerente_id, cliente.getCpf(), cliente.getEmail());

        cliente.setGerente(gerente);
        cliente.setAtivo(true);

        contaService.salvarConta(cliente);

        return clienteRepository.save(cliente);
    }

    public Cliente atualizarCliente(ClientePatchInput clientePatchInput, Long gerente_id, Long cliente_id) {
        HashMap<String, String> camposInvalidos = new HashMap<>();

        contaValidations.verificaContaClienteInativa(camposInvalidos, cliente_id);
        gerenteValidations.verificaVinculoGerenteCliente(camposInvalidos, gerente_id, cliente_id);
        clienteValidations.verificaCamposInvalidos(camposInvalidos, gerente_id, clientePatchInput.getEmail());

        Cliente cliente = clienteRepository.getReferenceById(cliente_id);

        cliente = clientePatchInputAssembler.merge(clientePatchInput, cliente);

        return clienteRepository.save(cliente);
    }
    public Cliente inativarCliente(Long gerente_id, Long cliente_id){

        HashMap<String, String> camposInvalidos = new HashMap<>();

        contaValidations.verificaContaClienteInativa(camposInvalidos, cliente_id);
        gerenteValidations.verificaVinculoGerenteCliente(camposInvalidos, gerente_id, cliente_id);

        Cliente cliente = clienteRepository.getReferenceById(cliente_id);

        cliente.setAtivo(false);

        return clienteRepository.save(cliente);
    }

    public Cliente ativarCliente(Long gerente_id, Long cliente_id){
        HashMap<String, String> camposInvalidos = new HashMap<>();

        contaValidations.verificaContaClienteAtiva(camposInvalidos, cliente_id);
        gerenteValidations.verificaVinculoGerenteCliente(camposInvalidos, gerente_id, cliente_id);

        Cliente cliente = clienteRepository.getReferenceById(cliente_id);

        cliente.setAtivo(true);

        return clienteRepository.save(cliente);
    }



}
