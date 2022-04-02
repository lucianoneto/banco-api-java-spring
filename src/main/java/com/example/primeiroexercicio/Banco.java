package com.example.primeiroexercicio;

import java.util.ArrayList;

public class Banco {
    ArrayList <ContaCorrente> contaCorrentes = new ArrayList<>();

    public void abreConta(Cliente cliente, Banco banco){
        ContaCorrente contaCorrente = new ContaCorrente(0,cliente,banco);
        cliente.setId(contaCorrente.getId());
        contaCorrente.setSaldo(50);
        contaCorrentes.add(contaCorrente);
        System.out.println("Conta criada com sucesso!");
    }
}
