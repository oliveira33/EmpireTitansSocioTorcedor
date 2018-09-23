package com.example.gustavooliveira.empiretitanssociotorcedor.Models;

public class Historico {

    private String id;
    private String idUsuario;
    private String idPartida;
    private String data;

    public Historico() {
    }

    public Historico(String idUsuario, String idPartida, String data) {
        this.idUsuario = idUsuario;
        this.idPartida = idPartida;
        this.data = data;
    }

    public Historico(String id, String idUsuario, String idPartida, String data) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.idPartida = idPartida;
        this.data = data;
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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
