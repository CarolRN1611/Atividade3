package org.example.entities;

import java.time.LocalDateTime;

public class Reserva {
    private int id;
    private Passageiro passageiro;
    private Voo voo;
    private LocalDateTime dataReserva;

    public Reserva(int id, Passageiro passageiro, Voo voo, LocalDateTime dataReserva) {
        this.id = id;
        this.passageiro = passageiro;
        this.voo = voo;
        this.dataReserva = dataReserva;
    }

    public int getId() {
        return this.id;
    }

    public Passageiro getPassageiro() {
        return passageiro;
    }

    public Voo getVoo() {
        return voo;
    }

    public LocalDateTime getDataReserva() {
        return dataReserva;
    }

    @Override
    public String toString() {
        return "Reserva{" +
                "id=" + id +
                ", passageiro=" + passageiro +
                ", voo=" + voo +
                ", dataReserva=" + dataReserva +
                '}';
    }
}
