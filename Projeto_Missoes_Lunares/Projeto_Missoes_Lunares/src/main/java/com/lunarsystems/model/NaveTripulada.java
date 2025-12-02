package com.lunarsystems.model;

public class NaveTripulada extends Nave {
    private static final long serialVersionUID = 1L;

    public NaveTripulada() {}

    public NaveTripulada(String id, String nome, int capacidadeTripulantes) {
        super(id, nome, capacidadeTripulantes);
    }

    @Override
    public String getTipo() { return "Tripulada"; }
}
