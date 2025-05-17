package org.example.application;

import org.example.entities.Passageiro;
import org.example.repositories.PassageiroRepository;

import java.util.List;

public class PassageiroApplication {
    private PassageiroRepository  passageiroRepository;

    public PassageiroApplication(PassageiroRepository passageiroRepository) {
        this.passageiroRepository = passageiroRepository;
    }

    public List<Passageiro> buscarTodos() {
        return this.passageiroRepository.buscarTodos();
    }

    public Passageiro buscarPorId(int id) {
        return this.passageiroRepository.buscarPorId(id);
    }

    public boolean salvar(Passageiro passageiro) {

        if (!validarDadosPassageiro(passageiro)) {
            System.out.println("Erro: CPF ou Email inválido.");
            return false;
        }

        this.passageiroRepository.salvar(passageiro);
        return true;
    }

    public static boolean validarDadosPassageiro(Passageiro passageiro){
        if (!passageiro.validarCPF(passageiro.getCpf())) {
            return false;
        }
        if (!passageiro.validarEmail(passageiro.getEmail())) {
            return false;
        }
        return true;
    }

}
