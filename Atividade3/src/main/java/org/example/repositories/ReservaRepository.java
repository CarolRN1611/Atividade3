package org.example.repositories;

import org.example.entities.Reserva;

import java.util.ArrayList;
import java.util.List;

public class ReservaRepository {
    private List<Reserva> reservas = new ArrayList<Reserva>();

    public List<Reserva> buscarTodos() {
        return reservas;
    }

    public void salvar(Reserva reserva) {
        reservas.add(reserva);
    }

}
