package br.com.helpdeskpim.helpdeskpim.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import br.com.helpdeskpim.helpdeskpim.R;
import br.com.helpdeskpim.helpdeskpim.utils.Singleton;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Context context;
    private EditText edtLogin;
    private EditText edtSenha;
    private Button btnEntrar;
    private TextView btnEsqueciSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        context = this;

        //metodo inicializa componentes
        initComponents();

    }

    private void initComponents() {

        edtLogin = findViewById(R.id.activity_login_edt_login);
        edtSenha = findViewById(R.id.activity_login_edt_senha);
        btnEntrar = findViewById(R.id.activity_login_btn_entrar);
        btnEsqueciSenha = findViewById(R.id.activity_login_btn_esqueci_senha);

        btnEntrar.setOnClickListener(this);
        btnEsqueciSenha.setOnClickListener(this);

    }

    private void verifyCompontents() {


        String login = String.valueOf(edtLogin.getText());
        String senha = String.valueOf(edtSenha.getText());

        if ((login == null || login.equalsIgnoreCase(""))
                || (senha == null || senha.equalsIgnoreCase(""))) {

            Singleton.getInstance().alert(context, getString(R.string.activity_login_str_msg_login_vazio));

        } else if (login.equalsIgnoreCase("user@helpdesk.com")
                && senha.equalsIgnoreCase("12345")) {

            Intent intent = new Intent(context, HomeActivity.class);
            startActivity(intent);

        } else {

            Singleton.getInstance().alert(context, getString(R.string.activity_login_str_msg_login_invalido));

        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.activity_login_btn_esqueci_senha:

                Intent intent = new Intent(context, ResetarSenhaActivity.class);
                startActivity(intent);

                break;

            case R.id.activity_login_btn_entrar:

                //metodo valida no click os campos login e senha
                verifyCompontents();

                break;
        }

    }
}
