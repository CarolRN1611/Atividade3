package org.example.application;

import org.example.entities.Aviao;
import org.example.entities.Passageiro;
import org.example.entities.Reserva;
import org.example.entities.Voo;
import org.example.repositories.ReservaRepository;
import org.junit.jupiter.api.*;

import java.time.LocalDateTime;
import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ReservaApplicationTest {
    ReservaRepository reservaRepository;
    ReservaApplication reservaApplication;
    Reserva reserva;
    Passageiro passageiro;
    Aviao aviao;
    Voo voo;
    LocalDateTime dataHora;

    @BeforeEach
    public void setUp() {
        reservaRepository = new ReservaRepository();
        reservaApplication = new ReservaApplication(reservaRepository);

        passageiro = new Passageiro(1, "Ana", "52998224725", "ana@email.com");
        aviao = new Aviao(1, "Boeing 737", 1, "Boeing");
        dataHora = LocalDateTime.now();
        voo = new Voo(1, "São Paulo", "Rio de Janeiro", dataHora, aviao);
        reserva = new Reserva(1, passageiro, voo, dataHora);
    }

    //Criar reserva com vagas disponíveis → Deve ser realizada com sucesso
    @Test
    @Order(1)
    public void salvarReservaComSucesso() {
        boolean resultado = reservaApplication.salvar(reserva);
        Assertions.assertTrue(resultado);
        Assertions.assertEquals(1, reservaRepository.buscarTodos().size());
    }

    //Criar reserva quando todas as vagas estiverem ocupadas → Deve falhar ou lançar exceção
    @Test
    @Order(2)
    public void salvarReservaSemVagasDisponiveis() {
        Passageiro passageiro2 = new Passageiro(2, "Carlos", "52998224725", "carlos@email.com");

        reservaApplication.salvar(reserva);
        boolean resultado = reservaApplication.salvar(new Reserva(2, passageiro2, voo, dataHora));

        Assertions.assertFalse(resultado);
        Assertions.assertEquals(1, reservaApplication.buscarTodos().size());
    }

    //Criar reserva duplicada para o mesmo passageiro e voo → Deve impedir ou notificar (se implementado)
    @Test
    @Order(3)
    public void reservaDuplicadaPassageiroVoo() {
        reservaApplication.salvar(reserva);
        Reserva reserva2 = new Reserva(2, passageiro, voo, dataHora);

        Assertions.assertFalse(reservaApplication.salvar(reserva2));
    }

    //Listar reservas após 2 registros → Deve retornar 2 reservas com passageiro e voo
    @Test
    @Order(4)
    public void listarReservasApos2Registros() {
        voo.getAviao().setCapacidade(2);

        Passageiro passageiro2 = new Passageiro(2, "Maria Oliveira", "98765432100", "maria.oliveira@example.com");
        Reserva reserva2 = new Reserva(2, passageiro2, voo, dataHora.plusMinutes(5));

        reservaApplication.salvar(reserva);
        reservaApplication.salvar(reserva2);

        List<Reserva> reservas = reservaApplication.buscarTodos();

        Assertions.assertEquals(2, reservas.size(), "Deveria haver exatamente 2 reservas registradas.");

        Reserva r1 = reservas.get(0);
        Assertions.assertEquals(1, r1.getId());
        Assertions.assertEquals("Ana", r1.getPassageiro().getNome());
        Assertions.assertEquals("São Paulo", r1.getVoo().getOrigem());
        Assertions.assertEquals("Rio de Janeiro", r1.getVoo().getDestino());
        Assertions.assertEquals(aviao.getModelo(), r1.getVoo().getAviao().getModelo());
        Assertions.assertEquals(dataHora, r1.getDataReserva());

        Reserva r2 = reservas.get(1);
        Assertions.assertEquals(2, r2.getId());
        Assertions.assertEquals("Maria Oliveira", r2.getPassageiro().getNome());
        Assertions.assertEquals("São Paulo", r2.getVoo().getOrigem());
        Assertions.assertEquals("Rio de Janeiro", r2.getVoo().getDestino());
        Assertions.assertEquals(aviao.getModelo(), r2.getVoo().getAviao().getModelo());
        Assertions.assertEquals(dataHora.plusMinutes(5), r2.getDataReserva());
    }
}
