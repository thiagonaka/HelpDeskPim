package br.com.helpdeskpim.helpdeskpim.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import br.com.helpdeskpim.helpdeskpim.R;
import br.com.helpdeskpim.helpdeskpim.adapter.OrdemServicoAdapter;
import br.com.helpdeskpim.helpdeskpim.bean.OrdemServicoBean;
import br.com.helpdeskpim.helpdeskpim.utils.UtilsPreference;

public class ConsultarChamadoActivity extends AppCompatActivity {

    private Context context;
    private List<OrdemServicoBean> ordemServicoList;
    private OrdemServicoAdapter adapter;
    private RecyclerView ordemServicoRecycler;
    private TextView txtMsgListaVazia;
    private boolean edit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_chamado);
        context = this;
        UtilsPreference.saveEdit(false);
        initToolbar();
        initComponents();
    }

    @Override
    protected void onResume() {
        super.onResume();
        edit = UtilsPreference.getEdit();
        if (edit){
            initList();
        }
    }

    private void initToolbar(){

        Toolbar toolbar = findViewById(R.id.toolbar_custom);
        toolbar.setTitleTextColor(context.getResources().getColor(R.color.RGBffffff));
        toolbar.setNavigationContentDescription(getString(R.string.acitvity_homr_str_txt_voltar));
        toolbar.setTitle(getString(R.string.activity_consultar_chamado_str_title_consultar));
        toolbar.setNavigationIcon(R.drawable.baseline_navigate_before_white);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initComponents(){
        ordemServicoRecycler = findViewById(R.id.activity_consultar_chamado_recy);
        txtMsgListaVazia = findViewById(R.id.activity_consultar_chamado_msg_vazia);
        ordemServicoRecycler.setLayoutManager(new LinearLayoutManager(context));
        ordemServicoRecycler.setItemAnimator(new DefaultItemAnimator());
        ordemServicoRecycler.setHasFixedSize(true);

        initList();

    }

    private void initList(){

        String json = UtilsPreference.getOrdemServicoList();

        if (json != null && !json.equalsIgnoreCase("") && !json.equalsIgnoreCase("[]")){
            txtMsgListaVazia.setVisibility(View.GONE);
            ordemServicoRecycler.setVisibility(View.VISIBLE);
            Gson gson = new GsonBuilder().create();
            ordemServicoList = (ArrayList<OrdemServicoBean>) gson.fromJson(json,
                    new TypeToken<ArrayList<OrdemServicoBean>>(){}.getType());
            adapter = new OrdemServicoAdapter(context, ordemServicoList,onClickOrdemServico());
            ordemServicoRecycler.setAdapter(adapter);

        }else {

            txtMsgListaVazia.setVisibility(View.VISIBLE);
            ordemServicoRecycler.setVisibility(View.GONE);

        }
    }

    private void callDialog(final int pos){

        final AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(context, android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(context);
        }
        builder.setTitle(getString(R.string.activity_consultar_chamado_srt_msg_titulo_excluir))
                .setMessage(getString(R.string.activity_consultar_chamado_srt_msg_excluir))
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        ordemServicoList.remove(pos);
                        Gson gsonFarma = new Gson();
                        String jsonReceita = gsonFarma.toJson(ordemServicoList);
                        UtilsPreference.setOrdemServicoList(jsonReceita);
                        adapter.notifyDataSetChanged();
                        initList();
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    private OrdemServicoAdapter.OrdemServicoClickListener onClickOrdemServico() {
        return new OrdemServicoAdapter.OrdemServicoClickListener() {
            @Override
            public void onClickOrdemServico(View view, int idx) {
                OrdemServicoBean osb = ordemServicoList.get(idx);
                Intent intent = new Intent(context, AbrirChamadoActivity.class);
                intent.putExtra("isEdtOS",true);
                intent.putExtra("pos",idx);
                startActivity(intent);

            }

            @Override
            public void onLongClickOrdemServico(View view, int idx) {
                callDialog(idx);
            }

        };
    }

}
