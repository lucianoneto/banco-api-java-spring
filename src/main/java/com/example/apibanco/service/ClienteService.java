package com.example.apibanco.service;

import com.example.apibanco.model.Cliente;
import com.example.apibanco.model.ClienteEndereco;
import com.example.apibanco.model.input.ClienteInput;
import com.example.apibanco.repository.ClienteEnderecoRepository;
import com.example.apibanco.repository.ClienteRepository;
import com.example.apibanco.repository.GerenteRepository;
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
    private GerenteRepository gerenteRepository;
    private ClienteEnderecoRepository clienteEnderecoRepository;
    private GerenteService gerenteService;
    private ModelMapper modelMapper;

    @Transactional
    public Cliente salvarCliente(ClienteInput clienteInput, Long gerente_id) {
        HashMap<String, String> camposInvalidos = new HashMap<>();

        Cliente cliente = modelMapper.map(clienteInput, Cliente.class);

        gerenteService.camposInvalidos(camposInvalidos, cliente.getCpf(), cliente.getEmail());

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
}
