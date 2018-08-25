package com.example.gustavooliveira.empiretitanssociotorcedor.Layouts;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.gustavooliveira.empiretitanssociotorcedor.MainActivity;
import com.example.gustavooliveira.empiretitanssociotorcedor.R;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button botaoLogin = findViewById(R.id.btLogin);
        Button botaoCadastrar = findViewById(R.id.btCadastrar);

        botaoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        botaoCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Cadastro.class);
                startActivity(intent);
            }
        });

    }
}
