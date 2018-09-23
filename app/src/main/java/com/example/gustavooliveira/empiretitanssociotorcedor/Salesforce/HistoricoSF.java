package com.example.gustavooliveira.empiretitanssociotorcedor.Salesforce;

import com.example.gustavooliveira.empiretitanssociotorcedor.Models.Historico;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class HistoricoSF {

    public ArrayList<Historico> GetHistoricoUsuario(String idUsuario) throws Exception {
        ArrayList<Historico> historicos = new ArrayList<Historico>();
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
                historicos.add(new Historico(json.getString("Id"), idUsuario, json.getString("IdPartida__c"), json.getString("Data__c")));
            }
        } else
            throw new Exception(conexao.getResponseMessage());

        return historicos;
    }

}
