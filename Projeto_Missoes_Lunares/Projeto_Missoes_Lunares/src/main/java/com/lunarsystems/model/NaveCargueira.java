package com.lunarsystems.model;

public class NaveCargueira extends Nave {
    private static final long serialVersionUID = 1L; //transformar objeto em bytes para salvar/enviar

    public NaveCargueira() {} //criar pra preencher depois

    public NaveCargueira(String id, String nome, int capacidadeTripulantes) { 
        super(id, nome, capacidadeTripulantes); //Chama o construtor da superclasse passando esses valores
    } 

    @Override
    public String getTipo() { return "Cargueira"; } //devolve o tipo da nave como Cargueira
}

