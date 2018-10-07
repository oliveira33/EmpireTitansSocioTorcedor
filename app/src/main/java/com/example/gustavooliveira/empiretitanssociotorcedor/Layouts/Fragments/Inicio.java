package com.example.gustavooliveira.empiretitanssociotorcedor.Layouts.Fragments;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gustavooliveira.empiretitanssociotorcedor.Adapters.JogoAdapter;
import com.example.gustavooliveira.empiretitanssociotorcedor.Models.Clube;
import com.example.gustavooliveira.empiretitanssociotorcedor.Models.Partida;
import com.example.gustavooliveira.empiretitanssociotorcedor.Models.Usuario;
import com.example.gustavooliveira.empiretitanssociotorcedor.R;
import com.example.gustavooliveira.empiretitanssociotorcedor.Salesforce.PartidaSF;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Inicio extends Fragment {

    private ArrayList<Partida> listJogo = new ArrayList<>();
    private JogoAdapter adapter;
    private RecyclerView recyclerView;
    private TextView txtNomeUsuario;
    private TextView txtEmailUsuario;
    private TextView txtCpfUsuario;
    private ProgressBar progressBar;
    private ImageButton btFacebook;
    private ImageButton btInsta;
    private ImageButton btTwiter;
    private View mView;

    public Inicio() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.fragment_inicio, container, false);
        recyclerView = mView.findViewById(R.id.jogo_recycleView);
        RecyclerView.LayoutManager li = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(li);
        txtNomeUsuario = mView.findViewById(R.id.txtCardNome);
        txtNomeUsuario.setText(Usuario.getPrincipal().getNome() + " " + Usuario.getPrincipal().getSobrenome());
        txtEmailUsuario = mView.findViewById(R.id.txtCardEmail);
        txtEmailUsuario.setText(Usuario.getPrincipal().getEmail());
        txtCpfUsuario = mView.findViewById(R.id.txtCardMatricula);
        txtCpfUsuario.setText(Usuario.getPrincipal().getCpf());
        progressBar = mView.findViewById(R.id.progressBarInicio);
        btFacebook = mView.findViewById(R.id.btFacebookInicio);
        btInsta = mView.findViewById(R.id.btInstaInicio);
        btTwiter = mView.findViewById(R.id.btTwitterInicio);

        btFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/Empire-Titans-2179135355701007/?modal=admin_todo_tour"));
                startActivity(intent);
            }
        });

        btInsta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/empire1titans/"));
                startActivity(intent);
            }
        });

        btTwiter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/empiretitans"));
                startActivity(intent);
            }
        });

        preencherPartidas();

        return mView;

    }

    private void preencherPartidas() {


        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    listJogo = new PartidaSF().getProximasPartidas();
                    implementarAdapter();
                } catch (Exception e) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getContext(), "Não foi possível carregar a lista de partidas", Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.INVISIBLE);
                        }
                    });
                    e.printStackTrace();
                }
            }
        }).start();

    }

    private void implementarAdapter() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter = new JogoAdapter(listJogo, getContext());
                recyclerView.setAdapter(adapter);
                progressBar.setVisibility(View.INVISIBLE);
            }
        });
    }

}
