package com.example.gustavooliveira.empiretitanssociotorcedor.Layouts.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.gustavooliveira.empiretitanssociotorcedor.Adapters.IngressoAdapter;
import com.example.gustavooliveira.empiretitanssociotorcedor.Models.Partida;
import com.example.gustavooliveira.empiretitanssociotorcedor.R;
import com.example.gustavooliveira.empiretitanssociotorcedor.Salesforce.PartidaSF;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class Ingresso extends Fragment {

    private RecyclerView recyclerView;
    private ArrayList<Partida> listPartidas = new ArrayList<>();
    private View mView;
    private IngressoAdapter adapter;
    private ProgressBar progressBar;


    public Ingresso() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_ingresso, container, false);
        progressBar = mView.findViewById(R.id.progressBarIngresso);
        recyclerView = mView.findViewById(R.id.recycleView_Ingresso);
        RecyclerView.LayoutManager li = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(li);

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
                } catch (Exception e) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getContext(), "A lista de ingressos não pode ser carregada.", Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.INVISIBLE);
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
                adapter = new IngressoAdapter(partidas, getContext(), mView);
                recyclerView.setAdapter(adapter);
                progressBar.setVisibility(View.INVISIBLE);
            }
        });
    }

}
