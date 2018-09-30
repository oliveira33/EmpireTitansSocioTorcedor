package com.example.gustavooliveira.empiretitanssociotorcedor.Models;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

public class Usuario {

    private String id;
    private String email;
    private String senha;
    private String nome;
    private String sobrenome;
    private Date dataNascimento;
    private String cpf;
    private String endereco;
    private String telefone;
    private String cartao;
    private String codSeguranca;
    private Character administrador;
    private static Usuario principal;

    public Usuario() {
    }

    public Usuario(String email, String senha, String nome, String sobrenome, Date dataNascimento, String cpf, String endereco, String telefone, String cartao, String codSeguranca, Character administrador) {
        this.email = email;
        this.senha = senha;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.dataNascimento = dataNascimento;
        this.cpf = cpf;
        this.endereco = endereco;
        this.telefone = telefone;
        this.cartao = cartao;
        this.codSeguranca = codSeguranca;
        this.administrador = administrador;
    }

    public Usuario(String id, String email, String senha, String nome, String sobrenome, Date dataNascimento, String cpf, String endereco, String telefone, String cartao, String codSeguranca, Character administrador) {
        this.id = id;
        this.email = email;
        this.senha = senha;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.dataNascimento = dataNascimento;
        this.cpf = cpf;
        this.endereco = endereco;
        this.telefone = telefone;
        this.cartao = cartao;
        this.codSeguranca = codSeguranca;
        this.administrador = administrador;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCartao() {
        return cartao;
    }

    public void setCartao(String cartao) {
        this.cartao = cartao;
    }

    public String getCodSeguranca() {
        return codSeguranca;
    }

    public void setCodSeguranca(String codSeguranca) {
        this.codSeguranca = codSeguranca;
    }

    public Character getAdministrador() {
        return administrador;
    }

    public void setAdministrador(Character administrador) {
        this.administrador = administrador;
    }

    public static Usuario getPrincipal() {
        return principal;
    }

    public static void setPrincipal(Usuario principal) {
        Usuario.principal = principal;
    }
}
