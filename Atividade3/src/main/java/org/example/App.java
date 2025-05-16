package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {

    private static List<Passageiro> passageiros = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void salvar(Passageiro passageiro) {
        if(validarPassageiro(passageiro)){
            passageiros.add(passageiro);
            System.out.println("Passageiro Cadastrado com Sucesso!");
        }else {
            System.out.println("Erro!Dados Inválidos");
        }
    }

    public static boolean validarPassageiro(Passageiro passageiro){
        if (!passageiro.validarCPF(passageiro.getCpf())) {
            return false;
        }
        if (!passageiro.validarEmail(passageiro.getEmail())) {
            return false;
        }
        return true;
    }

    public static void adicionarPassageiro() {
        System.out.println("Digite o id do Passageiro:");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Digite o nome do Passageiro:");
        String nome = scanner.nextLine();

        System.out.println("Digite o CPF do Passageiro:");
        String cpf = scanner.nextLine();

        System.out.println("Digite o Email do Passageiro:");
        String email = scanner.nextLine();
        Passageiro passageiro = new Passageiro(id,nome,cpf,email);
        salvar(passageiro);
    }

    public static List<Passageiro> listarTodosPassageiros() {
        if (passageiros.isEmpty()) {
            System.out.println("Nenhum passageiro cadastrado.");
        } else {
            for (Passageiro p : passageiros) {
                System.out.println(p);
            }
        }
        return passageiros;
    }

    public static int menu() {
        System.out.println("\n1 - Cadastrar passageiro");
        System.out.println("2 - Listar passageiros");
        System.out.println("3 - Sair");
        System.out.print("Escolha uma opção: ");
        return scanner.nextInt();
    }

    public static void main(String[] args) {
        int opcao;
        do {
            opcao = menu();
            switch (opcao) {
                case 1:
                    adicionarPassageiro();
                    break;
                case 2:
                    listarTodosPassageiros();
                    break;
                case 3:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 3);
    }
}
