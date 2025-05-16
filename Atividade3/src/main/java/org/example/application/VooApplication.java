package org.example.application;

import org.example.entities.Voo;
import org.example.repositories.VooRepository;

import java.util.List;

public class VooApplication {
    private VooRepository vooRepository;

    public VooApplication(VooRepository vooRepository) {
        this.vooRepository = vooRepository;
    }
    public List<Voo> buscarTodos() {
        return this.vooRepository.buscarTodos();
    }

    public Voo buscarPorId(int id) {
        return this.vooRepository.buscarPorId(id);
    }

    public boolean existe(int id) {
        return this.vooRepository.existe(id);
    }

    public boolean salvar(Voo voo) {
        if (existe(voo.getId())) {
            System.out.println("Erro: Já existe um voo com esse ID.");
            return false;
        }
        if (voo.getAviao() == null) {
            System.out.println("Erro: O voo deve estar vinculado a um avião.");
            return false;
        }
        if (voo.vagasDisponiveis() <= 0) {
            System.out.println("Erro: O voo não possui vagas disponíveis.");
            return false;
        }

        this.vooRepository.salvar(voo);
        return true;
    }
}
