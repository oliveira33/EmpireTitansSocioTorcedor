package com.example.gustavooliveira.empiretitanssociotorcedor.Salesforce;

import com.example.gustavooliveira.empiretitanssociotorcedor.Models.Usuario;


import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class UsuarioSF {

    public void Cadastrar(Usuario usuario) throws Exception {

        HttpURLConnection conexao = (HttpURLConnection) new URL("https://na57.salesforce.com/services/data/v43.0/sobjects/Usuario__c").openConnection();
        conexao.setDoInput(true);
        conexao.setDoOutput(true);
        conexao.setRequestMethod("POST");
        conexao.setRequestProperty("Authorization", "Bearer " + Autenticacao.get().getToken());
        conexao.setRequestProperty("Content-Type", "application/json");

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(conexao.getOutputStream(), "UTF-8"));
        writer.write(usuario.toJSON(false).toString());
        writer.flush();
        writer.close();

        if(conexao.getResponseCode() != HttpURLConnection.HTTP_CREATED)
            throw new Exception(conexao.getResponseMessage());
    }

}
