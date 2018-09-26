package com.example.gustavooliveira.empiretitanssociotorcedor.Salesforce;

import com.example.gustavooliveira.empiretitanssociotorcedor.Models.Historico;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class HistoricoSF {

    public void Cadastrar(Historico historico) throws Exception {
        HttpURLConnection conexao = (HttpURLConnection) new URL("https://na57.salesforce.com/services/data/v43.0/sobjects/Historico__c").openConnection();
        conexao.setDoOutput(true);
        conexao.setRequestMethod("POST");
        conexao.setRequestProperty("Authorization", "Bearer " + Autenticacao.get().getToken());
        conexao.setRequestProperty("Content-Type", "application/json");

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(conexao.getOutputStream(), "UTF-8"));
        writer.write(toJson(historico).toString());
        writer.flush();
        writer.close();

        if (conexao.getResponseCode() != HttpURLConnection.HTTP_CREATED)
            throw new Exception(conexao.getResponseMessage());
    }

    public ArrayList<Historico> getHistoricoUsuario(String idUsuario) throws Exception {
        ArrayList<Historico> historicos = new ArrayList<>();
        String query = "SELECT+Data__c,+Id,+IdPartida__c,+IdUsuario__c+FROM+Historico__c+WHERE+IdUsuario__c+=+'" + idUsuario + "'";
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
            JSONObject json = null;
            for (int i = 0; i < array.length(); i++) {
                json = array.getJSONObject(i);
                historicos.add(new Historico(json.getString("Id"), idUsuario, json.getString("IdPartida__c"), new DateSF().toDateTime(json.getString("Data__c"))));
            }
        } else
            throw new Exception(conexao.getResponseMessage());

        return historicos;
    }

    public JSONObject toJson(Historico historico) throws Exception {
        JSONObject json = new JSONObject();

        json.put("IdUsuario__c", historico.getIdUsuario());
        json.put("IdPartida__c", historico.getIdPartida());
        json.put("Data__c", new DateSF().fromDateTime(historico.getData()));

        return json;
    }

}
