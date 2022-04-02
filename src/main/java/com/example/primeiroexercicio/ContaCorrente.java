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
        int verificaExiste = 0;
        for(int i = 0; i < banco.contaCorrentes.size(); i++){
            if(id == banco.contaCorrentes.get(i).getId()){
                verificaExiste = 1;
            }
        }
        if(verificaExiste == 1){
            System.out.println(banco.contaCorrentes.get(id).getSaldo());
        }
        else{
            System.out.println("Uma conta com este número de ID não existe em nosso Banco de Dados \n");
        }

    }

    public void deposito(float valor, int id){
        int verificaExiste = 0;
        for(int i = 0; i < banco.contaCorrentes.size(); i++){
            if(id == banco.contaCorrentes.get(i).getId()){
                verificaExiste = 1;
            }
        }
        if(verificaExiste == 1){
            if(banco.contaCorrentes.get(id).getSaldo() >= valor){
                banco.contaCorrentes.get(id).setSaldo(banco.contaCorrentes.get(id).getSaldo() + valor);
                System.out.println("Valor R$" +valor+ " depositado com sucesso!");
                System.out.println("Saldo atual: R$" +banco.contaCorrentes.get(id).getSaldo() + "\n");
            }
            else{
                System.out.println("Não possui dinheiro suficiente para realizar o depósito! \n");
            }
        }
        else{
            System.out.println("Uma conta com este número de ID não existe em nosso Banco de Dados \n");
        }

    }

    public void saque(float valor, int id){
        int verificaExiste = 0;
        for(int i = 0; i < banco.contaCorrentes.size(); i++){
            if(id == banco.contaCorrentes.get(i).getId()){
                verificaExiste = 1;
            }
        }
        if(verificaExiste == 1){
            if(banco.contaCorrentes.get(id).getSaldo() >= valor){
                banco.contaCorrentes.get(id).setSaldo(banco.contaCorrentes.get(id).getSaldo() - valor);
                System.out.println("Valor R$" +valor+ " sacado com sucesso!");
                System.out.println("Saldo atual: R$" +banco.contaCorrentes.get(id).getSaldo() + "\n");
            }
            else{
                System.out.println("Não possui dinheiro suficiente para realizar o saque! \n");
            }
        }
        else{
            System.out.println("Uma conta com este número de ID não existe em nosso Banco de Dados \n");
        }
    }

    public void transferencia(float valor, int idTransfere,int idRecebe){
        int verificaRecebe = 0, verificaTransfere = 0;
        for(int i = 0; i < banco.contaCorrentes.size(); i++){
            if(idRecebe == banco.contaCorrentes.get(i).getId()){
                verificaRecebe = 1;
            }
            if(idTransfere == banco.contaCorrentes.get(i).getId()){
                verificaTransfere = 1;
            }
        }
        if (verificaRecebe == 1 && verificaTransfere == 1){
            if(banco.contaCorrentes.get(idTransfere).getSaldo() >= valor){
                banco.contaCorrentes.get(idRecebe).setSaldo(valor + banco.contaCorrentes.get(idRecebe).getSaldo());
                banco.contaCorrentes.get(idTransfere).setSaldo(banco.contaCorrentes.get(idTransfere).getSaldo() - valor);
                System.out.println("Transferência de R$ " +valor+ " realizada com sucesso \n");
            }
            else{
                System.out.println("Não possui dinheiro suficiente para realizar a transferência! \n");
            }

        }
        else{
            System.out.println("Uma conta com este número de ID não existe em nosso Banco de Dados \n");
        }
    }
    public ContaCorrente(float saldo, Cliente cliente, Banco banco) {
        this.banco = banco;
        this.saldo = saldo;
        this.cliente = cliente;
        this.id = ++count;
    }

}
