package com.example.apibanco.service;

import com.example.apibanco.model.ClienteEndereco;
import com.example.apibanco.model.input.ClienteInput;
import com.example.apibanco.repository.ClienteEnderecoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class ClienteEnderecoService {

    private ClienteEnderecoRepository clienteEnderecoRepository;

    @Transactional
    public ClienteEndereco salvarEnderecoCliente(ClienteInput clienteInput) {
        if (clienteInput.getClienteEndereco().getCEP() != null
                && clienteInput.getClienteEndereco().getLogradouro() != null
                && clienteInput.getClienteEndereco().getNumero() != null
                && clienteInput.getClienteEndereco().getSetor() != null) {
            clienteInput.getClienteEndereco().setCliente(clienteInput.getCliente());
            return clienteEnderecoRepository.save(clienteInput.getClienteEndereco());
        }
        return null;
    }
}
