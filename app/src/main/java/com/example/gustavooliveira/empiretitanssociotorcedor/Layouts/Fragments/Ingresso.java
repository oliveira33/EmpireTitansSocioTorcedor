package com.example.gustavooliveira.empiretitanssociotorcedor.Layouts.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Toast;

import com.example.gustavooliveira.empiretitanssociotorcedor.Adapters.IngressoAdapter;
import com.example.gustavooliveira.empiretitanssociotorcedor.Models.Partida;
import com.example.gustavooliveira.empiretitanssociotorcedor.R;
import com.example.gustavooliveira.empiretitanssociotorcedor.Salesforce.DateSF;
import com.example.gustavooliveira.empiretitanssociotorcedor.Salesforce.PartidaSF;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Ingresso extends Fragment {

    private RecyclerView recyclerView;
    static List<Partida> listPartidas = new ArrayList<>();
    private View mView;
    private IngressoAdapter adapter;


    public Ingresso() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        preencherListPartidas();
        mView = inflater.inflate(R.layout.fragment_ingresso, container, false);
        recyclerView = mView.findViewById(R.id.recycleView_Ingresso);
        adapter = new IngressoAdapter(listPartidas, getContext());
        RecyclerView.LayoutManager li = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(li);
        recyclerView.setAdapter(adapter);

        return mView;
    }

    private void preencherListPartidas() {

        /*try {
            listPartidas = new PartidaSF().getProximasPartidas();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getContext(), "A lista de ingressos não pode ser carregada.\n"+e, Toast.LENGTH_LONG).show();
        }*/
        listPartidas.add(new Partida("5", "1", new Date(), 10.50, "oi"));
        listPartidas.add(new Partida("1", "1", new Date(), 10.50, "oi"));
        listPartidas.add(new Partida("5", "1", new Date(), 10.50, "oi"));
        listPartidas.add(new Partida("1", "1", new Date(), 10.50, "oi"));
    }

}
