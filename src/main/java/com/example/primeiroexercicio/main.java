package com.example.primeiroexercicio;

import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Cliente cliente = new Cliente();
        Banco banco = new Banco();


        Scanner teclado = new Scanner(System.in);
        System.out.print("Digite o seu nome: ");
        cliente.setNome(teclado.nextLine());
        System.out.print("Digite o seu endereço: ");
        cliente.setEndereco(teclado.nextLine());
        System.out.print("Digite o seu telefone: ");
        cliente.setTelefone(teclado.nextLine());
        System.out.print("Digite o seu CPF: ");
        cliente.setCPF(teclado.nextLong());

        banco.abreConta(cliente, banco);

        System.out.print("Digite o seu nome: ");
        teclado.nextLine();
        cliente.setNome(teclado.nextLine());
        System.out.print("Digite o seu endereço: ");
        cliente.setEndereco(teclado.nextLine());
        System.out.print("Digite o seu telefone: ");
        cliente.setTelefone(teclado.nextLine());
        System.out.print("Digite o seu CPF: ");
        cliente.setCPF(teclado.nextLong());

        banco.abreConta(cliente, banco);

        System.out.println(banco.contaCorrentes.size());
        banco.contaCorrentes.get(0).informacoesConta();
        banco.contaCorrentes.get(0).deposito(500);
        banco.contaCorrentes.get(0).transferencia(300,1);
        banco.contaCorrentes.get(0).printaSaldo();
        banco.contaCorrentes.get(1).printaSaldo();


    }
}
