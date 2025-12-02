package com.lunarsystems.model;

public class NaveCargueira extends Nave {
    private static final long serialVersionUID = 1L;

    public NaveCargueira() {}

    public NaveCargueira(String id, String nome, int capacidadeTripulantes) {
        super(id, nome, capacidadeTripulantes);
    }

    @Override
    public String getTipo() { return "Cargueira"; }
}
