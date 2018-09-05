package com.example.gustavooliveira.empiretitanssociotorcedor.Layouts;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gustavooliveira.empiretitanssociotorcedor.MainActivity;
import com.example.gustavooliveira.empiretitanssociotorcedor.Models.Usuario;
import com.example.gustavooliveira.empiretitanssociotorcedor.R;
import com.example.gustavooliveira.empiretitanssociotorcedor.Salesforce.UsuarioSF;

public class Login extends AppCompatActivity {

    private Button btLogin;
    private Button btCadastrar;
    private EditText txtEmail;
    private EditText txtSenha;

    private final Handler handler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btLogin = (Button) findViewById(R.id.btLogin);
        btCadastrar = (Button) findViewById(R.id.btCadastrar);
        txtEmail = (EditText) findViewById(R.id.txtEmail);
        txtSenha = (EditText) findViewById(R.id.txtSenha);

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    // validar(); Desabilitado para testes
                    logar(txtEmail.getText().toString(), txtSenha.getText().toString());
                } catch (Exception ex) {
                    Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });

        btCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Login.this, Cadastro.class);
                    startActivity(intent);
                } catch (Exception ex) {
                    Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void validar() throws Exception {
        String email = txtEmail.getText().toString();
        if (email.isEmpty())
            throw new Exception("O e-mail é obrigatório");
        if (!email.contains("@") || !email.contains("."))
            throw new Exception("O e-mail é inválido");

        String senha = txtSenha.getText().toString();
        if (senha.isEmpty())
            throw new Exception("A senha é obrigatória");
    }

    public void logar(final String email, final String senha) {
        new Thread() {
            @Override
            public void run() {
                try {
                    UsuarioSF sf = new UsuarioSF();
                    String id = sf.logar(email, senha);
                    Usuario.setPrincipal(sf.get(id));

                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(Login.this, MainActivity.class);
                            startActivity(intent);
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
