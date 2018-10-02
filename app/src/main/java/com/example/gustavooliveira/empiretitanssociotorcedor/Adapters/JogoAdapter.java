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

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JogoAdapter extends RecyclerView.Adapter {

    private List<Partida> partidas;
    private Context context;
    private Map<String, Integer> imagens;

    public JogoAdapter(List<Partida> list, Context context) {
        this.partidas = list;
        this.context = context;
        imagens = new HashMap<String, Integer>();
        imagens.put("p1", R.drawable.time_a);
        imagens.put("p2", R.drawable.time_b);
        imagens.put("p3", R.drawable.time_c);
        imagens.put("p4", R.drawable.time_d);
    }

    @NonNull
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.celula_jogos, parent, false);
        NossoViewHolder holder = new NossoViewHolder(view);
        return holder;
    }

    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder nHolder, int position) {
        NossoViewHolder holder = (NossoViewHolder) nHolder;
        Partida p = partidas.get(position);
        // holder.imagem.setImageResource(imagens.get(0));
        holder.rival.setText(p.getClube().getNome());
        holder.horario.setText(new SimpleDateFormat("dd/MM").format(p.getData()) + " às " + new SimpleDateFormat("HH:mm").format(p.getData()));
        holder.local.setText(p.getLocal());
    }

    public int getItemCount() {
        return partidas.size();
    }


    public class NossoViewHolder extends RecyclerView.ViewHolder {

        private ImageView imagem;
        private TextView rival;
        private TextView horario;
        private TextView local;

        public NossoViewHolder(final View itemView) {
            super(itemView);
            imagem = (ImageView) itemView.findViewById(R.id.imgCardJogoRival);
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
