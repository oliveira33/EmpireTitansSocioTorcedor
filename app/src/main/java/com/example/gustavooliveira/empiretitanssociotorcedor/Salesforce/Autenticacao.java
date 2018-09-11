package com.example.gustavooliveira.empiretitanssociotorcedor.Salesforce;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;

public class Autenticacao {

    private static Autenticacao _instancia;
    private String token;
    private Date _atualizacao;

    private Autenticacao() {
    }

    public static Autenticacao get() {
        if (_instancia == null)
            _instancia = new Autenticacao();

        return _instancia;
    }

    public String getToken() throws Exception {
        if (compararData()) {
            _atualizacao = new Date();

            String parametros = "grant_type=password&client_id=3MVG9dZJodJWITSt1OZ0VfVl9MJZa_4Uk6rsD.FMfw8bfaSRsiOfQxUNxTfW914d0yZjtzKg_WFeXn98_XL7P";
            parametros += "&client_secret=4508895819599791871&username=gtv.assis@gmail.com&password=empiretitans99QEZuFdpoOTuHSwqedK1rUjpte";

            HttpURLConnection conexao = (HttpURLConnection) new URL("https://login.salesforce.com/services/oauth2/token").openConnection();
            conexao.setRequestMethod("POST");
            conexao.setDoInput(true);
            conexao.setDoOutput(true);

            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(conexao.getOutputStream(), "UTF-8"));
            writer.write(parametros);
            writer.flush();
            writer.close();

            if (conexao.getResponseCode() == HttpURLConnection.HTTP_OK) {
                String linha, resposta = new String();
                BufferedReader reader = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
                while ((linha = reader.readLine()) != null)
                    resposta += linha;
                reader.close();

                JSONObject json = new JSONObject(resposta);
                token = json.getString("access_token");
            } else
                throw new Exception("Houve algum erro durante a execução da requisição do token");
        }

        return token;
    }

    private boolean compararData() {
        if (_atualizacao == null)
            return true;

        Calendar calendario = Calendar.getInstance();
        calendario.setTime(_atualizacao);
        calendario.add(Calendar.MINUTE, 15);

        return new Date().after(calendario.getTime());
    }
}
