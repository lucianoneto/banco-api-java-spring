package com.example.apibanco.domain.service;

import com.example.apibanco.api.exception.NegocioException;
import com.example.apibanco.api.model.ClienteInput;
import com.example.apibanco.domain.model.Cliente;
import com.example.apibanco.domain.model.ClienteEndereco;
import com.example.apibanco.domain.model.Gerente;
import com.example.apibanco.domain.repository.ClienteEnderecoRepository;
import com.example.apibanco.domain.repository.ClienteRepository;
import com.example.apibanco.domain.repository.GerenteRepository;
import com.example.apibanco.domain.validations.ClienteValidations;
import com.example.apibanco.domain.validations.GerenteValidations;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;


@Service
@AllArgsConstructor
public class ClienteService {
    private ClienteRepository clienteRepository;
    private ContaService contaService;
    private GerenteRepository gerenteRepository;
    private ClienteEnderecoRepository clienteEnderecoRepository;
    private ModelMapper modelMapper;
    private GerenteValidations gerenteValidations;
    private ClienteValidations clienteValidations;

    @Transactional
    public Cliente salvarCliente(ClienteInput clienteInput, Long gerente_id) {
        HashMap<String, String> camposInvalidos = new HashMap<>();

        Cliente cliente = modelMapper.map(clienteInput, Cliente.class);
        Gerente gerente = gerenteRepository.getReferenceById(gerente_id);
        verificaCamposInvalidos(camposInvalidos, gerente_id, cliente.getCpf(), cliente.getEmail());

        cliente.setGerente(gerente);
        salvarEnderecoCliente(clienteInput.getEndereco());
        cliente.setAtivo(true);
        contaService.salvarConta(cliente);

        return clienteRepository.save(cliente);
    }

    private void salvarEnderecoCliente(ClienteEndereco clienteEndereco) {
        clienteEndereco.setCliente(clienteEndereco.getCliente());
        clienteEnderecoRepository.save(clienteEndereco);
    }

    public Cliente inativarCliente(Long gerente_id, Long cliente_id){
        HashMap<String, String> camposInvalidos = new HashMap<>();

        clienteValidations.verificaContaClienteInativa(camposInvalidos, cliente_id);
        gerenteValidations.verificaVinculoGerenteCliente(camposInvalidos, gerente_id, cliente_id);

        Cliente cliente = clienteRepository.getReferenceById(cliente_id);

        cliente.setAtivo(false);

        return clienteRepository.save(cliente);
    }

    public Cliente ativarCliente(Long gerente_id, Long cliente_id){
        HashMap<String, String> camposInvalidos = new HashMap<>();

        clienteValidations.verificaContaClienteAtiva(camposInvalidos, cliente_id);
        gerenteValidations.verificaVinculoGerenteCliente(camposInvalidos, gerente_id, cliente_id);

        Cliente cliente = clienteRepository.getReferenceById(cliente_id);

        cliente.setAtivo(true);

        return clienteRepository.save(cliente);
    }

    private void verificaCamposInvalidos(HashMap<String, String> camposInvalidos, Long gerente_id, String cpf, String email) {
        verificaGerente(camposInvalidos, gerente_id);

        if (clienteRepository.existsByCpf(cpf))
            camposInvalidos.put("cpf", "CPF already registered in the database.");
        if (clienteRepository.existsByEmail(email))
            camposInvalidos.put("e-mail", "E-mail already registered in the database.");
        if (!camposInvalidos.isEmpty())
            throw new NegocioException("One or more fields are invalid.", camposInvalidos);
    }

    private void verificaGerente(HashMap<String, String> camposInvalidos, Long gerente_id){
        if (gerenteRepository.findById(gerente_id).isEmpty())
            camposInvalidos.put("/idGerente", "Gerente does not exist in the database");
        if (!camposInvalidos.isEmpty())
            throw new NegocioException("One or more fields are invalid.", camposInvalidos);
    }


}
