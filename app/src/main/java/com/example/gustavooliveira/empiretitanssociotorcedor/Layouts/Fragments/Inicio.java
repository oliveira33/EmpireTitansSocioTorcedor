package com.example.gustavooliveira.empiretitanssociotorcedor.Layouts.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.gustavooliveira.empiretitanssociotorcedor.Adapters.JogoAdapter;
import com.example.gustavooliveira.empiretitanssociotorcedor.Models.Partida;
import com.example.gustavooliveira.empiretitanssociotorcedor.Models.Usuario;
import com.example.gustavooliveira.empiretitanssociotorcedor.R;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Inicio extends Fragment {

    static List<Partida> listJogo = preencherPartidas();
    private JogoAdapter adapter;
    private RecyclerView recyclerView;
    private TextView txtNomeUsuario;
    private TextView txtEmailUsuario;
    private TextView txtCpfUsuario;
    View mView;

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

        adapter = new JogoAdapter(listJogo, getContext());

        recyclerView.setAdapter(adapter);

        return mView;

    }

    private static List<Partida> preencherPartidas() {
        List<Partida> list = new ArrayList<>();

        return list;
    }

}
