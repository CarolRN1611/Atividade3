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

    public Voo(int id, String origem, String destino, LocalDateTime dataHora, Aviao aviao) {
        this.id = id;
        this.origem = origem;
        this.destino = destino;
        this.dataHora = dataHora;
        this.aviao = aviao;
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
