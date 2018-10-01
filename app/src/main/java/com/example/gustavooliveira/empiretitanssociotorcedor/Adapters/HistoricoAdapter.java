package com.example.gustavooliveira.empiretitanssociotorcedor.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gustavooliveira.empiretitanssociotorcedor.Models.Historico;
import com.example.gustavooliveira.empiretitanssociotorcedor.R;

import java.text.SimpleDateFormat;
import java.util.List;

public class HistoricoAdapter extends RecyclerView.Adapter {

    private List<Historico> historicos;
    private Context context;

    public HistoricoAdapter(List<Historico> historicos, Context context) {
        this.historicos = historicos;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.celula_jogos, parent, false);
        NossoViewHolder holder = new NossoViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder nholder, int position) {
        SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
        NossoViewHolder holder = (NossoViewHolder) nholder;
        Historico h = historicos.get(position);
        holder.txtCardJogoRival.setText(h.getPartida().getClube().getNome());
        holder.txtCardJogoHorario.setText(new SimpleDateFormat("HH:mm").format(h.getData()));
        holder.txtCardJogoData.setText(new SimpleDateFormat("dd/MM/yyyy").format(h.getData()));
        // holder.txtCardJogoDisponibilidade.setText(); ?
        holder.txtCardJogoLocal.setText("Local: " + h.getPartida().getLocal());
    }

    @Override
    public int getItemCount() {
        return historicos.size();
    }

    public class NossoViewHolder extends RecyclerView.ViewHolder {
        private TextView txtCardJogoRival;
        private TextView txtCardJogoHorario;
        private TextView txtCardJogoData;
        private TextView txtCardJogoDisponibilidade;
        private TextView txtCardJogoLocal;

        public NossoViewHolder(View itemView) {
            super(itemView);
            this.txtCardJogoRival = (TextView) itemView.findViewById(R.id.txtCardJogoRival);
            this.txtCardJogoHorario = (TextView) itemView.findViewById(R.id.txtCardJogoHorario);
            this.txtCardJogoData = (TextView) itemView.findViewById(R.id.txtCardJogoData);
            this.txtCardJogoDisponibilidade = (TextView) itemView.findViewById(R.id.txtCardJogoDisponibilidade);
            this.txtCardJogoLocal = (TextView) itemView.findViewById(R.id.txtCardJogoLocal);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "Item Clicado", Toast.LENGTH_LONG).show();
                }
            });
        }
    }

}
