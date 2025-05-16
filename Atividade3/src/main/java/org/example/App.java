package org.example;

import org.example.application.AviaoApplication;
import org.example.application.PassageiroApplication;
import org.example.application.ReservaApplication;
import org.example.application.VooApplication;
import org.example.entities.Aviao;
import org.example.entities.Passageiro;
import org.example.entities.Reserva;
import org.example.entities.Voo;
import org.example.repositories.AviaoRepository;
import org.example.repositories.PassageiroRepository;
import org.example.repositories.ReservaRepository;
import org.example.repositories.VooRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    private static PassageiroRepository passageiroRepository;
    private static PassageiroApplication passageiroApplication;
    private static AviaoRepository aviaoRepository;
    private static AviaoApplication aviaoApplication;
    private static VooRepository vooRepository;
    private static VooApplication vooApplication;
    private static ReservaRepository reservaRepository;
    private static ReservaApplication reservaApplication;
    private static Scanner scanner;

    public static void resolveDependencies() {
        passageiroRepository = new PassageiroRepository();
        passageiroApplication = new PassageiroApplication(passageiroRepository);
        aviaoRepository = new AviaoRepository();
        aviaoApplication = new AviaoApplication(aviaoRepository);
        vooRepository = new VooRepository();
        vooApplication = new VooApplication(vooRepository);
        reservaRepository = new ReservaRepository();
        reservaApplication = new ReservaApplication(reservaRepository);
        scanner = new Scanner(System.in);
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

        Passageiro passageiro = new Passageiro(id, nome, cpf, email);

        boolean sucesso = passageiroApplication.salvar(passageiro);
        if (sucesso) {
            System.out.println("Passageiro cadastrado com sucesso!");
        }
    }


    public static void listarTodosPassageiros() {
        List<Passageiro> passageiros = passageiroApplication.buscarTodos();
        if (passageiros.isEmpty()) {
            System.out.println("Nenhum Passageiro cadastrado.");
        } else {
            for (Passageiro p : passageiros) {
                System.out.println(p);
            }
        }
    }

    public static void cadastrarAviao() {
        System.out.println("Digite o id do Aviao:");
        int id = scanner.nextInt();
        scanner.nextLine(); // consome o \n pendente

        System.out.println("Digite o Modelo do Aviao:");
        String modelo = scanner.nextLine();

        System.out.println("Digite do Capacidade do Aviao:");
        int capacidade = scanner.nextInt();
        scanner.nextLine(); // consome o \n pendente

        System.out.println("Digite o Fabricante do Aviao:");
        String fabricante = scanner.nextLine();

        Aviao aviao = new Aviao(id, modelo, capacidade, fabricante);

        boolean sucesso = aviaoApplication.salvar(aviao);
        if (sucesso) {
            System.out.println("Avião cadastrado com sucesso!");
        }
    }
    public static void listarTodosAvioes() {
        List<Aviao> avioes = aviaoApplication.buscarTodos();
        if (avioes.isEmpty()) {
            System.out.println("Nenhum Avião Cadastrado.");
        } else {
            for (Aviao a : avioes) {
                System.out.println(a);
            }
        }
    }

    public static void cadastrarVoo() {
        System.out.println("Digite o id do Voo:");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Digite o Origem do Voo:");
        String origem = scanner.nextLine();

        System.out.println("Digite o Destino do Voo:");
        String destino = scanner.nextLine();

        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        System.out.println("Digite a Data e Hora do Voo (ex: 16/05/2025 14:30):");
        String entrada = scanner.nextLine();

        LocalDateTime datahora = null;
        try {
            datahora = LocalDateTime.parse(entrada, formatter);
            System.out.println("Data e hora registrada: " + datahora);
        } catch (DateTimeParseException e) {
            System.out.println("Formato inválido. Use o formato dd/MM/yyyy HH:mm.");
            return;
        }

        System.out.println("Digite o ID do Avião:");
        int aviao = scanner.nextInt();
        scanner.nextLine();

        Aviao aviaoObj = aviaoApplication.buscarPorId(aviao);
        Voo voo = new Voo(id, origem, destino, datahora, aviaoObj);

        if (vooApplication.salvar(voo)) {
            System.out.println("Voo cadastrado com sucesso!");
        }
    }


    public static void listarTodosVoos() {
        List<Voo> voos = vooApplication.buscarTodos();
        if (voos.isEmpty()) {
            System.out.println("Nenhum Voo Cadastrado.");
        } else {
            for (Voo v : voos) {
                System.out.println(v);
            }
        }
    }

    public static void reservarPassagem() {
        System.out.println("Digite o Id da Reserva:");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Digite o Id do Passageiro:");
        String passageiro = scanner.nextLine();

        System.out.println("Digite do Id do Voo:");
        int voo = scanner.nextInt();

        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        System.out.println("Digite a Data da Reserva (ex: 16/05/2025 14:30):");
        String entrada = scanner.nextLine();

        LocalDateTime datahora = null;
        try {
            datahora = LocalDateTime.parse(entrada, formatter);
            System.out.println("Data e hora registrada: " + datahora);
        } catch (DateTimeParseException e) {
            System.out.println("Formato inválido. Use o formato dd/MM/yyyy HH:mm.");
            return;
        }

        Passageiro passageiroObj = passageiroApplication.buscarPorId(Integer.parseInt(passageiro));
        Voo vooObj = vooApplication.buscarPorId(voo);

        Reserva reserva = new Reserva(id, passageiroObj,vooObj,datahora);

        boolean sucesso = reservaApplication.salvar(reserva);
        if (sucesso) {
            System.out.println("Reserva realizada com sucesso!");
        } else {
            System.out.println("Não há vagas disponíveis neste voo.");
        }

    }


    public static void listarTodasReservas() {
        List<Reserva> reservas = reservaApplication.buscarTodos();
        if (reservas.isEmpty()) {
            System.out.println("Nenhum Reserva Cadastrado.");
        } else {
            for (Reserva a : reservas) {
                System.out.println(a);
            }
        }
    }


    public static int menu() {
        System.out.println("\n--- MENU ---");
        System.out.println("1 - Cadastrar passageiro");
        System.out.println("2 - Listar passageiros");
        System.out.println("3 - Cadastrar avião");
        System.out.println("4 - Listar aviões");
        System.out.println("5 - Cadastrar voo");
        System.out.println("6 - Listar voos");
        System.out.println("7 - Reservar passagem");
        System.out.println("8 - Listar reservas");
        System.out.println("9 - Sair");
        System.out.print("Escolha uma opção: ");
        return scanner.nextInt();
    }


    public static void main(String[] args) {
        resolveDependencies();

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
                    cadastrarAviao();
                    break;
                case 4:
                    listarTodosAvioes();
                    break;
                case 5:
                    cadastrarVoo();
                    break;
                case 6:
                    listarTodosVoos();
                    break;
                case 7:
                    reservarPassagem();
                    break;
                case 8:
                    listarTodasReservas();
                    break;
                case 9:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 9);
    }

}
