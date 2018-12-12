package br.com.helpdeskpim.helpdeskpim.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.com.helpdeskpim.helpdeskpim.R;
import br.com.helpdeskpim.helpdeskpim.utils.Singleton;

public class ResetarSenhaActivity extends AppCompatActivity implements View.OnClickListener {

    private Context context;
    private EditText edtEmail;
    private Button btnEnviar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resetar_senha);
        context = this;
        initToolbar();
        initComponents();
    }

    //inicializa toolbar
    private void initToolbar() {

        Toolbar toolbar = findViewById(R.id.toolbar_custom);
        toolbar.setTitleTextColor(context.getResources().getColor(R.color.RGBffffff));
        toolbar.setTitle(getString(R.string.activity_resetar_senha_str_title_toolbar));
        toolbar.setNavigationContentDescription(getString(R.string.acitvity_homr_str_txt_voltar));
        toolbar.setNavigationIcon(R.drawable.baseline_navigate_before_white);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    //inicializa componentes
    private void initComponents() {

        edtEmail = findViewById(R.id.activity_resetar_senha_edt_email);
        btnEnviar = findViewById(R.id.activity_resetar_senha_btn_enviar);

        btnEnviar.setOnClickListener(this);

    }

    private void validationValues() {
        String email = String.valueOf(edtEmail.getText());

        if (email == null || email.equalsIgnoreCase("")) {
            Singleton.getInstance().alert(context, getString(R.string.activity_resetar_senha_str_msg));
        } else {
            Singleton.getInstance().alert(context,getString(R.string.activity_resetar_senha_str_msg_sucesso));
            finish();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_resetar_senha_btn_enviar:
                validationValues();
                break;

        }
    }
}
