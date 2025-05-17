package org.example.repositories;

import org.example.entities.Passageiro;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PassageiroRepositoryTest {
    PassageiroRepository repository = new PassageiroRepository();
    Passageiro passageiro;

    @BeforeEach
    public void setUp() {
        passageiro = new Passageiro(1, "João Silva", "52998224725", "joao@email.com");
    }

    //Testar da funçao de salvar
    @Test
    public void salvarPassageiro() {
        Passageiro passageiro = new Passageiro(1, "Ana", "52998224725", "ana@email.com");
        repository.salvar(passageiro);

        Assertions.assertEquals(1, repository.buscarTodos().size());
        Assertions.assertEquals(passageiro, repository.buscarPorId(1));
    }

    //Testar da buscarId Inexistente
    @Test
    public void buscarPorIdNaoExistente() {
        Assertions.assertThrows(RuntimeException.class, () -> {
            repository.buscarPorId(99);
        }, "Deveria lançar exceção se o ID não existir.");
    }

    //Teste função BuscarTodos
    @Test
    public void buscarTodosPassageiros(){
        repository.salvar(passageiro);
        Passageiro passageiro2 = new Passageiro(2, "Maria", "52998224725", "maria@email.com");
        repository.salvar(passageiro2);

        Assertions.assertEquals(2,repository.buscarTodos().size());
    }
}
