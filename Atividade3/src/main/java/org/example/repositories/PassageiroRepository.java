package org.example.repositories;

import org.example.entities.Passageiro;

import java.util.ArrayList;
import java.util.List;

public class PassageiroRepository {
    private List<Passageiro> passageiros = new ArrayList<Passageiro>();

    public List<Passageiro> buscarTodos() {
        return passageiros;
    }

    public Passageiro buscarPorId(int id) {
        Passageiro passageiro = passageiros.stream().filter(p -> p.getId() == id).findFirst().get();
        return passageiro;
    }

    public boolean existe(int id) {
        return passageiros.stream().anyMatch(p -> p.getId() == id);
    }

    public void salvar(Passageiro passageiro) {
        passageiros.add(passageiro);
    }
}
