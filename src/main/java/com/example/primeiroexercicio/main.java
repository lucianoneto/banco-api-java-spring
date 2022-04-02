package com.example.primeiroexercicio;

import java.io.IOException;
import java.util.Scanner;

public class main {
    public static void main(String[] args) throws IOException {
        Cliente cliente = new Cliente();
        Banco banco = new Banco();
        String menu = null;
        Scanner teclado = new Scanner(System.in);
        Scanner tecladoMenu = new Scanner(System.in);
        int id = 0;
        float valor;


        System.out.println("B E M  V I N D O S  A O  ****** B A N C O  M U N D I A L ******!");

        do {
            System.out.println("Escolha a baixo o que deseja fazer:");
                System.out.println("1- Criar conta");
                System.out.println("2- Depósito");
                System.out.println("3- Saque");
                System.out.println("4- Transferência");
                System.out.println("5- Saldo");
                System.out.println("6- SAIR");

            menu = tecladoMenu.nextLine();

            switch (menu){
                case "1": {
                    System.out.print("Digite o seu nome: ");
                    teclado.next();
                    cliente.setNome(teclado.nextLine());
                    System.out.print("Digite o seu endereço: ");
                    cliente.setEndereco(teclado.nextLine());
                    System.out.print("Digite o seu telefone: ");
                    cliente.setTelefone(teclado.nextLine());
                    System.out.print("Digite o seu CPF: ");
                    cliente.setCPF(teclado.nextLong());
                    banco.abreConta(cliente, banco);
                    break;
                }
                case "2":{
                    System.out.print("Id da conta: ");
                    id = teclado.nextInt();
                    System.out.print("Valor: ");
                    valor = teclado.nextFloat();
                    banco.contaCorrentes.get(id-1).deposito(valor,id-1);
                    break;
                }
                case "3":{
                    System.out.print("Id da conta: ");
                    id = teclado.nextInt();
                    System.out.print("Valor: ");
                    valor = teclado.nextFloat();
                    banco.contaCorrentes.get(id-1).saque(valor,id-1);
                    break;
                }
                case "4":{
                    System.out.print("Id da conta a transferir: ");
                    id = teclado.nextInt();
                    System.out.print("Id da conta a receber: ");
                    int id2 = teclado.nextInt();
                    System.out.print("Valor: ");
                    valor = teclado.nextFloat();
                    banco.contaCorrentes.get(id-1).transferencia(valor,id-1,id2-1);
                    break;
                }
                case "5":{
                    System.out.print("Id da para ver o saldo: ");
                    id = teclado.nextInt();
                    banco.contaCorrentes.get(id-1).informacoesConta(id-1);
                    break;
                }
                case "6":{
                    System.out.println("Até mais!");
                    menu = "6";
                    break;
                }
                default:{
                    System.out.println("Opção inválida");
                    break;
                }

            }
        }while(menu!="6");


    }
}
