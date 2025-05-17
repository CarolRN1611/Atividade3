package org.example.application;

import org.example.entities.Aviao;
import org.example.entities.Voo;
import org.example.repositories.VooRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

public class VooApplicationTest {
    VooRepository vooRepository;
    VooApplication vooApplication;
    Voo voo;
    Aviao aviao;

    @BeforeEach
    public void setUp() {
        vooRepository = new VooRepository();
        vooApplication = new VooApplication(vooRepository);
        aviao = new Aviao(1, "Boeing 737", 200, "Boeing");
        voo = new Voo(1, "São Paulo", "Rio de Janeiro", LocalDateTime.now(), aviao);
    }

    //Cadastrar voo com origem, destino, data e avião válido → Deve ser salvo corretamente
    @Test
    public void salvarVooComDadosValidos() {
        Assertions.assertTrue( vooApplication.salvar(voo));
        Assertions.assertEquals(1, vooRepository.buscarTodos().size());
    }

    //Tentar cadastrar voo sem avião associado → Deve lançar exceção ou falhar
    @Test
    public void naoSalvarVooSemAviao() {
        Voo voo = new Voo(1, "Bahia", "Rio Grande do Sil", LocalDateTime.now(), null);

        Assertions.assertFalse(vooApplication.salvar(voo));
        Assertions.assertEquals(0, vooRepository.buscarTodos().size());
    }


    //Listar voos após 1 cadastro → Deve retornar 1 registro com dados do avião
    @Test
    public void listarVooApos1Cadastros(){
        vooApplication.salvar(voo);
        List<Voo> voos = vooApplication.buscarTodos();

        Assertions.assertEquals(1,voos.size());
        Voo vooBuscado = vooApplication.buscarPorId(1);

        Assertions.assertEquals(voo.getOrigem(),vooBuscado.getOrigem());
        Assertions.assertEquals(voo.getDestino(),vooBuscado.getDestino());
        Assertions.assertEquals(voo.getDataHora(),vooBuscado.getDataHora());
        Assertions.assertEquals(voo.getAviao().getModelo(),vooBuscado.getAviao().getModelo());
        Assertions.assertEquals(voo.getAviao().getCapacidade(),vooBuscado.getAviao().getCapacidade());
        Assertions.assertEquals(voo.getAviao().getFabricante(),vooBuscado.getAviao().getFabricante());
    }
}
