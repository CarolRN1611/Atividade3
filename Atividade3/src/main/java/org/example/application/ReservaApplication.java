package org.example.application;

import org.example.entities.Reserva;
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
        if (reserva.getVoo().vagasDisponiveis() < 0) {
            return false;
        }
        this.reservaRepository.salvar(reserva);
        return true;
    }
}
