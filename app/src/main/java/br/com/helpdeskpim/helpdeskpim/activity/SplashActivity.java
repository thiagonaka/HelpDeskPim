package br.com.helpdeskpim.helpdeskpim.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import br.com.helpdeskpim.helpdeskpim.R;

public class SplashActivity extends AppCompatActivity {

    private static int TEMPO_SPLASH = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        final SplashActivity activity = this;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent i = new Intent(activity, LoginActivity.class);
                startActivity(i);
                activity.finish();

            }
        }, TEMPO_SPLASH);
    }
}
