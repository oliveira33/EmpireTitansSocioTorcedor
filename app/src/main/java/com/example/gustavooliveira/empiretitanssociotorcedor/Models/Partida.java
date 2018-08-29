package com.example.gustavooliveira.empiretitanssociotorcedor.Models;

import java.util.Date;

public class Partida {

    private int id;
    private int idClube;
    private Date data;
    private Double valor;
    private String local;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdClube() {
        return idClube;
    }

    public void setIdClube(int idClube) {
        this.idClube = idClube;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }
}
