package com.example.primeiroexercicio;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Cliente {
    private String nome;
    private long CPF;
    private String telefone;
    private String endereco;
    private int id;

    @Override
    public String toString() {
        return "Cliente{" +
                "nome='" + nome + '\'' +
                ", CPF=" + CPF +
                ", telefone='" + telefone + '\'' +
                ", endereco='" + endereco + '\'' +
                ", id=" + id +
                '}';
    }
}


