package com.lunarsystems.model;

import java.io.Serializable;
import java.util.Objects;

public class Austronauta implements Serializable {
    private static final long serialVersionUID = 1L;  //esse número serve para identificar a versão da classe ao serializar/deserializar

    private String id;
    private String nome;
    private int idade;
    private String especialidade;
    private int horasVoo;

    public Austronauta() {}

    public Austronauta(String id, String nome, int idade, String especialidade, int horasVoo) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.especialidade = especialidade;
        this.horasVoo = horasVoo;
    } ////setando as caracteristicas do astronauta
    
    //getters e setters das caracteristicas
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public int getIdade() { return idade; }
    public void setIdade(int idade) { this.idade = idade; }

    public String getEspecialidade() { return especialidade; }
    public void setEspecialidade(String especialidade) { this.especialidade = especialidade; }

    public int getHorasVoo() { return horasVoo; }
    public void setHorasVoo(int horasVoo) { this.horasVoo = horasVoo; }

    public boolean isElegivel() {
        return this.idade >= 21;
    } //metodo pra conferir se a idade é maior que 21 ou não

    @Override //o próximo método substitui um método da superclasse
    
    public String toString() { //transforma o objeto em texto
        return String.format("Astronauta[id=%s, nome=%s, idade=%d, especialidade=%s, horas=%d]",
                id, nome, idade, especialidade, horasVoo);
    }

    @Override
    public boolean equals(Object o) { //esse objeto é igual ao outro?
        if (this == o) return true;
        if (!(o instanceof Austronauta)) return false;
        Austronauta that = (Austronauta) o;
        return Objects.equals(id, that.id);
    } //sãp iguais desde que tenham o mesmo id

    @Override
    public int hashCode() {
        return Objects.hash(id);
    } //gera um numero baseado no id pro java conseguir guardar e encontrar obj
}

