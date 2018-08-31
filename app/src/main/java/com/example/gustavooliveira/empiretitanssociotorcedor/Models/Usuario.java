package com.example.gustavooliveira.empiretitanssociotorcedor.Models;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

public class Usuario {

    private int id;
    private String email;
    private String senha;
    private String nome;
    private String sobrenome;
    private String dataNascimento;
    private String cpf;
    private String rg;
    private String endereco;
    private String celular;
    private String cartao;

    public Usuario() { }

    public Usuario(String email, String senha, String nome, String sobrenome, String dataNascimento, String cpf, String rg, String endereco, String celular, String cartao) {
        this.email = email;
        this.senha = senha;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.dataNascimento = dataNascimento;
        this.cpf = cpf;
        this.rg = rg;
        this.endereco = endereco;
        this.celular = celular;
        this.cartao = cartao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public JSONObject toJSON(boolean incluirId) throws JSONException {
        JSONObject json = new JSONObject();

        if(incluirId)
            json.put("id", id);

        json.put("email__c", email);
        json.put("senha__c", senha);
        json.put("nome__c", nome);
        json.put("sobrenome__c", sobrenome);
        json.put("dataNascimento__c", dataNascimento);
        json.put("cpf__c", cpf);
        json.put("rg__c", rg);
        json.put("endereco__c", endereco);
        json.put("celular__c", celular);
        json.put("cartao__c", cartao);

        return json;
    }
}
