package com.example.gustavooliveira.empiretitanssociotorcedor.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gustavooliveira.empiretitanssociotorcedor.Models.Partida;
import com.example.gustavooliveira.empiretitanssociotorcedor.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class IngressoAdapter extends RecyclerView.Adapter {

    private List<Partida> partidas;
    private Context context;

    public IngressoAdapter(List<Partida> partidas, Context context) {
        this.partidas = partidas;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.celula_ingresso, parent, false);
        NossoViewHolder holder = new NossoViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder nHolder, int position) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        NossoViewHolder holder = (NossoViewHolder) nHolder;
        Partida p = partidas.get(position);
        holder.idClube.setText(p.getIdClube());
        holder.data.setText(format.format(p.getData()));
        holder.local.setText("Local: "+p.getLocal());
        holder.valor.setText("R$"+p.getValor().toString());
    }

    @Override
    public int getItemCount() {
        return partidas.size();
    }

    public class NossoViewHolder extends RecyclerView.ViewHolder {
        private TextView idClube;
        private TextView data;
        private TextView valor;
        private TextView local;

        public NossoViewHolder(View itemView) {
            super(itemView);
            this.idClube = (TextView) itemView.findViewById(R.id.viewPartidaTitulo);
            this.data = (TextView) itemView.findViewById(R.id.viewDataCardIngresso);
            this.valor = (TextView) itemView.findViewById(R.id.viewValorCardIngresso);
            this.local = (TextView) itemView.findViewById(R.id.viewLocalCardIngresso);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "Item Clicado", Toast.LENGTH_LONG).show();
                }
            });
        }
    }

}
