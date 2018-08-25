package com.example.gustavooliveira.empiretitanssociotorcedor.Layouts;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.example.gustavooliveira.empiretitanssociotorcedor.R;

public class Cadastro extends AppCompatActivity {

    private Button btConfirma;
    private Button btCancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        btConfirma = (Button)findViewById(R.id.btConfirma);
        btCancelar = (Button)findViewById(R.id.btCancelar);

        btConfirma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Função não criada", Toast.LENGTH_LONG).show();
            }
        });

        btCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Cadastro.this, Login.class);
                startActivity(intent);
            }
        });

    }
}
