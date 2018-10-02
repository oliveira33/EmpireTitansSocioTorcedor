package com.example.gustavooliveira.empiretitanssociotorcedor.Layouts.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gustavooliveira.empiretitanssociotorcedor.Adapters.JogoAdapter;
import com.example.gustavooliveira.empiretitanssociotorcedor.Models.Partida;
import com.example.gustavooliveira.empiretitanssociotorcedor.Models.Usuario;
import com.example.gustavooliveira.empiretitanssociotorcedor.R;
import com.example.gustavooliveira.empiretitanssociotorcedor.Salesforce.PartidaSF;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class Inicio extends Fragment {

    static ArrayList<Partida> listPartidas;
    private JogoAdapter adapter;
    private RecyclerView recyclerView;
    private TextView txtNomeUsuario;
    private TextView txtEmailUsuario;
    private TextView txtCpfUsuario;
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

        consultarPartidas();

        return mView;
    }

    private void consultarPartidas() {
        new Thread() {
            @Override
            public void run() {
                try {
                    listPartidas = new PartidaSF().getProximasPartidas();
                    preencherPartidas(listPartidas);
                } catch (final Exception e) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getContext(), "A lista de ingressos não pode ser carregada.\n" + e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
        }.start();
    }

    private void preencherPartidas(final ArrayList<Partida> partidas) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter = new JogoAdapter(partidas, getContext());
                recyclerView.setAdapter(adapter);
            }
        });
    }

}
