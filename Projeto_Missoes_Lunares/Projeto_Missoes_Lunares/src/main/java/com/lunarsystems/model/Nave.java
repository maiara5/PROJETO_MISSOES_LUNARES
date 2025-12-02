package com.lunarsystems.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import java.io.Serializable;

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,              
    include = JsonTypeInfo.As.PROPERTY,     
    property = "naveType"                  
)
@JsonSubTypes({
    @JsonSubTypes.Type(value = NaveTripulada.class, name = "TRIPULADA"),
    @JsonSubTypes.Type(value = NaveCargueira.class, name = "CARGUEIRA")
})
public abstract class Nave implements Serializable {
    private static final long serialVersionUID = 1L;

    protected String id;
    protected String nome;
    protected int capacidadeTripulantes;

    public Nave() {}

    public Nave(String id, String nome, int capacidadeTripulantes) {
        this.id = id;
        this.nome = nome;
        this.capacidadeTripulantes = capacidadeTripulantes;
    }

    // Getters e Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public int getCapacidadeTripulantes() { return capacidadeTripulantes; }
    public void setCapacidadeTripulantes(int capacidadeTripulantes) { this.capacidadeTripulantes = capacidadeTripulantes; }

    public abstract String getTipo();

    @Override
    public String toString() {
        return String.format("Nave[id=%s, nome=%s, tipo=%s, capacidade=%d]",
                id, nome, getTipo(), capacidadeTripulantes);
    }
}

