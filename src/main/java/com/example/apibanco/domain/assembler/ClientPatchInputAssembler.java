package com.example.apibanco.domain.assembler;

import com.example.apibanco.api.model.ClientPatchInput;
import com.example.apibanco.domain.model.Client;
import com.example.apibanco.domain.model.ClientAdress;
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

        if (Objects.nonNull(clientPatchInput.getAdress())) {
            ClientAdress novoEndereco = clientPatchInput.getAdress();

            if (novoEndereco.getStreet() != null)
                client.getAdress().setStreet(novoEndereco.getStreet());
            if (novoEndereco.getDistrict() != null)
                client.getAdress().setDistrict(novoEndereco.getDistrict());
            if (novoEndereco.getCEP() != null)
                client.getAdress().setCEP(novoEndereco.getCEP());
            if (novoEndereco.getNumber() != null)
                client.getAdress().setNumber(novoEndereco.getNumber());
        }

        return client;
    }
}
