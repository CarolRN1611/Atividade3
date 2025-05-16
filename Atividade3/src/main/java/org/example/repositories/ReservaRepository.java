package org.example.repositories;

import org.example.entities.Reserva;

import java.util.ArrayList;
import java.util.List;

public class ReservaRepository {
    private List<Reserva> reservas = new ArrayList<Reserva>();

    public List<Reserva> buscarTodos() {
        return reservas;
    }

    public Reserva buscarPorId(int id) {
        Reserva reserva = reservas.stream().filter(p -> p.getId() == id).findFirst().get();
        return reserva;
    }

    public boolean existe(int id) {
        return reservas.stream().anyMatch(p -> p.getId() == id);
    }

    public void salvar(Reserva reserva) {
        reservas.add(reserva);
    }

}
