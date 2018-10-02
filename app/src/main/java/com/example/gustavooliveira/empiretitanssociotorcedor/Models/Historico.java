package com.example.gustavooliveira.empiretitanssociotorcedor.Models;

import java.util.Date;

public class Historico {

    private String id;
    private String idUsuario;
    private String idPartida;
    private Date data;

    private Partida partida;

    public Historico() {
    }

    public Historico(String idUsuario, String idPartida, Date data) {
        this.idUsuario = idUsuario;
        this.idPartida = idPartida;
        this.data = data;
    }

    public Historico(String id, String idUsuario, String idPartida, Date data, Partida partida) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.idPartida = idPartida;
        this.data = data;
        this.partida = partida;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getIdPartida() {
        return idPartida;
    }

    public void setIdPartida(String idPartida) {
        this.idPartida = idPartida;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Partida getPartida() {
        return partida;
    }

    public void setPartida(Partida partida) {
        this.partida = partida;
    }
}
