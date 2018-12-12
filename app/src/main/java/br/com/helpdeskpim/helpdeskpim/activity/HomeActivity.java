package br.com.helpdeskpim.helpdeskpim.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import br.com.helpdeskpim.helpdeskpim.R;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    private Context context;
    private View btnAbriOS;
    private View btnConsultOS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        context = this;
        initToolbar();
        initComponents();
    }

    private void initToolbar(){

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_custom);
        toolbar.setTitleTextColor(context.getResources().getColor(R.color.RGBffffff));
        toolbar.setTitle(getString(R.string.activity_home_str_txt_home));
        toolbar.setNavigationIcon(R.drawable.baseline_navigate_before_white);
        toolbar.setNavigationContentDescription(getString(R.string.acitvity_homr_str_txt_voltar));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initComponents(){
        btnAbriOS = findViewById(R.id.cardview_home_btn_abrir_chamado);
        btnConsultOS = findViewById(R.id.cardview_home_consultar_chamado);

        btnAbriOS.setOnClickListener(this);
        btnConsultOS.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()){
            case R.id.cardview_home_btn_abrir_chamado:

                intent = new Intent(context, AbrirChamadoActivity.class);
                startActivity(intent);

                break;

            case R.id.cardview_home_consultar_chamado:

                intent = new Intent(context, ConsultarChamadoActivity.class);
                startActivity(intent);

                break;
        }
    }
}
