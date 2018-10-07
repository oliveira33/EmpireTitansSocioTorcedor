package com.example.gustavooliveira.empiretitanssociotorcedor.Layouts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.gustavooliveira.empiretitanssociotorcedor.R;

public class CadastroRealizado extends AppCompatActivity {

    Button btVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_realizado);

        btVoltar = findViewById(R.id.btVoltarCadastroRealizado);
        btVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
