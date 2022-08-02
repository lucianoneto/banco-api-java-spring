package com.example.apibanco.service;

import com.example.apibanco.model.Cliente;
import com.example.apibanco.model.ClienteEndereco;
import com.example.apibanco.repository.ClienteEnderecoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class ClienteEnderecoService {

    private ClienteEnderecoRepository clienteEnderecoRepository;

    @Transactional
    public ClienteEndereco salvarEnderecoCliente (ClienteEndereco clienteEndereco, Cliente cliente){
        if (clienteEndereco.getCEP() != null
                && clienteEndereco.getLogradouro() != null
                && clienteEndereco.getNumero() != null
                && clienteEndereco.getSetor() != null)
        {
                clienteEndereco.setCliente(cliente);
            return clienteEnderecoRepository.save(clienteEndereco);
        }
        return null;
    }
}
