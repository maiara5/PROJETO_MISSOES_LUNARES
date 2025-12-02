package com.lunarsystems.model;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Missao implements Serializable {
    private static final long serialVersionUID = 1L;

    private String codigo;
    private String nome;
    private LocalDate dataLancamento;
    private LocalDate dataRetorno;
    private String destino;
    private String objetivo;
    private String resultado;
    private Nave nave;
    private List<Austronauta> tripulacao = new ArrayList<>();

    public Missao() {}

    public Missao(String codigo, String nome, LocalDate dataLancamento, LocalDate dataRetorno,
                  String destino, String objetivo, Nave nave) {
        this.codigo = codigo;
        this.nome = nome;
        this.dataLancamento = dataLancamento;
        this.dataRetorno = dataRetorno;
        this.destino = destino;
        this.objetivo = objetivo;
        this.nave = nave;
    }

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public LocalDate getDataLancamento() { return dataLancamento; }
    public void setDataLancamento(LocalDate dataLancamento) { this.dataLancamento = dataLancamento; }

    public LocalDate getDataRetorno() { return dataRetorno; }
    public void setDataRetorno(LocalDate dataRetorno) { this.dataRetorno = dataRetorno; }

    public String getDestino() { return destino; }
    public void setDestino(String destino) { this.destino = destino; }

    public String getObjetivo() { return objetivo; }
    public void setObjetivo(String objetivo) { this.objetivo = objetivo; }

    public String getResultado() { return resultado; }
    public void setResultado(String resultado) { this.resultado = resultado; }

    public Nave getNave() { return nave; }
    public void setNave(Nave nave) { this.nave = nave; }

    public List<Austronauta> getTripulacao() { return tripulacao; }

    public void adicionarAstronauta(Austronauta a) {
        if (a == null) throw new IllegalArgumentException("Astronauta nulo");
        if (!a.isElegivel()) throw new IllegalArgumentException("Astronauta com idade inferior a 21 anos: " + a.getNome());
        if (tripulacao.size() >= nave.getCapacidadeTripulantes())
            throw new IllegalStateException("Nave incapaz de receber mais tripulantes");
        tripulacao.add(a);
    }

    public Duration duracao() {
        if (dataLancamento == null || dataRetorno == null) return Duration.ZERO;
        return Duration.between(dataLancamento.atStartOfDay(), dataRetorno.atStartOfDay());
    }

    @Override
    public String toString() {
        return String.format(
            "Missao[codigo=%s, nome=%s, lancamento=%s, retorno=%s, destino=%s, objetivo=%s, resultado=%s, nave=%s, tripulacao=%d]",
            codigo, nome, dataLancamento, dataRetorno, destino, objetivo, resultado, nave, tripulacao.size());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Missao)) return false;
        Missao missao = (Missao) o;
        return Objects.equals(codigo, missao.codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }
}

