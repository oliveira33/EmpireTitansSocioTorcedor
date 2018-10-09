package com.example.gustavooliveira.empiretitanssociotorcedor.Layouts;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gustavooliveira.empiretitanssociotorcedor.Models.Usuario;
import com.example.gustavooliveira.empiretitanssociotorcedor.R;
import com.example.gustavooliveira.empiretitanssociotorcedor.Salesforce.UsuarioSF;
import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Cadastro extends AppCompatActivity {

    private Button btConfirma;
    private Button btCancelar;
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
    private EditText txtCodSeguranca;
    private EditText txtDataValCartao;
    private EditText txtNomeCartao;
    private TextView viewData;
    private TextView viewCpf;
    private TextView viewTelefone;
    private TextView viewCartao;
    private CheckBox checkTermos;
    private TextView viewNome;
    private TextView viewSobrenome;
    private TextView viewEmail;
    private TextView viewEndereco;
    private TextView viewCodSeguranca;
    private TextView viewSenha;
    private TextView viewDataValCartao;
    private TextView viewNomeCartao;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        btConfirma = (Button) findViewById(R.id.btConfirma);
        btCancelar = (Button) findViewById(R.id.btCancelar);
        txtNome = (EditText) findViewById(R.id.txtNome);
        txtSobrenome = (EditText) findViewById(R.id.txtSobrenome);
        txtEmail = (EditText) findViewById(R.id.txtEmail);
        txtData = (EditText) findViewById(R.id.txtData);
        txtCpf = (EditText) findViewById(R.id.txtCpf);
        txtTelefone = (EditText) findViewById(R.id.txtTelefone);
        txtEndereco = (EditText) findViewById(R.id.txtEndereco);
        txtSenha = (EditText) findViewById(R.id.txtSenha);
        txtSenhaConfirm = (EditText) findViewById(R.id.txtSenhaConfirm);
        txtCartao = (EditText) findViewById(R.id.txtCartao);
        txtCodSeguranca = (EditText) findViewById(R.id.txtCodSegurancaDialog);
        viewData = (TextView) findViewById(R.id.viewData);
        viewCpf = (TextView) findViewById(R.id.viewCpf);
        viewTelefone = (TextView) findViewById(R.id.viewTelefone);
        viewCartao = (TextView) findViewById(R.id.viewCartao);
        checkTermos = (CheckBox) findViewById(R.id.checkTermos);
        viewNome = (TextView) findViewById(R.id.viewNome);
        viewSobrenome = (TextView) findViewById(R.id.viewSobrenome);
        viewEmail = (TextView) findViewById(R.id.viewEmail);
        viewEndereco = (TextView) findViewById(R.id.viewEndereco);
        viewCodSeguranca = (TextView) findViewById(R.id.viewCodSeguranca);
        viewSenha = (TextView) findViewById(R.id.viewSenha);
        progressBar = (ProgressBar) findViewById(R.id.progressBarCadastro);
        txtDataValCartao = (EditText) findViewById(R.id.txtVencimentoCartao);
        txtNomeCartao = (EditText) findViewById(R.id.txtNomeTitular);
        viewDataValCartao = (TextView) findViewById(R.id.viewVencimentoCartao);
        viewNomeCartao = (TextView) findViewById(R.id.viewNomeTitular);

        aplicarMascaras();

        btConfirma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validarCampos()) {
                    progressBar.setVisibility(View.VISIBLE);
                    try {
                        cadastrar(validar());
                    } catch (Exception ex) {
                        Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
                    } finally {
                        progressBar.setVisibility(View.INVISIBLE);
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Verifique os campos incorretos!", Toast.LENGTH_LONG).show();
                }
            }
        });

        btCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private boolean validarCampos() {
        boolean status = true;

        if(txtNome.length() <= 0) {
            viewNome.setTextColor(Color.RED);
            viewNome.setText("*Nome");
            status = false;
        } else {
            viewNome.setTextColor(Color.WHITE);
            viewNome.setText("Nome");
        }

        if(txtSobrenome.length() <= 0) {
            viewSobrenome.setTextColor(Color.RED);
            viewSobrenome.setText("*Sobrenome");
            status = false;
        } else {
            viewNome.setTextColor(Color.WHITE);
            viewNome.setText("Sobrenome");
        }

        if (txtEmail. length() <= 5 || !txtEmail.getText().toString().contains("@") || !txtEmail.getText().toString().contains(".")) {
            viewEmail.setTextColor(Color.RED);
            viewEmail.setText("*E-Mail");
            status = false;
        } else {
            viewEmail.setTextColor(Color.WHITE);
            viewEmail.setText("E-Mail");
        }

        if (txtEndereco.length() <= 0) {
            viewEndereco.setTextColor(Color.RED);
            viewEndereco.setText("*Endereço");
            status = false;
        } else {
            viewEndereco.setTextColor(Color.WHITE);
            viewEndereco.setText("Endereço");
        }

        if (txtCodSeguranca.length() != 3) {
            viewCodSeguranca.setTextColor(Color.RED);
            viewCodSeguranca.setText("*Código de Segurança");
            status = false;
        } else {
            viewCodSeguranca.setTextColor(Color.WHITE);
            viewCodSeguranca.setText("Código de Segurança");
        }

        if (txtData.length() != 10) {
            viewData.setTextColor(Color.RED);
            viewData.setText("*Data de Nascimento");
            status = false;
        } else {
            viewData.setTextColor(Color.WHITE);
            viewData.setText("Data de Nascimento");
        }

        if (txtCpf.length() != 14) {
            viewCpf.setTextColor(Color.RED);
            viewCpf.setText("*CPF");
            status = false;
        } else {
            viewCpf.setTextColor(Color.WHITE);
            viewCpf.setText("CPF");
        }

        if (txtTelefone.length() != 15 && txtTelefone.length() != 14) {
            viewTelefone.setTextColor(Color.RED);
            viewTelefone.setText("*Telefone de Contato");
            status = false;
        } else {
            viewTelefone.setTextColor(Color.WHITE);
            viewTelefone.setText("Telefone de Contato");
        }

        if (txtCartao.length() != 19) {
            viewCartao.setTextColor(Color.RED);
            viewCartao.setText("*Cartão");
            status = false;
        } else {
            viewCartao.setTextColor(Color.WHITE);
            viewCartao.setText("Cartão");
        }

        if (!txtSenha.getText().toString().equals(txtSenhaConfirm.getText().toString()) || txtSenha.length() < 3) {
            viewSenha.setTextColor(Color.RED);
            viewSenha.setText("*Senha");
            status = false;
        } else {
            viewSenha.setTextColor(Color.WHITE);
            viewSenha.setText("Senha");
        }

        if (!checkTermos.isChecked()) {
            checkTermos.setTextColor(Color.RED);
            checkTermos.setText("*Concordo com os Termos de Uso");
            status = false;
        } else {
            checkTermos.setTextColor(Color.WHITE);
            checkTermos.setText("Concordo com os Termos de Uso");
        }

        if(txtNomeCartao.length() <= 0) {
            viewNomeCartao.setTextColor(Color.RED);
            viewNomeCartao.setText("*Nome do Titular do Cartão");
            status = false;
        } else {
            viewNomeCartao.setTextColor(Color.WHITE);
            viewNomeCartao.setText("Nome do Titular do Cartão");
        }

        if(txtDataValCartao.length() != 5) {
            viewDataValCartao.setTextColor(Color.RED);
            viewDataValCartao.setText("*Data de Vencimento do Cartão");
            status = false;
        } else {
            viewDataValCartao.setTextColor(Color.WHITE);
            viewDataValCartao.setText("Data de Vencimento do Cartão");
        }

        return status;
    }

    public Usuario validar() throws Exception {
        String email = txtEmail.getText().toString();
        if (email.isEmpty())
            throw new Exception("O e-mail não foi informado");

        Date data = null;
        String dataString = txtData.getText().toString();
        if (dataString.isEmpty())
            throw new Exception("A data de nascimento não foi informada");

        try {
            data = new SimpleDateFormat("dd/MM/yyyy").parse(dataString);
        } catch (ParseException e) {
            throw new Exception("A data de nascimento informada é inválida");
        }

        String senha = txtSenha.getText().toString();
        if (!senha.equals(txtSenhaConfirm.getText().toString()))
            throw new Exception("As senhas não conferem");

        String codSeguranca = txtCodSeguranca.getText().toString();
        if (codSeguranca.isEmpty())
            throw new Exception("O código de segurança não foi informado");

        if (codSeguranca.length() != 3)
            throw new Exception("O código de segurança é inválido");

        for (char c : codSeguranca.toCharArray()) {
            if (!Character.isDigit(c))
                throw new Exception("O código de segurança é inválido");
        }

        Date dataValCartao = null;
        String dataValCartaoString = txtDataValCartao.getText().toString();
        if (dataValCartaoString.isEmpty())
            throw new Exception("A data de validade do cartão não foi preenchida");

        try {
            dataValCartao = new SimpleDateFormat("MM/yy").parse(dataValCartaoString);
        } catch (ParseException e) {
            throw new Exception("A data de validade do cartão informada é inválida");
        }

        return new Usuario(email, senha, txtNome.getText().toString(), txtSobrenome.getText().toString(), data, txtCpf.getText().toString(), txtEndereco.getText().toString(),
                txtTelefone.getText().toString(), txtCartao.getText().toString(), codSeguranca, 'U', dataValCartao, txtNomeCartao.getText().toString());
    }

    public void cadastrar(final Usuario usuario) {
        new Thread() {
            @Override
            public void run() {
                try {
                    new UsuarioSF().cadastrar(usuario);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setVisibility(View.INVISIBLE);
                            Intent intent = new Intent(Cadastro.this, CadastroRealizado.class);
                            startActivity(intent);
                            finish();
                        }
                    });
                } catch (final Exception ex) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setVisibility(View.INVISIBLE);
                            Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
        }.start();
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

        //Mascara Cod Segurança
        mask = new SimpleMaskFormatter("NNN");
        mtw = new MaskTextWatcher(txtCodSeguranca, mask);
        txtCodSeguranca.addTextChangedListener(mtw);

        //Mascara Venc Cartao
        mask = new SimpleMaskFormatter("NN/NN");
        mtw = new MaskTextWatcher(txtDataValCartao, mask);
        txtDataValCartao.addTextChangedListener(mtw);
    }

}
