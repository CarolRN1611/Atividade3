package org.example.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Voo {
    private int id;
    private String origem;
    private String destino;
    private LocalDateTime dataHora;
    private Aviao aviao;
    private List<Reserva> reservas = new ArrayList<>();

    public Voo(int id, String origem, String destino, LocalDateTime dataHora, Aviao aviao) {
        if (aviao == null) {
            throw new IllegalArgumentException("Voo deve estar vinculado a um avião.");
        }
        this.id = id;
        this.origem = origem;
        this.destino = destino;
        this.dataHora = dataHora;
        this.aviao = aviao;
        aviao.getVoos().add(this);
    }

    public int getId() {
        return id;
    }

    public String getOrigem() {
        return origem;
    }

    public String getDestino() {
        return destino;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public Aviao getAviao() {
        return aviao;
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public int vagasDisponiveis(){
        return (aviao.getCapacidade() - reservas.size());
    }

    @Override
    public String toString() {
        return "Voo{" +
                "id=" + id +
                ", origem='" + origem + '\'' +
                ", destino='" + destino + '\'' +
                ", dataHora=" + dataHora +
                ", aviao=" + aviao +
                '}';
    }
}
