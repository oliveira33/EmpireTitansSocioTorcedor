package com.example.gustavooliveira.empiretitanssociotorcedor.Layouts.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gustavooliveira.empiretitanssociotorcedor.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FaleConosco extends Fragment {

    private EditText txtAssusntoEmail;
    private EditText txtMensagemEmail;
    private Button btEnviarEmail;
    private View mView;

    public FaleConosco() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.fragment_fale_conosco, container, false);

        txtAssusntoEmail = mView.findViewById(R.id.txtAssuntoEmail);
        txtMensagemEmail = mView.findViewById(R.id.txtMensagemEmail);
        btEnviarEmail = mView.findViewById(R.id.btEnviarEmail);

        btEnviarEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!txtAssusntoEmail.getText().toString().equals("") && !txtMensagemEmail.getText().toString().equals("")) {
                    enviarEmail();
                } else {
                    Toast.makeText(getContext(), "Você deve preencher os campos", Toast.LENGTH_LONG).show();
                }
            }
        });

        return mView;
    }

    private void enviarEmail() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"empire1titans@gmail.com"});
        intent.putExtra(Intent.EXTRA_SUBJECT, txtAssusntoEmail.getText().toString());
        intent.putExtra(Intent.EXTRA_TEXT, txtMensagemEmail.getText().toString());
        try {
            startActivity(Intent.createChooser(intent, "Enviando e-mail"));
            txtAssusntoEmail.setText("");
            txtMensagemEmail.setText("");
        }catch (Exception e) {
            e.getStackTrace();
        }
    }

}
