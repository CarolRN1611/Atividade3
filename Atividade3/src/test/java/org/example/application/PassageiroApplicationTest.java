package org.example.application;
import org.example.entities.Passageiro;
import org.example.repositories.PassageiroRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class PassageiroApplicationTest{
    PassageiroApplication application;
    PassageiroRepository repository;
    Passageiro passageiro;

    @BeforeEach
    public void setUp() {
        passageiro = new Passageiro(1, "João Silva", "52998224725", "joao@email.com");
        repository = new PassageiroRepository();
        application = new PassageiroApplication(repository);
    }

    //Cadastrar passageiro com dados válidos → Deve adicionar à lista
    @Test
    public void salvarPassageiroValido() {

        Passageiro passageiro = new Passageiro(1, "Ana", "52998224725", "ana@email.com");

        Assertions.assertTrue(application.salvar(passageiro));
        Assertions.assertEquals(1, repository.buscarTodos().size());
    }

    //Tentar cadastrar passageiro com CPF inválido → Deve falhar ou lançar exceção
    @Test
    public void salvarPassageiroInvalido() {

        Passageiro passageiro = new Passageiro(2, "Maria Souza", "1234567890", "maria@email.com");
        ;
        Assertions.assertFalse(application.salvar(passageiro));
        Assertions.assertEquals(0, repository.buscarTodos().size());
    }

    //Listar passageiros após 3 cadastros → Deve retornar 3 registros
    @Test
    public void listarPassageirosApos3Cadastros(){
        Passageiro passageiro1 = new Passageiro(1, "João Silva", "137.817.040-76", "joao@email.com");
        Passageiro passageiro2 = new Passageiro(2, "Maria Oliveira", "548.839.360-90", "maria.oliveira@gmail.com");
        Passageiro passageiro3 = new Passageiro(3, "Carlos Santos", "398.200.930-82", "carlos.santos@gmail.com");

        application.salvar(passageiro1);
        application.salvar(passageiro2);
        application.salvar(passageiro3);

        List<Passageiro> passageiros = application.buscarTodos();

        Assertions.assertEquals(3,passageiros.size());

        Passageiro p1 = application.buscarPorId(1);


        Assertions.assertEquals(passageiro1.getId(),p1.getId());
        Assertions.assertEquals(passageiro1.getNome(),p1.getNome());
        Assertions.assertEquals(passageiro1.getCpf(),p1.getCpf());
        Assertions.assertEquals(passageiro1.getEmail(),p1.getEmail());
    }
}
