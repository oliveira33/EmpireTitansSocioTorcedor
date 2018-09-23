package com.example.gustavooliveira.empiretitanssociotorcedor.Layouts.Fragments;


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

        carregarDados();

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

        btAttCadastro = mView.findViewById(R.id.btAttCadastro);
        btAttCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(txtSenha.equals(txtSenhaConfirm) && openDialogPassword()) {
                    realizarAtualizacao();
                } else {
                    Toast.makeText(getContext(), "As senhas não conferem!", Toast.LENGTH_LONG).show();
                }

            }
        });

        return mView;
    }

    private boolean openDialogPassword() {

        return false;
    }

    private void realizarAtualizacao() {
        Usuario usuarioAtt = new Usuario();
    }

    private void carregarDados() {

        viewNome = mView.findViewById(R.id.viewNomeAtt);
        viewNome.setText("Nome: " + Usuario.getPrincipal().getNome());
        viewSobrenome = mView.findViewById(R.id.viewSobrenomeAtt);
        viewSobrenome.setText("Sobrenome: " + Usuario.getPrincipal().getSobrenome());
        viewEmail = mView.findViewById(R.id.viewEmailAtt);
        viewEmail.setText("E-Mail: " + Usuario.getPrincipal().getEmail());
        viewData = mView.findViewById(R.id.viewDataNascimentoAtt);
        viewData.setText("Data de Nascimento: " + Usuario.getPrincipal().getDataNascimento());
        viewCpf = mView.findViewById(R.id.viewCpfAtt);
        viewCpf.setText("CPF: " + Usuario.getPrincipal().getCpf());
        viewTelefone = mView.findViewById(R.id.viewTelefoneAtt);
        viewTelefone.setText("Celular: " + Usuario.getPrincipal().getCelular());
        viewEndereco = mView.findViewById(R.id.viewEnderecoAtt);
        viewEndereco.setText("Endereço: " + Usuario.getPrincipal().getEndereco());
        viewCartao = mView.findViewById(R.id.viewCartaoAtt);
        viewCartao.setText("Número do cartão: " + Usuario.getPrincipal().getCartao());

    }


}
