package br.com.helpdeskpim.helpdeskpim.activity;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EdgeEffect;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import br.com.helpdeskpim.helpdeskpim.R;
import br.com.helpdeskpim.helpdeskpim.bean.OrdemServicoBean;
import br.com.helpdeskpim.helpdeskpim.utils.Mask;
import br.com.helpdeskpim.helpdeskpim.utils.Singleton;
import br.com.helpdeskpim.helpdeskpim.utils.UtilsPreference;

public class AbrirChamadoActivity extends AppCompatActivity implements View.OnClickListener {

    private Context context;
    private EditText edtDescricao;
    private Button btnAnexar;
    private Button btnEnviar;
    private int countId = 0;
    private String idOrdemServico = "0";
    private List<OrdemServicoBean> ordemServicoBeanList;
    private EditText edtNome;
    private EditText edtEmail;
    private EditText edtTel;
    private EditText edtLocal;
    private EditText edtTitulo;
    private String nomeRespOrdemServico;
    private String emailOrdemServico;
    private String telOrdemServico;
    private String localOrdemServico;
    private String tituloOrdemServico;
    private String descOrdemServico;
    private boolean isEdt;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abrir_chamado);
        context = this;

        isEdt = getIntent().getBooleanExtra("isEdtOS", false);

        initIdOrdemServico();
        initToolbar();
        initComponents();
    }

    private void initIdOrdemServico() {
        idOrdemServico = UtilsPreference.getContadorOs();
        if (idOrdemServico == null || idOrdemServico.equalsIgnoreCase("")) {
            countId = 0;
            idOrdemServico = "0";
        } else {
            countId = Integer.parseInt(idOrdemServico);
        }
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_custom);
        toolbar.setTitleTextColor(context.getResources().getColor(R.color.RGBffffff));

        if (isEdt){
            toolbar.setTitle(getString(R.string.activity_abrir_chamado_str_title_editar_ordem_servico));
        }else {
            toolbar.setTitle(getString(R.string.activity_abrir_chamado_str_title_abrir_chamado));
        }

        toolbar.setNavigationContentDescription(getString(R.string.acitvity_homr_str_txt_voltar));
        toolbar.setNavigationIcon(R.drawable.baseline_navigate_before_white);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initEditComponents() {

        String json = UtilsPreference.getOrdemServicoList();
        Gson gson = new GsonBuilder().create();
        position = getIntent().getIntExtra("pos", position);
        ordemServicoBeanList = (ArrayList<OrdemServicoBean>)
                gson.fromJson(json, new TypeToken<ArrayList<OrdemServicoBean>>() {
                }.getType());

        edtNome.setText(ordemServicoBeanList.get(position).getNome());
        edtEmail.setText(ordemServicoBeanList.get(position).getEmail());
        edtTel.setText(ordemServicoBeanList.get(position).getTelefone());
        edtLocal.setText(ordemServicoBeanList.get(position).getLocal());
        edtTitulo.setText(ordemServicoBeanList.get(position).getTitulo());
        edtDescricao.setText(ordemServicoBeanList.get(position).getDescricao());

    }

    private void initComponents() {
        edtDescricao = findViewById(R.id.activity_abrir_chamado_edt_descricao);
        btnEnviar = findViewById(R.id.activity_abrir_chamado_btn_enviar);
        btnAnexar = findViewById(R.id.activity_abrir_chamado_btn_anexar);
        edtNome = findViewById(R.id.activity_abrir_chamado_edt_nome);
        edtTel = findViewById(R.id.activity_abrir_chamado_edt_telefone);
        edtEmail = findViewById(R.id.activity_abrir_chamado_edt_email);
        edtLocal = findViewById(R.id.activity_abrir_chamado_edt_local);
        edtTitulo = findViewById(R.id.activity_abrir_chamado_edt_titulo);
        edtTel.addTextChangedListener(Mask.insert("(##)####-####", edtTel));
        btnEnviar.setOnClickListener(this);

        if (isEdt) {
            initEditComponents();
        }
    }

    private boolean camposValidados() {
        if (!campoPreenchido(nomeRespOrdemServico)) {
            Singleton.getInstance().alert(context, getString(R.string.activity_abrir_chamado_str_msg_nome));
            return false;
        }
        if (!campoPreenchido(emailOrdemServico)) {
            Singleton.getInstance().alert(context, getString(R.string.activity_abrir_chamado_str_msg_email));
            return false;
        }
        if (!campoPreenchido(telOrdemServico)) {
            Singleton.getInstance().alert(context, getString(R.string.activity_abrir_chamado_str_msg_tel));
            return false;
        }
        if (!campoPreenchido(localOrdemServico)) {
            Singleton.getInstance().alert(context, getString(R.string.activity_abrir_chamado_str_msg_local));
            return false;
        }
        if (!campoPreenchido(tituloOrdemServico)) {
            Singleton.getInstance().alert(context, getString(R.string.activity_abrir_chamado_str_msg_titulo));
            return false;
        }
        if (!campoPreenchido(descOrdemServico)) {
            Singleton.getInstance().alert(context, getString(R.string.activity_abrir_chamado_str_msg_descr));
            return false;
        }
        return true;
    }

    private boolean campoPreenchido(String campo) {
        if (campo == null || campo.equals("")) {
            return false;
        }
        return true;
    }

    private void validationEditValues() {

        //Pegando as strings do componentes
        nomeRespOrdemServico = String.valueOf(edtNome.getText());
        telOrdemServico = String.valueOf(edtTel.getText());
        emailOrdemServico = String.valueOf(edtEmail.getText());
        localOrdemServico = String.valueOf(edtLocal.getText());
        tituloOrdemServico = String.valueOf(edtTitulo.getText());
        descOrdemServico = String.valueOf(edtDescricao.getText());


        if (camposValidados()) {
            String json = UtilsPreference.getOrdemServicoList();
            Gson gson = new GsonBuilder().create();

            ordemServicoBeanList = (ArrayList<OrdemServicoBean>)
                    gson.fromJson(json, new TypeToken<ArrayList<OrdemServicoBean>>() {
                    }.getType());

            ordemServicoBeanList.get(position).setNome(nomeRespOrdemServico);
            ordemServicoBeanList.get(position).setEmail(emailOrdemServico);
            ordemServicoBeanList.get(position).setTelefone(telOrdemServico);
            ordemServicoBeanList.get(position).setLocal(localOrdemServico);
            ordemServicoBeanList.get(position).setTitulo(tituloOrdemServico);
            ordemServicoBeanList.get(position).setDescricao(descOrdemServico);

            Gson gsonOrdemServico = new Gson();
            String jsonOrdemServico = gsonOrdemServico.toJson(ordemServicoBeanList);
            UtilsPreference.setOrdemServicoList(jsonOrdemServico);
            UtilsPreference.saveEdit(true);
            finish();
            Singleton.getInstance().alert(context, getString(R.string.activity_abrir_chamado_str_msg_editar_sucesso));
        }


    }

    private void validationValues() {
        //Pegando as strings do componentes
        nomeRespOrdemServico = String.valueOf(edtNome.getText());
        telOrdemServico = String.valueOf(edtTel.getText());
        emailOrdemServico = String.valueOf(edtEmail.getText());
        localOrdemServico = String.valueOf(edtLocal.getText());
        tituloOrdemServico = String.valueOf(edtTitulo.getText());
        descOrdemServico = String.valueOf(edtDescricao.getText());


        if (camposValidados()) {
            countId++;
            idOrdemServico = String.valueOf(countId);
            UtilsPreference.setContadorPedido(idOrdemServico);

            List<OrdemServicoBean> ordemServico = new ArrayList<OrdemServicoBean>();

            String json = UtilsPreference.getOrdemServicoList();
            Gson gson = new GsonBuilder().create();

            ordemServicoBeanList = (ArrayList<OrdemServicoBean>)
                    gson.fromJson(json, new TypeToken<ArrayList<OrdemServicoBean>>() {
                    }.getType());

            if (ordemServicoBeanList != null) {
                ordemServico.addAll(ordemServicoBeanList);
            }

            ordemServico.add(new OrdemServicoBean(countId, nomeRespOrdemServico,
                    emailOrdemServico, telOrdemServico, localOrdemServico, tituloOrdemServico
                    , descOrdemServico));
            Gson gsonOrdemServico = new Gson();
            String jsonOrdemServico = gsonOrdemServico.toJson(ordemServico);

            UtilsPreference.setOrdemServicoList(jsonOrdemServico);
            finish();
            Singleton.getInstance().alert(context, getString(R.string.activity_abrir_chamado_str_msg_aberto_chamado));
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_abrir_chamado_btn_enviar:
                if (isEdt) {
                    validationEditValues();
                } else {
                    validationValues();
                }
                break;
        }
    }
}