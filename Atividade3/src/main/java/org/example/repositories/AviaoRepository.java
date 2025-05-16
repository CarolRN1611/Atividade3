package org.example.repositories;

import org.example.entities.Aviao;

import java.util.ArrayList;
import java.util.List;

public class AviaoRepository {
    private List<Aviao> avioes = new ArrayList<Aviao>();

    public List<Aviao> buscarTodos() {
        return avioes;
    }

    public Aviao buscarPorId(int id) {
        Aviao aviao = avioes.stream().filter(p -> p.getId() == id).findFirst().get();
        return aviao;
    }

    public boolean existe(int id) {
        return avioes.stream().anyMatch(p -> p.getId() == id);
    }

    public void salvar(Aviao aviao) {
        avioes.add(aviao);
    }
}
