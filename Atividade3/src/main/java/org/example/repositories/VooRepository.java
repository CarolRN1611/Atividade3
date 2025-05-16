package org.example.repositories;

import org.example.entities.Voo;

import java.util.ArrayList;
import java.util.List;

public class VooRepository {
    private List<Voo> voos = new ArrayList<Voo>();

    public List<Voo> buscarTodos() {
        return voos;
    }

    public Voo buscarPorId(int id) {
        Voo voo = voos.stream().filter(p -> p.getId() == id).findFirst().get();
        return voo;
    }

    public boolean existe(int id) {
        return voos.stream().anyMatch(p -> p.getId() == id);
    }

    public void salvar(Voo voo) {
        voos.add(voo);
    }
}
