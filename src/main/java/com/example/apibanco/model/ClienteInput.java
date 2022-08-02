package com.example.apibanco.model;

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
