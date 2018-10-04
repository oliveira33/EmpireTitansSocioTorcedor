package com.example.gustavooliveira.empiretitanssociotorcedor.Adapters;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gustavooliveira.empiretitanssociotorcedor.Models.Historico;
import com.example.gustavooliveira.empiretitanssociotorcedor.Models.Partida;
import com.example.gustavooliveira.empiretitanssociotorcedor.Models.Usuario;
import com.example.gustavooliveira.empiretitanssociotorcedor.R;
import com.example.gustavooliveira.empiretitanssociotorcedor.Salesforce.HistoricoSF;
import com.example.gustavooliveira.empiretitanssociotorcedor.Salesforce.UsuarioSF;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class IngressoAdapter extends RecyclerView.Adapter {

    private List<Partida> partidas;
    private Context context;
    private View view;

    public IngressoAdapter(List<Partida> partidas, Context context, View view) {
        this.partidas = partidas;
        this.context = context;
        this.view = view;
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
        NossoViewHolder holder = (NossoViewHolder) nHolder;
        Partida p = partidas.get(position);
        holder.id = p.getId();
        holder.nomeClube.setText(p.getClube().getNome());
        holder.data.setText(new SimpleDateFormat("dd/MM/yyyy").format(p.getData()));
        holder.local.setText("Local: " + p.getLocal());
        holder.valor.setText("R$ " + String.format("%.2f", p.getValor()));

    }

    @Override
    public int getItemCount() {
        return partidas.size();
    }

    public class NossoViewHolder extends RecyclerView.ViewHolder {
        private String id;
        private TextView nomeClube;
        private TextView data;
        private TextView valor;
        private TextView local;

        public NossoViewHolder(final View itemView) {
            super(itemView);
            this.nomeClube = (TextView) itemView.findViewById(R.id.viewPartidaTitulo);
            this.data = (TextView) itemView.findViewById(R.id.viewDataCardIngresso);
            this.valor = (TextView) itemView.findViewById(R.id.viewValorCardIngresso);
            this.local = (TextView) itemView.findViewById(R.id.viewLocalCardIngresso);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    final Dialog dialog = new Dialog(context);
                    dialog.setTitle("Confirmar Compra");
                    dialog.setContentView(R.layout.latout_dialog_ingresso);
                    dialog.setCancelable(false);

                    final TextView viewAdversario = (TextView) dialog.findViewById(R.id.viewDialogCompraAdversario);
                    final TextView viewData = (TextView) dialog.findViewById(R.id.viewDialogCompraData);
                    final TextView viewLocal = (TextView) dialog.findViewById(R.id.viewDialogCompraLocal);
                    final TextView viewValor = (TextView) dialog.findViewById(R.id.viewDialogCompraValor);
                    final TextView viewCartao = (TextView) dialog.findViewById(R.id.viewDialogCompraCartao);
                    Button confirmar = (Button) dialog.findViewById(R.id.btConfirmarDialogIngresso);
                    Button cancelar = (Button) dialog.findViewById(R.id.btCancelarDialogIngresso);

                    viewAdversario.setText("Empire Titans x "+nomeClube.getText());
                    viewData.setText(data.getText());
                    viewLocal.setText(local.getText());
                    viewValor.setText(valor.getText());
                    viewCartao.setText("Cartão: "+Usuario.getPrincipal().getCartao());

                    confirmar.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            realizarCompra(id);
                            dialog.dismiss();
                        }
                    });

                    cancelar.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });

                    dialog.show();

                }
            });
        }

        private void realizarCompra(final String idPartida) {

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            new HistoricoSF().cadastrar(new Historico(Usuario.getPrincipal().getId(), idPartida, new Date()));
                            Snackbar.make(view, "Ingresso comprado com sucesso!", Snackbar.LENGTH_LONG).show();
                        } catch (Exception e) {
                            e.printStackTrace();
                            Snackbar.make(view, "ATENÇÃO, ALGO DEU ERRADO!\nSeu transação NÃO foi efetivada com sucesso", Snackbar.LENGTH_LONG).show();
                        }
                    }
                }).start();

        }
    }

}
