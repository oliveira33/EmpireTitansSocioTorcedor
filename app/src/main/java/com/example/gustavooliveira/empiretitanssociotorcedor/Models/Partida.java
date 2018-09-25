package com.example.gustavooliveira.empiretitanssociotorcedor.Models;

import java.util.Date;

public class Partida {

    private String id;
    private String idClube;
    private Date data;
    private Double valor;
    private String local;
    private String imagem;

    public Partida() {
    }

    public Partida(String id, String idClube, Date data, Double valor, String local) {
        this.id = id;
        this.idClube = idClube;
        this.data = data;
        this.valor = valor;
        this.local = local;
    }

    public Partida(String idClube, Double valor, String local, String imagem) {
        this.idClube = idClube;
        this.valor = valor;
        this.local = local;
        this.imagem = imagem;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdClube() {
        return idClube;
    }

    public void setIdClube(String idClube) {
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

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }
}
