package com.example.gustavooliveira.empiretitanssociotorcedor.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gustavooliveira.empiretitanssociotorcedor.Models.Partida;
import com.example.gustavooliveira.empiretitanssociotorcedor.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JogoAdapter extends RecyclerView.Adapter {

    private List<Partida> list;
    private Context context;

    public JogoAdapter(List<Partida> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.celula_jogos, parent, false);
        NossoViewHolder holder = new NossoViewHolder(view);
        return holder;
    }

    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder nHolder, int position) {
        NossoViewHolder holder = (NossoViewHolder) nHolder;
        Partida p = list.get(position);
        holder.rival.setText(p.getIdClube());
        holder.horario.setText("???");
        holder.local.setText(p.getLocal());
    }

    public int getItemCount() {
        return list.size();
    }


    public class NossoViewHolder extends RecyclerView.ViewHolder {

        private ImageView imagem;
        private TextView rival;
        private TextView horario;
        private TextView local;

        public NossoViewHolder(final View itemView) {
            super(itemView);
            rival = (TextView) itemView.findViewById(R.id.txtCardJogoRival);
            horario = (TextView) itemView.findViewById(R.id.txtCardJogoHorario);
            local = (TextView) itemView.findViewById(R.id.txtCardJogoLocal);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //O que acontece ao ser clicado
                    Toast.makeText(context, "Partida Info", Toast.LENGTH_SHORT).show();
                }
            });


        }
    }

}
