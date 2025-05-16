import org.example.application.ReservaApplication;
import org.example.entities.Aviao;
import org.example.entities.Passageiro;
import org.example.entities.Reserva;
import org.example.entities.Voo;
import org.example.repositories.ReservaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

public class ReservaTest {
    private Reserva reservaValido;
    private ReservaRepository reservaRepository;
    private ReservaApplication reservaApplication;
    private LocalDateTime dataHora;
    private Voo voo;
    private Aviao aviao;

    @BeforeEach
    public void setUp() {
        reservaRepository = new ReservaRepository();
        reservaApplication = new ReservaApplication(reservaRepository);
        aviao = new Aviao(1,"EMB 190",2,"Embraer");
        Passageiro passageiro = new Passageiro(1, "João Silva", "123.456.789-09", "joao@email.com");
        dataHora = LocalDateTime.of(2025, 5, 16, 12, 0);
        voo = new Voo(1,"Bahia","Rio de Janeiro",dataHora, aviao);
        reservaValido = new Reserva(1,passageiro,voo,dataHora);

    }

    @Test
    public void cadastrarReservaVagasDisponiveis(){
        Assertions.assertTrue(reservaApplication.salvar(reservaValido));
    }
    @Test
    public void cadastrarReservaVagasIndisponiveis(){
        Passageiro passageiro2 = new Passageiro(2, "Mariana Silva", "931.073.680-16", "Mariana@email.com");
        Reserva reserva = new Reserva(2,passageiro2,voo,dataHora);
        Passageiro passageiro3 = new Passageiro(3, "Maria Oliveira", "987.654.321-00", "maria.oliveira@gmail.com");
        Reserva reserva2 = new Reserva(3,passageiro3,voo,dataHora);
        reservaApplication.salvar(reserva2);
        Assertions.assertFalse(reservaApplication.salvar(reserva));
    }

    @Test
    public void listarReservasApos2Registros(){

        Passageiro passageiro2 = new Passageiro(2, "Maria Oliveira", "987.654.321-00", "maria.oliveira@example.com");
        Reserva reserva2 = new Reserva(2, passageiro2, voo, dataHora);

        reservaApplication.salvar(reservaValido);
        reservaApplication.salvar(reserva2);
        List<Reserva> reservas = reservaApplication.buscarTodos();

        Assertions.assertEquals(2,reservas.size());

        Reserva r1 = reservas.get(0);
        Assertions.assertEquals(1, r1.getId());
        Assertions.assertEquals("João Silva", r1.getPassageiro().getNome());
        Assertions.assertEquals("Bahia", r1.getVoo().getOrigem());
        Assertions.assertEquals("Rio de Janeiro", r1.getVoo().getDestino());
        Assertions.assertEquals(aviao.getModelo(), r1.getVoo().getAviao().getModelo());
        Assertions.assertEquals(dataHora, r1.getDataReserva());


        Reserva r2 = reservas.get(1);
        Assertions.assertEquals(2, r2.getId());
        Assertions.assertEquals("Maria Oliveira", r2.getPassageiro().getNome());
        Assertions.assertEquals("Bahia", r2.getVoo().getOrigem());
        Assertions.assertEquals("Rio de Janeiro", r2.getVoo().getDestino());
        Assertions.assertEquals(aviao.getModelo(), r2.getVoo().getAviao().getModelo());
        Assertions.assertEquals(dataHora, r2.getDataReserva());

    }
}
