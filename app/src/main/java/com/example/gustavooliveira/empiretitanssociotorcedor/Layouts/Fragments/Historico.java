package com.example.gustavooliveira.empiretitanssociotorcedor.Layouts.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;

import com.example.gustavooliveira.empiretitanssociotorcedor.Adapters.HistoricoAdapter;
import com.example.gustavooliveira.empiretitanssociotorcedor.Models.Partida;
import com.example.gustavooliveira.empiretitanssociotorcedor.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Historico extends Fragment {

    private View mView;
    private RecyclerView recyclerView;
    private List<Partida> historicoLista = preencherLista();
    private HistoricoAdapter adapter;

    public Historico() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.fragment_historico, container, false);
        recyclerView = mView.findViewById(R.id.recycleViewHistorico);
        RecyclerView.LayoutManager li = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(li);
        adapter = new HistoricoAdapter(historicoLista, getContext());

        recyclerView.setAdapter(adapter);

        return mView;
    }

    private ArrayList<Partida> preencherLista() {
        SimpleDateFormat out = new SimpleDateFormat("dd/MM/yyyy");
        ArrayList<Partida> list = new ArrayList<>();
        list.add(new Partida("0", "0", new Date(), 15.5, "estadio"));
        list.add(new Partida("0", "0", new Date(), 1.5, "morro"));
        list.add(new Partida("0", "0", new Date(), 1555.5, "rua"));
        return list;
    }

}
