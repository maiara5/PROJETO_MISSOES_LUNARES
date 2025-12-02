package com.lunarsystems.model;

public class NaveTripulada extends Nave {
    private static final long serialVersionUID = 1L; //serialização

    public NaveTripulada() {}

    public NaveTripulada(String id, String nome, int capacidadeTripulantes) {
        super(id, nome, capacidadeTripulantes); //em vez de repetir código para inicializar delega pra superclasse
    }

    @Override
    public String getTipo() { return "Tripulada"; } //se referência tipo Nave apontando para uma NaveTripulada e chamar getTipo() retorna tripulada
}

