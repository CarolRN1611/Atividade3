package org.example.application;

import org.example.entities.Aviao;
import org.example.repositories.AviaoRepository;

import java.util.List;

public class AviaoApplication {
    private AviaoRepository aviaoRepository;

    public AviaoApplication(AviaoRepository aviaoRepository) {
        this.aviaoRepository = aviaoRepository;
    }

    public List<Aviao> buscarTodos() {
        return this.aviaoRepository.buscarTodos();
    }

    public Aviao buscarPorId(int id) {
        return this.aviaoRepository.buscarPorId(id);
    }

    public boolean salvar(Aviao aviao) {
        if (aviao.getCapacidade() <= 0) {
            System.out.println("Erro: Capacidade deve ser maior que 0.");
            return false;
        }

        this.aviaoRepository.salvar(aviao);
        return true;
    }
}
