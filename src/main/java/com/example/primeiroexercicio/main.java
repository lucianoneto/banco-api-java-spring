package com.example.primeiroexercicio;

import java.util.Scanner;

public class main {
    public static void main(String[] args) {


        Banco banco = new Banco();
        Cliente cliente = new Cliente("Luciano Ferreira de Carvalho Neto", 708126851, "64 9 9916-3574",
                "Rua Castro Alves, 94, Setor Iracema");

        banco.abreConta(cliente, banco);
        banco.contaCorrentes.get(0).informacoesConta();
        banco.contaCorrentes.get(0).deposito(500);
        //banco.contaCorrentes.get(0).transferencia(300,1);
        //banco.contaCorrentes.get(1).setSaldo(300 + banco.contaCorrentes.get(1).getSaldo());
        //banco.contaCorrentes.get(1).printaSaldo();


    }
}
