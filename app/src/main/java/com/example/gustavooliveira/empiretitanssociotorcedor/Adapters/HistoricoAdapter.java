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

import org.w3c.dom.Text;

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
        View view = LayoutInflater.from(context).inflate(R.layout.celula_historico, parent, false);
        NossoViewHolder holder = new NossoViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder nholder, int position) {
        NossoViewHolder holder = (NossoViewHolder) nholder;
        Historico h = historicos.get(position);
        holder.txtPartidaHistoricoCard.setText("Empire Titans x "+h.getPartida().getClube().getNome());
        holder.txtDataHitoricoCard.setText(new SimpleDateFormat("dd/MM/yyyy").format(h.getData()));
        holder.txtLocalHistoricoCard.setText(h.getPartida().getLocal());
        holder.txtValorHistoricoCard.setText("R$"+String.format("%,.2f", h.getPartida().getValor()));
        SimpleDateFormat sdfData = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat sdfHora = new SimpleDateFormat("HH:mm");
        holder.txtAquisicao.setText("Ingresso adquirido em: "+sdfData.format(h.getData())+" às "+sdfHora.format(h.getData()));
    }

    @Override
    public int getItemCount() {
        return historicos.size();
    }

    public class NossoViewHolder extends RecyclerView.ViewHolder {
        private TextView txtPartidaHistoricoCard;
        private TextView txtDataHitoricoCard;
        private TextView txtLocalHistoricoCard;
        private TextView txtValorHistoricoCard;
        private TextView txtAquisicao;


        public NossoViewHolder(View itemView) {
            super(itemView);
            this.txtPartidaHistoricoCard = (TextView) itemView.findViewById(R.id.txtPartidaHistoricoCard);
            this.txtDataHitoricoCard = (TextView) itemView.findViewById(R.id.txtDataHitoricoCard);
            this.txtLocalHistoricoCard = (TextView) itemView.findViewById(R.id.txtLocalHitoricoCard);
            this.txtValorHistoricoCard = (TextView) itemView.findViewById(R.id.txtValorHistoricoCard);
            this.txtAquisicao = (TextView) itemView.findViewById(R.id.viewDataAdquirido);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "Item Clicado", Toast.LENGTH_LONG).show();
                }
            });
        }
    }

}
