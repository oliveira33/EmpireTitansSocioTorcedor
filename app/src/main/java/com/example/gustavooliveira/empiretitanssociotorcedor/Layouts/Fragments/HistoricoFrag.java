package com.example.gustavooliveira.empiretitanssociotorcedor.Layouts.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.gustavooliveira.empiretitanssociotorcedor.Adapters.HistoricoAdapter;
import com.example.gustavooliveira.empiretitanssociotorcedor.Models.Historico;
import com.example.gustavooliveira.empiretitanssociotorcedor.Models.Usuario;
import com.example.gustavooliveira.empiretitanssociotorcedor.R;
import com.example.gustavooliveira.empiretitanssociotorcedor.Salesforce.HistoricoSF;

import java.util.ArrayList;


public class HistoricoFrag extends Fragment {

    private View mView;
    private RecyclerView recyclerView;
    private ArrayList<Historico> listHistorico;
    private HistoricoAdapter adapter;

    public HistoricoFrag() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_historico, container, false);
        recyclerView = mView.findViewById(R.id.recycleViewHistorico);
        RecyclerView.LayoutManager li = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(li);

        consultarHistoricos();

        return mView;
    }

    private void consultarHistoricos() {
        new Thread() {
            @Override
            public void run() {
                try {
                    listHistorico = new HistoricoSF().getHistoricoUsuario(Usuario.getPrincipal().getId());
                    preencherHistoricos(listHistorico);
                } catch (final Exception e) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getContext(), "A lista de histórico não pode ser carregada.\n" + e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
        }.start();
    }

    private void preencherHistoricos(final ArrayList<Historico> historicos) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter = new HistoricoAdapter(historicos, getContext());
                recyclerView.setAdapter(adapter);
            }
        });
    }

}
