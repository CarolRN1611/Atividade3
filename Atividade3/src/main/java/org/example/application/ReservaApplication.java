package org.example.application;

import org.example.entities.Passageiro;
import org.example.entities.Reserva;
import org.example.entities.Voo;
import org.example.repositories.ReservaRepository;

import java.util.List;

public class ReservaApplication {
    private ReservaRepository reservaRepository;

    public ReservaApplication(ReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
    }

    public List<Reserva> buscarTodos() {
        return this.reservaRepository.buscarTodos();
    }

    public boolean salvar(Reserva reserva) {
        if (calcularVagasDisponiveis(reserva.getVoo()) <= 0) {
            return false;
        }
        if (verificarDuplicidade(reserva)) {
            return false;
        }
        this.reservaRepository.salvar(reserva);
        return true;
    }

    public boolean verificarDuplicidade(Reserva novaReserva) {
        List<Reserva> reservas = buscarTodos();

        String chaveNova = gerarChave(novaReserva.getPassageiro(), novaReserva.getVoo());

        return reservas.stream()
                .map(r -> gerarChave(r.getPassageiro(), r.getVoo()))
                .anyMatch(chave -> chave.equals(chaveNova));
    }

    private String gerarChave(Passageiro passageiro, Voo voo) {
        return passageiro.getId() + "-" + voo.getId();
    }

    private int calcularVagasDisponiveis(Voo voo) {
        long reservasNoVoo = reservaRepository.buscarTodos().stream()
                .filter(r -> r.getVoo().getId() == voo.getId())
                .count();
        return voo.getAviao().getCapacidade() - (int) reservasNoVoo;
    }


}
