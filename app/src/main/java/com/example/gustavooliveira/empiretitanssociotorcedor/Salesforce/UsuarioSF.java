package com.example.gustavooliveira.empiretitanssociotorcedor.Salesforce;

import com.example.gustavooliveira.empiretitanssociotorcedor.Models.Usuario;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class UsuarioSF {

    public void alterar(Usuario usuario) throws Exception {
        HttpURLConnection conexao = (HttpURLConnection) new URL("https://na57.salesforce.com/services/data/v43.0/sobjects/Usuario__c/" + usuario.getId()).openConnection();
        conexao.setDoOutput(true);
        conexao.setRequestMethod("PATCH");
        conexao.setRequestProperty("Authorization", "Bearer " + Autenticacao.get().getToken());
        conexao.setRequestProperty("Content-Type", "application/json");

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(conexao.getOutputStream(), "UTF-8"));
        writer.write(toJson(usuario).toString());
        writer.flush();
        writer.close();

        if (conexao.getResponseCode() != HttpURLConnection.HTTP_NO_CONTENT)
            throw new Exception(conexao.getResponseMessage());
    }

    public void cadastrar(Usuario usuario) throws Exception {
        HttpURLConnection conexao = (HttpURLConnection) new URL("https://na57.salesforce.com/services/data/v43.0/sobjects/Usuario__c").openConnection();
        conexao.setDoOutput(true);
        conexao.setRequestMethod("POST");
        conexao.setRequestProperty("Authorization", "Bearer " + Autenticacao.get().getToken());
        conexao.setRequestProperty("Content-Type", "application/json");

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(conexao.getOutputStream(), "UTF-8"));
        writer.write(toJson(usuario).toString());
        writer.flush();
        writer.close();

        if (conexao.getResponseCode() != HttpURLConnection.HTTP_CREATED)
            throw new Exception(conexao.getResponseMessage());
    }

    public void excluir(String id) throws Exception {
        HttpURLConnection conexao = (HttpURLConnection) new URL("https://na57.salesforce.com/services/data/v43.0/sobjects/Usuario__c/" + id).openConnection();
        conexao.setRequestMethod("DELETE");
        conexao.setRequestProperty("Authorization", "Bearer " + Autenticacao.get().getToken());

        if (conexao.getResponseCode() != HttpURLConnection.HTTP_NO_CONTENT)
            throw new Exception(conexao.getResponseMessage());
    }

    public Usuario logar(String email, String senha) throws Exception {
        String query = "SELECT+Id,+Email__c,+Senha__c,+Nome__c,+Sobrenome__c,+Cpf__c,+Endereco__c,+DataNascimento__c,+Celular__c,+Cartao__c,+CodSeguranca__c,+Administrador__c+FROM+Usuario__c+WHERE+Email__c+=+'" + email + "'+AND+senha__c+=+'" + senha + "'";
        HttpURLConnection conexao = (HttpURLConnection) new URL("https://na57.salesforce.com/services/data/v43.0/query/?q=" + query).openConnection();
        conexao.setDoInput(true);
        conexao.setRequestMethod("GET");
        conexao.setRequestProperty("Authorization", "Bearer " + Autenticacao.get().getToken());

        if (conexao.getResponseCode() == HttpURLConnection.HTTP_OK) {
            String linha, resposta = new String();
            BufferedReader reader = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
            while ((linha = reader.readLine()) != null)
                resposta += linha;
            reader.close();

            JSONArray array = new JSONObject(resposta).getJSONArray("records");
            if (array.length() == 0)
                throw new Exception("Usuário não cadastrado");

            JSONObject json = array.getJSONObject(0);
            return new Usuario(json.getString("Id"), json.getString("Email__c"), json.getString("Senha__c"), json.getString("Nome__c"), json.getString("Sobrenome__c"),
                    new DateSF().toDate(json.getString("DataNascimento__c")), json.getString("Cpf__c"), json.getString("Endereco__c"), json.getString("Celular__c"),
                    json.getString("Cartao__c"), json.getString("CodSeguranca__c"), json.getString("Administrador__c").charAt(0));
        } else
            throw new Exception(conexao.getResponseMessage());
    }

    private JSONObject toJson(Usuario usuario) throws Exception {
        JSONObject json = new JSONObject();

        json.put("Email__c", usuario.getEmail());
        json.put("Senha__c", usuario.getSenha());
        json.put("Nome__c", usuario.getNome());
        json.put("Sobrenome__c", usuario.getSobrenome());
        json.put("DataNascimento__c", new DateSF().fromDate(usuario.getDataNascimento()));
        json.put("Cpf__c", usuario.getCpf());
        json.put("Endereco__c", usuario.getEndereco());
        json.put("Celular__c", usuario.getCelular());
        json.put("Cartao__c", usuario.getCartao());
        json.put("CodSeguranca__c", usuario.getCodSeguranca());
        json.put("Administrador__c", usuario.getAdministrador());

        return json;
    }
}
