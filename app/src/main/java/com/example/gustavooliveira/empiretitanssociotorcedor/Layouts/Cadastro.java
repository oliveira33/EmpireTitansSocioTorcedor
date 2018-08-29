package com.example.gustavooliveira.empiretitanssociotorcedor.Layouts;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gustavooliveira.empiretitanssociotorcedor.R;

public class Cadastro extends AppCompatActivity {

    private Button btConfirma;
    private Button btCancelar;
    private EditText txtNome;
    private EditText txtSobrenome;
    private EditText txtEmail;
    private EditText txtData;
    private EditText txtCpf;
    private EditText txtTelefone;
    private EditText txtEndereco;
    private EditText txtSenha;
    private EditText txtSenhaConfirm;
    private EditText txtCartao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        btConfirma = (Button)findViewById(R.id.btConfirma);
        btCancelar = (Button)findViewById(R.id.btCancelar);
        txtNome = (EditText)findViewById(R.id.txtNome);
        txtSobrenome = (EditText)findViewById(R.id.txtSobrenome);
        txtEmail = (EditText)findViewById(R.id.txtEndereco);
        txtData = (EditText)findViewById(R.id.txtData);
        txtCpf = (EditText)findViewById(R.id.txtCpf);
        txtTelefone = (EditText)findViewById(R.id.txtTelefone);
        txtEndereco = (EditText)findViewById(R.id.txtEndereco);
        txtSenha = (EditText)findViewById(R.id.txtSenha);
        txtSenhaConfirm = (EditText)findViewById(R.id.txtSenhaConfirm);
        txtCartao = (EditText)findViewById(R.id.txtCartao);

        btConfirma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Botão para a função criar novo usuario
                Toast.makeText(getApplicationContext(), "Função não criada", Toast.LENGTH_LONG).show();
            }
        });

        btCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Cadastro.this, Login.class);
                startActivity(intent);
                finish();
            }
        });

    }
}
