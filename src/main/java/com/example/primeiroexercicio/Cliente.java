package com.example.primeiroexercicio;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Cliente {
    private String nome;
    private int CPF;
    private String telefone;
    private String endereco;
    private int id;

    public Cliente(String nome, int CPF, String telefone, String endereco) {
        this.nome = nome;
        this.CPF = CPF;
        this.telefone = telefone;
        this.endereco = endereco;
    }

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


