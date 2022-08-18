package com.example.apibanco.domain.service;

import com.example.apibanco.api.exception.NegocioException;
import com.example.apibanco.api.model.ClienteInput;
import com.example.apibanco.domain.model.Cliente;
import com.example.apibanco.domain.model.ClienteEndereco;
import com.example.apibanco.domain.repository.ClienteEnderecoRepository;
import com.example.apibanco.domain.repository.ClienteRepository;
import com.example.apibanco.domain.repository.GerenteRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;


@Service
@AllArgsConstructor
public class ClienteService {
    private ClienteRepository clienteRepository;
    private ContaService contaService;
    private GerenteService gerenteService;
    private GerenteRepository gerenteRepository;
    private ClienteEnderecoRepository clienteEnderecoRepository;
    private ModelMapper modelMapper;

    @Transactional
    public Cliente salvarCliente(ClienteInput clienteInput, Long gerente_id) {
        HashMap<String, String> camposInvalidos = new HashMap<>();

        Cliente cliente = modelMapper.map(clienteInput, Cliente.class);

        verificaCamposInvalidos(camposInvalidos, gerente_id, cliente.getCpf(), cliente.getEmail());

        contaService.salvarConta(cliente);
        cliente.setGerente(gerenteRepository.getById(gerente_id));
        salvarEnderecoCliente(clienteInput.getEndereco());
        return clienteRepository.save(cliente);
    }

    public List<Cliente> mostrarClientes() {
        return clienteRepository.findAll();
    }

    private void salvarEnderecoCliente(ClienteEndereco clienteEndereco) {
        clienteEndereco.setCliente(clienteEndereco.getCliente());
        clienteEnderecoRepository.save(clienteEndereco);
    }

    private void verificaCamposInvalidos(HashMap<String, String> camposInvalidos, Long gerente_id, String cpf, String email) {
        if (!gerenteService.verificaGerente(gerente_id))
            camposInvalidos.put("/idGerente", "Gerente does not exist in the database");
        if (clienteRepository.existsByCpf(cpf))
            camposInvalidos.put("cpf", "CPF already registered in the database.");
        if (clienteRepository.existsByEmail(email))
            camposInvalidos.put("e-mail", "E-mail already registered in the database.");
        if (!camposInvalidos.isEmpty())
            throw new NegocioException("One or more fields are invalid.", camposInvalidos);
    }


}
