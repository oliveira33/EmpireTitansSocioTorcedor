package com.example.gustavooliveira.empiretitanssociotorcedor.Salesforce;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class Autenticacao {

    private Duration _tempo;
    private static Autenticacao _instancia;
    private String token;
    private Instant _atualizacao;

    private Autenticacao() {
        _tempo = Duration.of(14, ChronoUnit.MINUTES);
    }

    public static Autenticacao get() {
        if(_instancia == null)
            _instancia = new Autenticacao();

        return _instancia;
    }

    public String getToken() throws Exception {
        Instant instante = Instant.now();

        if(token == null || Duration.between(instante, _atualizacao).compareTo(_tempo) > 0) {
            _atualizacao = instante;

            if(token == null)
                token = "QEZuFdpoOTuHSwqedK1rUjpte"; // Coloca o token do e-mail aqui

            String parametros = "grant_type=password&client_id=3MVG9dZJodJWITSt1OZ0VfVl9MJZa_4Uk6rsD.FMfw8bfaSRsiOfQxUNxTfW914d0yZjtzKg_WFeXn98_XL7P";
            parametros += "&client_secret=4508895819599791871&username=gtv.assis@gmail.com&password=empiretitans99" + token;

            // Teste
            //String parametros1 = "grant_type=refresh_token&client_id=3MVG9dZJodJWITSt1OZ0VfVl9MJZa_4Uk6rsD.FMfw8bfaSRsiOfQxUNxTfW914d0yZjtzKg_WFeXn98_XL7P";
            //parametros1 += "&client_secret=4508895819599791871&refresh_token=" + token;

            HttpURLConnection conexao = (HttpURLConnection) new URL("https://login.salesforce.com/services/oauth2/token").openConnection();
            conexao.setRequestMethod("POST");
            conexao.setDoInput(true);
            conexao.setDoOutput(true);

            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(conexao.getOutputStream(), "UTF-8"));
            writer.write(parametros);
            writer.flush();
            writer.close();

            if(conexao.getResponseCode() == HttpURLConnection.HTTP_OK) {
                String linha, resposta = new String();
                BufferedReader reader = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
                while ((linha = reader.readLine()) != null)
                    resposta += linha;

                JSONObject json = new JSONObject(resposta);
                token = json.getString("access_token");
            }
            else
                throw new Exception("Houve algum erro durante a execução da requisição do token");
        }

        return token;
    }
}
