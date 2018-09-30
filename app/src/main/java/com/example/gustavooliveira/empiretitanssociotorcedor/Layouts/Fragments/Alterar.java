package com.example.gustavooliveira.empiretitanssociotorcedor.Layouts.Fragments;


import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gustavooliveira.empiretitanssociotorcedor.Models.Usuario;
import com.example.gustavooliveira.empiretitanssociotorcedor.R;
import com.example.gustavooliveira.empiretitanssociotorcedor.Salesforce.UsuarioSF;
import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * A simple {@link Fragment} subclass.
 */
public class Alterar extends Fragment {

    private EditText txtNome;
    private EditText txtSobrenome;
    private EditText txtEmail;
    private EditText txtData;
    private EditText txtCpf;
    private EditText txtTelefone;
    private EditText txtEndereco;
    private EditText txtSenha;
    private EditText txtSenhaConfirm;
    private EditText txtCartao;
    private TextView viewNome;
    private TextView viewSobrenome;
    private TextView viewEmail;
    private TextView viewData;
    private TextView viewCpf;
    private TextView viewTelefone;
    private TextView viewEndereco;
    private TextView viewCartao;

    private View mView;
    private ImageButton btAttCadastro;

    public Alterar() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.fragment_alterar, container, false);

        txtNome = mView.findViewById(R.id.txtNomeAtt);
        txtSobrenome = mView.findViewById(R.id.txtSobrenomeAtt);
        txtEmail = mView.findViewById(R.id.txtEmailAtt);
        txtData = mView.findViewById(R.id.txtDataNascimentoAtt);
        txtCpf = mView.findViewById(R.id.txtCpfAtt);
        txtTelefone = mView.findViewById(R.id.txtTelefoneAtt);
        txtEndereco = mView.findViewById(R.id.txtEnderecoAtt);
        txtSenha = mView.findViewById(R.id.txtSenhaAtt);
        txtSenhaConfirm = mView.findViewById(R.id.txtSenhaConfirmAtt);
        txtCartao = mView.findViewById(R.id.txtCartaoAtt);

        carregarDados();
        aplicarMascaras();

        btAttCadastro = mView.findViewById(R.id.btAttCadastro);
        btAttCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txtSenha.getText().toString().equals(txtSenhaConfirm.getText().toString()) && validarCampos()) {
                    openDialogPassword();
                } else {
                    Toast.makeText(getContext(), "Confira os campos, algo está errado!", Toast.LENGTH_LONG).show();
                }
            }
        });

        return mView;
    }

    private void aplicarMascaras() {
        //Mascara Data de Nascimento
        SimpleMaskFormatter mask = new SimpleMaskFormatter("NN/NN/NNNN");
        MaskTextWatcher mtw = new MaskTextWatcher(txtData, mask);
        txtData.addTextChangedListener(mtw);

        //Mascara CPF
        mask = new SimpleMaskFormatter("NNN.NNN.NNN-NN");
        mtw = new MaskTextWatcher(txtCpf, mask);
        txtCpf.addTextChangedListener(mtw);

        //Mascara Celular
        mask = new SimpleMaskFormatter("(NN) NNNNN-NNNN");
        mtw = new MaskTextWatcher(txtTelefone, mask);
        txtTelefone.addTextChangedListener(mtw);

        //Mascara Cartão
        mask = new SimpleMaskFormatter("NNNN NNNN NNNN NNNN");
        mtw = new MaskTextWatcher(txtCartao, mask);
        txtCartao.addTextChangedListener(mtw);
    }

    private void openDialogPassword() {
        final Dialog dialog = new Dialog(mView.getContext());
        dialog.setTitle("Confirmar Alteração");
        dialog.setContentView(R.layout.layout_dialog_password);
        dialog.setCancelable(true);
        final EditText senha = (EditText) dialog.findViewById(R.id.txtSenhaDialog);
        Button cadastrar = (Button) dialog.findViewById(R.id.btConfirmaDialog);
        Button cancelar = (Button) dialog.findViewById(R.id.btCancelaDialog);

        cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (senha.getText().toString().equals(Usuario.getPrincipal().getSenha())) {
                    realizarAtualizacao();
                    dialog.dismiss();
                } else {
                    Toast.makeText(getContext(), "Senha incorreta!!!", Toast.LENGTH_LONG).show();
                }
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

    private void realizarAtualizacao() {
        final String senha = txtSenha.getText().toString().trim().isEmpty() ? Usuario.getPrincipal().getSenha() : txtSenha.getText().toString().trim();

        new Thread() {
            @Override
            public void run() {
                try {
                    new UsuarioSF().alterar(new Usuario(Usuario.getPrincipal().getId(), txtEmail.getText().toString(), senha, txtNome.getText().toString(),
                            txtSobrenome.getText().toString(), new SimpleDateFormat("dd/MM/yyyy").parse(txtData.getText().toString()), txtCpf.getText().toString(), txtEndereco.getText().toString(),
                            txtTelefone.getText().toString(), txtCartao.getText().toString(), "", 'U'));
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getContext(), "Dados alterados com sucesso!", Toast.LENGTH_LONG).show();
                        }
                    });
                } catch (final Exception e) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
        }.start();
    }

    private void carregarDados() {
        SimpleDateFormat out = new SimpleDateFormat("dd/MM/yyyy");

        viewEmail = mView.findViewById(R.id.viewEmailAtt);
        viewData = mView.findViewById(R.id.viewDataNascimentoAtt);
        viewCpf = mView.findViewById(R.id.viewCpfAtt);
        viewTelefone = mView.findViewById(R.id.viewTelefoneAtt);
        viewCartao = mView.findViewById(R.id.viewCartaoAtt);
        txtNome.setText(Usuario.getPrincipal().getNome());
        txtSobrenome.setText(Usuario.getPrincipal().getSobrenome());
        txtEmail.setText(Usuario.getPrincipal().getEmail());
        txtData.setText(out.format(Usuario.getPrincipal().getDataNascimento()));
        txtCpf.setText(Usuario.getPrincipal().getCpf());
        txtTelefone.setText(Usuario.getPrincipal().getTelefone());
        txtEndereco.setText(Usuario.getPrincipal().getEndereco());
        txtCartao.setText(Usuario.getPrincipal().getCartao());

    }

    private boolean validarCampos() {
        boolean status = true;

        if (txtData.length() != 10) {
            viewData.setTextColor(Color.RED);
            viewData.setText("*Data de Nascimento:");
            status = false;
        } else {
            viewData.setTextColor(Color.GRAY);
            viewData.setText("Data de Nascimento:");
        }

        if (txtCpf.length() != 14) {
            viewCpf.setTextColor(Color.RED);
            viewCpf.setText("*CPF:");
            status = false;
        } else {
            viewCpf.setTextColor(Color.GRAY);
            viewCpf.setText("CPF:");
        }

        if (txtTelefone.length() != 15 && txtTelefone.length() != 14) {
            viewTelefone.setTextColor(Color.RED);
            viewTelefone.setText("*Celular:");
            status = false;
        } else {
            viewTelefone.setTextColor(Color.GRAY);
            viewTelefone.setText("Celular:");
        }

        if (txtCartao.length() != 19) {
            viewCartao.setTextColor(Color.RED);
            viewCartao.setText("*Número do cartão:");
            status = false;
        } else {
            viewCartao.setTextColor(Color.GRAY);
            viewCartao.setText("Número do cartão:");
        }

        return status;
    }

}
