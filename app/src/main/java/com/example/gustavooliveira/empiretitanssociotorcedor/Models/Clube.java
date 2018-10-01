package com.example.gustavooliveira.empiretitanssociotorcedor.Models;

public class Clube {

    private String id;
    private String nome;

    public Clube() {
    }

    public Clube(String nome) {
        this.nome = nome;
    }

    public Clube(String id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
