package com.example.gustavooliveira.empiretitanssociotorcedor.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.example.gustavooliveira.empiretitanssociotorcedor.Models.Partida;

import java.util.ArrayList;
import java.util.List;

public class HistoricoAdapter extends RecyclerView.Adapter {

    private List<Partida> list;
    private Context context;

    public HistoricoAdapter(List<Partida> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class NossoViewHolder extends RecyclerView.ViewHolder {



        public NossoViewHolder(View itemView) {
            super(itemView);
        }
    }

}
