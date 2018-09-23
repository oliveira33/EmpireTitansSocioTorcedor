package com.example.gustavooliveira.empiretitanssociotorcedor.Layouts;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gustavooliveira.empiretitanssociotorcedor.Models.Usuario;
import com.example.gustavooliveira.empiretitanssociotorcedor.R;
import com.example.gustavooliveira.empiretitanssociotorcedor.Salesforce.UsuarioSF;

import java.text.ParseException;
import java.text.SimpleDateFormat;

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
    private EditText txtCodSeguranca;

    private final Handler handler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        btConfirma = (Button) findViewById(R.id.btConfirma);
        btCancelar = (Button) findViewById(R.id.btCancelar);
        txtNome = (EditText) findViewById(R.id.txtNome);
        txtSobrenome = (EditText) findViewById(R.id.txtSobrenome);
        txtEmail = (EditText) findViewById(R.id.txtEmail);
        txtData = (EditText) findViewById(R.id.txtData);
        txtCpf = (EditText) findViewById(R.id.txtCpf);
        txtTelefone = (EditText) findViewById(R.id.txtTelefone);
        txtEndereco = (EditText) findViewById(R.id.txtEndereco);
        txtSenha = (EditText) findViewById(R.id.txtSenha);
        txtSenhaConfirm = (EditText) findViewById(R.id.txtSenhaConfirm);
        txtCartao = (EditText) findViewById(R.id.txtCartao);
        // txtCodSeguranca = (EditText) findViewById(R.id.txtCodSeguranca);

        btConfirma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    cadastrar(validar());
                } catch (Exception ex) {
                    Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });

        btCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public Usuario validar() throws Exception {
        // Precisa implementar as validações aqui
        // Pega os campos aqui, armazena nas variáveis e valida. No fim usa as variáveis para criar o Objeto

        String email = txtEmail.getText().toString();
        if (email.isEmpty())
            throw new Exception("O e-mail não foi informada");

        if (!email.contains("@") || !email.contains("."))
            throw new Exception("O e-mail informado é inválido");

        String data = txtData.getText().toString();
        if (data.isEmpty())
            throw new Exception("A data de nascimento não foi informada");

        try {
            new SimpleDateFormat("dd/MM/yyyy").parse(data);
        } catch (ParseException e) {
            throw new Exception("A data de nascimento informada é inválida");
        }

        String senha = txtSenha.getText().toString();
        if (!senha.equals(txtSenhaConfirm.getText().toString()))
            throw new Exception("As senhas não são conferem");

        String codSeguranca = txtCodSeguranca.getText().toString();
        if (codSeguranca.isEmpty())
            throw new Exception("O código de segurança não foi informado");

        if (codSeguranca.length() != 3)
            throw new Exception("O código de segurança é inválido");

        for (char c : codSeguranca.toCharArray()) {
            if (!Character.isDigit(c))
                throw new Exception("O código de segurança é inválido");
        }

        return new Usuario(email, senha, txtNome.getText().toString(), txtSobrenome.getText().toString(), data, txtCpf.getText().toString(),
                txtEndereco.getText().toString(), txtTelefone.getText().toString(), txtCartao.getText().toString(), codSeguranca);
    }

    public void cadastrar(final Usuario usuario) {
        new Thread() {
            @Override
            public void run() {
                try {
                    new UsuarioSF().cadastrar(usuario);
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(), "Cadastro realizado com sucesso!", Toast.LENGTH_LONG).show();
                            finish();
                        }
                    });
                } catch (final Exception ex) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
        }.start();
    }
}
