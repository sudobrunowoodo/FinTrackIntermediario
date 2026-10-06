package com.finTrack.model;
import java.time.LocalDate;

/**
 *
 * @author Bruno
 */
public class Transacao {
    private int id;
    private LocalDate data;
    private String descricao;
    private double valor;
    private String tipo;

    public Transacao() {
    }

    public Transacao(
            LocalDate data,
            String descricao,
            double valor,
            String tipo) {

        this.data = data;
        this.descricao = descricao;
        this.valor = valor;
        this.tipo = tipo;
    }

    public Transacao(
            int id,
            LocalDate data,
            String descricao,
            double valor,
            String tipo) {

        this.id = id;
        this.data = data;
        this.descricao = descricao;
        this.valor = valor;
        this.tipo = tipo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void atualizar(
            LocalDate data,
            String descricao,
            double valor,
            String tipo) {

        this.data = data;
        this.descricao = descricao;
        this.valor = valor;
        this.tipo = tipo;
    }
}
