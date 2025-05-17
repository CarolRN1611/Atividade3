package org.example.repositories;

import org.example.application.ReservaApplication;
import org.example.entities.Aviao;
import org.example.entities.Passageiro;
import org.example.entities.Reserva;
import org.example.entities.Voo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

public class ReservaRepositoryTest {
    ReservaRepository reservaRepository;
    Reserva reserva;
    Passageiro passageiro;
    Aviao aviao;
    Voo voo;
    LocalDateTime dataHora;

    @BeforeEach
    public void setUp() {
        reservaRepository = new ReservaRepository();
        Passageiro passageiro = new Passageiro(1, "Ana", "52998224725", "ana@email.com");
        aviao = new Aviao(1, "Boeing 737", 2, "Boeing");
        dataHora = LocalDateTime.now();
        voo = new Voo(1, "São Paulo", "Rio de Janeiro",dataHora, aviao);
        reserva = new Reserva(1, passageiro,voo,dataHora);
    }

    //Criar reserva com vagas disponíveis → Deve ser realizada com sucesso
    @Test
    public void salvarReservaNoRepositório() {
        reservaRepository.salvar(reserva);

        List<Reserva> reservas = reservaRepository.buscarTodos();
        Assertions.assertEquals(1, reservas.size());
        Assertions.assertEquals(reserva, reservas.get(0));
    }


    //Listar reservas após 2 registros → Deve retornar 2 reservas com passageiro e voo
    @Test
    public void listarTodasReservas() {
        Passageiro passageiro2 = new Passageiro(2, "Carlos", "12345678901", "carlos@email.com");

        reservaRepository.salvar(new Reserva(1, passageiro,voo,dataHora));
        reservaRepository.salvar(new Reserva(2, passageiro2, voo,dataHora));

        List<Reserva> reservas = reservaRepository.buscarTodos();
        Assertions.assertEquals(2, reservas.size());
    }
}