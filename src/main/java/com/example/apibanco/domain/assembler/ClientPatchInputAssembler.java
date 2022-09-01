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
            ClientAddress newAddress = clientPatchInput.getAddress();

            if (newAddress.getStreet() != null)
                client.getAddress().setStreet(newAddress.getStreet());
            if (newAddress.getDistrict() != null)
                client.getAddress().setDistrict(newAddress.getDistrict());
            if (newAddress.getCEP() != null)
                client.getAddress().setCEP(newAddress.getCEP());
            if (newAddress.getNumber() != null)
                client.getAddress().setNumber(newAddress.getNumber());
        }

        return client;
    }
}
