package com.example.gustavooliveira.empiretitanssociotorcedor.Layouts.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gustavooliveira.empiretitanssociotorcedor.Adapters.JogoAdapter;
import com.example.gustavooliveira.empiretitanssociotorcedor.Models.Partida;
import com.example.gustavooliveira.empiretitanssociotorcedor.R;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Inicio extends Fragment {

    static List<Partida> listJogo = preencherPartidas();
    private JogoAdapter adapter;
    private RecyclerView recyclerView;
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


        adapter = new JogoAdapter(listJogo, getContext());


        recyclerView.setAdapter(adapter);


        return mView;

    }

    private static List<Partida> preencherPartidas() {
        List<Partida> list = new ArrayList<>();

        list.add(new Partida("Atletico", 120.00, "Estaio A", "p1"));
        list.add(new Partida("Nacional", 150.00, "Estaio B", "p2"));
        list.add(new Partida("Monte Alegre", 90.00, "Estaio A", "p3"));
        list.add(new Partida("Vazame", 75.50, "Estaio C", "p4"));


        return list;
    }

}
