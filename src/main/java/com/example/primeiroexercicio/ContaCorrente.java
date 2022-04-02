package com.example.primeiroexercicio;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;


@Getter
@Setter
public class ContaCorrente {
    private float saldo = 0;
    private int id = 0;
    private static int count = 0;
    Banco banco;
    Cliente cliente;

    public void informacoesConta(int id){
        System.out.println(banco.contaCorrentes.get(id).getSaldo());
    }


    public void deposito(float valor, int id){
        banco.contaCorrentes.get(id).setSaldo(banco.contaCorrentes.get(id).getSaldo() + valor);
        System.out.println("Valor R$" +valor+ " depositado com sucesso!");
        System.out.println("Saldo atual: R$" +banco.contaCorrentes.get(id).getSaldo());
    }

    public void saque(float valor, int id){
        banco.contaCorrentes.get(id).setSaldo(banco.contaCorrentes.get(id).getSaldo() - valor);
        System.out.println("Valor R$" +valor+ " sacado com sucesso!");
        System.out.println("Saldo atual: R$" +banco.contaCorrentes.get(id).getSaldo());
    }

    public void transferencia(float valor, int idTransfere,int idRecebe){
        int aux = 1;
        for(int i = 0; i < banco.contaCorrentes.size(); i++){
            if(idRecebe == banco.contaCorrentes.get(i).getId()){
                aux = 1;
            }
        }
        if (aux == 1){
            banco.contaCorrentes.get(idRecebe).setSaldo(valor + banco.contaCorrentes.get(idRecebe).getSaldo());
            banco.contaCorrentes.get(idTransfere).setSaldo(banco.contaCorrentes.get(idTransfere).getSaldo() - valor);
            System.out.println("Transferência de R$ " +valor+ " realizada com sucesso");
        }
        else{
            System.out.println("Uma conta com este número de ID não existe em nosso Banco de Dados");
        }
    }
    public ContaCorrente(float saldo, Cliente cliente, Banco banco) {
        this.banco = banco;
        this.saldo = saldo;
        this.cliente = cliente;
        this.id = ++count;
    }

}
