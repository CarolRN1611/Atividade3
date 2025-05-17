package org.example.repositories;

import org.example.entities.Aviao;
import org.example.entities.Voo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

public class VooRepositoryTest {
    VooRepository vooRepository;
    Voo voo;
    Aviao aviao;

    @BeforeEach
    public void setUp() {
        vooRepository = new VooRepository();
        aviao = new Aviao(1, "Boeing 737", 200, "Boeing");
        voo = new Voo(1, "São Paulo", "Rio de Janeiro", LocalDateTime.now(), aviao);
    }

    //Cadastrar voo com origem, destino, data e avião válido → Deve ser salvo corretamente
    @Test
    public void salvarVooNoRepositório() {
        vooRepository.salvar(voo);

        List<Voo> voos = vooRepository.buscarTodos();
        Assertions.assertEquals(1, voos.size());
        Voo vooInDb = vooRepository.buscarPorId(1);
        Assertions.assertEquals(voo, vooInDb);
    }

    @Test
    public void buscarVooPorIdExistente() {
        VooRepository vooRepository = new VooRepository();
        Aviao aviao = new Aviao(1, "Boeing 737", 200, "Boeing");
        Voo voo = new Voo(1, "São Paulo", "Rio de Janeiro", LocalDateTime.now(), aviao);
        vooRepository.salvar(voo);

        Voo buscado = vooRepository.buscarPorId(1);
        Assertions.assertEquals(voo, buscado);
    }

    @Test
    public void buscarVooPorIdInexistente() {
        VooRepository vooRepository = new VooRepository();
        Assertions.assertThrows(RuntimeException.class, () -> vooRepository.buscarPorId(99));
    }

    //Teste função de listar todos os voo cadastrados
    @Test
    public void listarTodosVoos() {
        vooRepository.salvar(new Voo(1, "São Paulo", "Rio de Janeiro", LocalDateTime.now(), aviao));
        vooRepository.salvar(new Voo(2, "Rio de Janeiro", "Curitiba", LocalDateTime.now(), aviao));

        List<Voo> voos = vooRepository.buscarTodos();
        Assertions.assertEquals(2, voos.size());
    }
}
