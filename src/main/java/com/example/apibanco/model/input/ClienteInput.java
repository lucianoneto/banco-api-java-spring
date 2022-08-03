package com.example.apibanco.model.input;

import com.example.apibanco.model.Cliente;
import com.example.apibanco.model.ClienteEndereco;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;

@Getter
@Setter
public class ClienteInput {

    @Valid
    private ClienteEndereco clienteEndereco;

    @Valid
    private Cliente cliente;

}
