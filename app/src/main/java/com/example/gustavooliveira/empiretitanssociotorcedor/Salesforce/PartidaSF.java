package com.example.gustavooliveira.empiretitanssociotorcedor.Salesforce;

import com.example.gustavooliveira.empiretitanssociotorcedor.Models.Clube;
import com.example.gustavooliveira.empiretitanssociotorcedor.Models.Partida;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;

public class PartidaSF {

    public ArrayList<Partida> getProximasPartidas() throws Exception {
        ArrayList<Partida> partidas = new ArrayList<>();

        String query = "SELECT+Partida__c.Clube__c,+Partida__c.Data__c,+Partida__c.Id,+Partida__c.Local__c,+Partida__c.Name,+Partida__c.Valor__c,+Partida__c.Clube__r.Nome__c+FROM+Partida__c+WHERE+Data__c+>+" + new DateSF().fromDateTime(new Date());
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
            JSONObject json;
            for (int i = 0; i < array.length(); i++) {
                json = array.getJSONObject(i);
                partidas.add(new Partida(json.getString("Id"), json.getString("Clube__c"), new DateSF().toDateTime(json.getString("Data__c")),
                        Double.parseDouble(json.getString("Valor__c")), json.getString("Local__c"), new Clube(json.getString("Clube__c"), json.getString("Clube__c.Nome__c"))));
            }
        } else
            throw new Exception(conexao.getResponseMessage());

        return partidas;
    }

}
