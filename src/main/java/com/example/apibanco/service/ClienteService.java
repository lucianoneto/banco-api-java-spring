package com.example.apibanco.service;

import com.example.apibanco.model.Cliente;
import com.example.apibanco.model.ClienteEndereco;
import com.example.apibanco.model.Conta;
import com.example.apibanco.model.Gerente;
import com.example.apibanco.repository.ClienteEnderecoRepository;
import com.example.apibanco.repository.ClienteRepository;
import com.example.apibanco.repository.GerenteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
@AllArgsConstructor
public class ClienteService {

    private ClienteRepository clienteRepository;
    private ContaService contaService;

    private ClienteEnderecoService clienteEnderecoService;
    private GerenteRepository gerenteRepository;

    @Transactional
    public Cliente salvarCliente(Cliente cliente, Long gerente_id, ClienteEndereco clienteEndereco) {
        if (cliente.getCPF() != null && cliente.getNome() != null && cliente.getTelefone() != null && cliente.getTipoConta() != null) {
            contaService.salvarConta(new Conta(), cliente);
            Gerente gerente = gerenteRepository.getById(gerente_id);
            cliente.setGerente(gerente);
            clienteEnderecoService.salvarEnderecoCliente(clienteEndereco, cliente);
            return clienteRepository.save(cliente);
        }
        return null;
    }
    public List<Cliente> mostrarClientes() {
        return clienteRepository.findAll();
    }


}
