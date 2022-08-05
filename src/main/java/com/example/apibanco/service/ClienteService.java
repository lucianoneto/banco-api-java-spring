package com.example.apibanco.service;

import com.example.apibanco.model.Cliente;
import com.example.apibanco.model.Conta;
import com.example.apibanco.model.input.ClienteInput;
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
    public Cliente salvarCliente(ClienteInput clienteInput, Long gerente_id) {
//        if (clienteInput.getCliente().getCPF() != null && clienteInput.getCliente().getNome() != null && clienteInput.getCliente().getTelefone() != null && clienteInput.getCliente().getTipoConta() != null) {
            contaService.salvarConta(new Conta(), clienteInput);
            clienteInput.getCliente().setGerente(gerenteRepository.getById(gerente_id));
            clienteEnderecoService.salvarEnderecoCliente(clienteInput);
            return clienteRepository.save(clienteInput.getCliente());
//        }
//        return null;
    }

    public List<Cliente> mostrarClientes() {
        return clienteRepository.findAll();
    }


}
