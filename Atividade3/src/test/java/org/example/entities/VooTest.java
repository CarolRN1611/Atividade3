package org.example.entities;

import org.example.repositories.AviaoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class VooTest {
    Voo voo;
    Aviao aviao;
    LocalDateTime dataHora;


    @BeforeEach
    public void setUp() {
        AviaoRepository aviaoRepository = new AviaoRepository();
        aviao = new Aviao(1, "Boeing 737", 200, "Boeing");
        aviaoRepository.salvar(aviao);
        dataHora = LocalDateTime.now();
        voo = new Voo(1, "São Paulo", "Rio de Janeiro", dataHora, aviao);
    }

    //Cadastrar voo com origem, destino, data e avião válido → Deve ser salvo corretamente
    @Test
    public void criarVooComDadosValidos() {

        Assertions.assertEquals(1, voo.getId());
        Assertions.assertEquals("São Paulo", voo.getOrigem());
        Assertions.assertEquals("Rio de Janeiro", voo.getDestino());
        Assertions.assertEquals(dataHora, voo.getDataHora());
        Assertions.assertEquals(aviao, voo.getAviao());
    }

    //Testar retorno no ToSring
    @Test
    public void testarToStringDeVoo() {
        Aviao aviao = new Aviao(1, "Boeing 737", 200, "Boeing");
        LocalDateTime dataHora = LocalDateTime.of(2025, 5, 16, 12, 0);
        Voo voo = new Voo(1, "São Paulo", "Rio de Janeiro", dataHora, aviao);

        String resultado = voo.toString();

        Assertions.assertTrue(resultado.contains("id=1"));
        Assertions.assertTrue(resultado.contains("origem='São Paulo'"));
        Assertions.assertTrue(resultado.contains("destino='Rio de Janeiro'"));
        Assertions.assertTrue(resultado.contains("dataHora=2025-05-16T12:00"));
        Assertions.assertTrue(resultado.contains("aviao=Aviao{id=1, modelo='Boeing 737', capacidade=200, fabricante='Boeing'}"));
    }

}
