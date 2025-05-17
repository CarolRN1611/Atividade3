package org.example.entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class ReservaTest {
    Passageiro passageiro;
    Aviao aviao;
    Voo voo;
    Reserva reserva;
    LocalDateTime dataHora;

    @BeforeEach
    public void setUp() {
        passageiro = new Passageiro(1, "Ana", "52998224725", "ana@email.com");
        aviao = new Aviao(1, "Boeing 737", 2, "Boeing");
        dataHora = LocalDateTime.now();
        voo = new Voo(1, "São Paulo", "Rio de Janeiro", dataHora, aviao);
        reserva = new Reserva(1, passageiro, voo, dataHora);
    }

    @Test
    public void criarReservaValida() {
        Assertions.assertEquals(1, reserva.getId());
        Assertions.assertEquals(passageiro, reserva.getPassageiro());
        Assertions.assertEquals(voo, reserva.getVoo());
        Assertions.assertEquals(dataHora, reserva.getDataReserva());
    }

    @Test
    public void toStringDeReserva() {
        String resultado = reserva.toString();

        Assertions.assertTrue(resultado.contains("id=1"));
        Assertions.assertTrue(resultado.contains("passageiro=Passageiro{id=1, nome='Ana'"));
        Assertions.assertTrue(resultado.contains("voo=Voo{id=1, origem='São Paulo'"));
        Assertions.assertTrue(resultado.contains("destino='Rio de Janeiro'"));
    }
}
