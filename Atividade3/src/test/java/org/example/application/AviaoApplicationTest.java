package org.example.application;

import org.example.entities.Aviao;
import org.example.repositories.AviaoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class AviaoApplicationTest {
    AviaoApplication aviaoApplication;
    AviaoRepository aviaoRepository;
    Aviao aviao;

    @BeforeEach
    public void setUp() {
        aviaoRepository = new AviaoRepository();
        aviaoApplication = new AviaoApplication(aviaoRepository);
        aviao = new Aviao(1, "Boeing 737", 200, "Boeing");
    }

    //Cadastrar avião com modelo e capacidade válidos → Deve ser salvo corretamente
    @Test
    public void criarAviaoComCapacidadeValida() {
        aviaoApplication.salvar(aviao);

        Aviao buscado = aviaoApplication.buscarPorId(1);
        Assertions.assertEquals(aviao, buscado);
    }

    //Tentar cadastrar avião com capacidade zero → Deve lançar exceção ou falhar
    @Test
    public void criarAviaoComCapacidadeZero() {
        Aviao aviaoCapacidadeZero = new Aviao(2,"Boeing 737",0,"Boeing");
        Assertions.assertFalse(aviaoApplication.salvar(aviaoCapacidadeZero));
    }

    //Tentar cadastrar avião com capacidade negativa → Deve lançar exceção ou falhar
    @Test
    public void criarAviaoComCapacidadeNegativa() {
        Aviao aviaoCapacidadeNegativa = new Aviao(2,"Boeing 737",-1,"Boeing");
        Assertions.assertFalse(aviaoApplication.salvar(aviaoCapacidadeNegativa));
    }

    //Listar aviões após 2 cadastros → Deve retornar 2 registros
    @Test
    public void listarAviaoApos2Cadastros(){
        Aviao aviao1 = new Aviao(1, "EMB 190", 2, "Embraer");
        Aviao aviao2 = new Aviao(2, "Boeing 737", 150, "Boeing");


        aviaoApplication.salvar(aviao1);
        aviaoApplication.salvar(aviao2);

        List<Aviao> avioes = aviaoApplication.buscarTodos();
        Assertions.assertEquals(2,avioes.size());

        Assertions.assertEquals(aviao1.getModelo(),avioes.get(0).getModelo());
        Assertions.assertEquals(aviao2.getModelo(),avioes.get(1).getModelo());
        Assertions.assertEquals(aviao1.getCapacidade(),avioes.get(0).getCapacidade());
        Assertions.assertEquals(aviao2.getCapacidade(),avioes.get(1).getCapacidade());
        Assertions.assertEquals(aviao1.getFabricante(),avioes.get(0).getFabricante());
        Assertions.assertEquals(aviao2.getFabricante(),avioes.get(1).getFabricante());
        Assertions.assertEquals(aviao1.getId(),avioes.get(0).getId());
        Assertions.assertEquals(aviao2.getId(),avioes.get(1).getId());
    }
}
