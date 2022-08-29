package com.example.apibanco.domain.assembler;

import com.example.apibanco.api.model.ClientPatchInput;
import com.example.apibanco.domain.model.Client;
import com.example.apibanco.domain.model.ClientAddress;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class ClientPatchInputAssembler {

    public Client merge(ClientPatchInput clientPatchInput, Client client) {

        if (clientPatchInput.getEmail() != null) {
            client.setEmail(clientPatchInput.getEmail());
        }
        if (clientPatchInput.getPhone() != null) {
            client.setPhone(clientPatchInput.getPhone());
        }

        if (Objects.nonNull(clientPatchInput.getAddress())) {
            ClientAddress novoEndereco = clientPatchInput.getAddress();

            if (novoEndereco.getStreet() != null)
                client.getAddress().setStreet(novoEndereco.getStreet());
            if (novoEndereco.getDistrict() != null)
                client.getAddress().setDistrict(novoEndereco.getDistrict());
            if (novoEndereco.getCEP() != null)
                client.getAddress().setCEP(novoEndereco.getCEP());
            if (novoEndereco.getNumber() != null)
                client.getAddress().setNumber(novoEndereco.getNumber());
        }

        return client;
    }
}
