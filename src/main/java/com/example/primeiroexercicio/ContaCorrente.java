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

    public void informacoesConta(){
        System.out.println(cliente.toString());
    }
    public void printaSaldo(){
        System.out.println("Saldo atual: R$" +this.saldo);
    }
    public void deposito(float valor){
        this.saldo+=valor;
        System.out.println("Valor R$" +valor+ " depositado com sucesso");
        System.out.println("Saldo atual: R$" +this.saldo);
    }

    public void saque(float valor){
        this.saldo-=valor;
    }

    public void transferencia(float valor, int id){
        banco.contaCorrentes.get(1).setSaldo(300 + banco.contaCorrentes.get(1).getSaldo());

        //int aux = 1;
        /*for(int i = 0; i < banco.contaCorrentes.size(); i++){
            if(id == banco.contaCorrentes.get(i).getId()){
                aux = 1;
            }
        }
        if (aux == 1){
            banco.contaCorrentes.get(id).setSaldo(valor + banco.contaCorrentes.get(id).getSaldo());
            System.out.println("Transferência de R$ " +valor+ " realizada com sucesso");
        }
        else{
            System.out.println("Uma conta com este número de ID não existe em nosso Banco de Dados");
        }*/
    }
    public ContaCorrente(float saldo, Cliente cliente, Banco banco) {
        this.banco = banco;
        this.saldo = saldo;
        this.cliente = cliente;
        this.id = ++count;
    }
}
