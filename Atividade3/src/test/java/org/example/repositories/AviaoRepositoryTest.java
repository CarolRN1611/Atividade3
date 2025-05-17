package org.example.repositories;

import org.example.entities.Aviao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class AviaoRepositoryTest {
    AviaoRepository aviaoRepository;
    Aviao aviao;

    @BeforeEach
    public void setUp() {
        aviaoRepository = new AviaoRepository();
        aviao = new Aviao(1, "Boeing 737", 200, "Boeing");
    }


    @Test
    public void salvarAviaoComSucesso() {
        aviaoRepository.salvar(aviao);

        List<Aviao> avioes = aviaoRepository.buscarTodos();
        Assertions.assertEquals(1, avioes.size());
        Aviao aviaoInDb = aviaoRepository.buscarPorId(1);

        Assertions.assertEquals("Boeing 737",aviaoInDb.getModelo());
        Assertions.assertEquals(200,aviaoInDb.getCapacidade());
        Assertions.assertEquals("Boeing",aviaoInDb.getFabricante());
    }

    @Test
    public void buscarAviaoPorIdExistente() {
        aviaoRepository.salvar(aviao);

        Aviao buscado = aviaoRepository.buscarPorId(1);
        Assertions.assertEquals(aviao, buscado);
    }

    @Test
    public void buscarAviaoPorIdNaoExistente() {
        AviaoRepository aviaoRepository = new AviaoRepository();
        Assertions.assertThrows(RuntimeException.class, () -> aviaoRepository.buscarPorId(99));
    }

    @Test
    public void listarTodosAvioes() {
        aviaoRepository.salvar(new Aviao(1, "Boeing 737", 200, "Boeing"));
        aviaoRepository.salvar(new Aviao(2, "Airbus A320", 180, "Airbus"));

        List<Aviao> avioes = aviaoRepository.buscarTodos();
        Assertions.assertEquals(2, avioes.size());
    }
}
