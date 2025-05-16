package org.example.entities;

import java.util.ArrayList;
import java.util.List;

public class Aviao {
    private int id;
    private String modelo;
    private int capacidade;
    private String fabricante;
    private List<Voo> voos = new ArrayList<>();

    public Aviao(int id, String modelo, int capacidade, String fabricante) {
        this.id = id;
        this.modelo = modelo;
        this.capacidade = capacidade;
        this.fabricante = fabricante;
    }

    public int getId() {
        return id;
    }

    public String getModelo() {
        return modelo;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public String getFabricante() {
        return fabricante;
    }

    public List<Voo> getVoos() {
        return voos;
    }

    @Override
    public String toString() {
        return "Aviao{" +
                "id=" + id +
                ", modelo='" + modelo + '\'' +
                ", capacidade=" + capacidade +
                ", fabricante='" + fabricante + '\'' +
                '}';
    }

}
