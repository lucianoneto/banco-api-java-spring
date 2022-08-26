package com.example.apibanco.domain.assembler;

import com.example.apibanco.api.model.ClientePatchInput;
import com.example.apibanco.domain.model.Cliente;
import com.example.apibanco.domain.model.ClienteEndereco;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class ClientePatchInputAssembler {

    public Cliente merge(ClientePatchInput clientePatchInput, Cliente cliente) {

        if (clientePatchInput.getEmail() != null) {
            cliente.setEmail(clientePatchInput.getEmail());
        }
        if (clientePatchInput.getTelefone() != null) {
            cliente.setTelefone(clientePatchInput.getTelefone());
        }

        if (Objects.nonNull(clientePatchInput.getEndereco())) {
            ClienteEndereco novoEndereco = clientePatchInput.getEndereco();

            if (novoEndereco.getLogradouro() != null)
                cliente.getEndereco().setLogradouro(novoEndereco.getLogradouro());
            if (novoEndereco.getSetor() != null)
                cliente.getEndereco().setSetor(novoEndereco.getSetor());
            if (novoEndereco.getCEP() != null)
                cliente.getEndereco().setCEP(novoEndereco.getCEP());
            if (novoEndereco.getNumero() != null)
                cliente.getEndereco().setNumero(novoEndereco.getNumero());
        }

        return cliente;
    }
}
