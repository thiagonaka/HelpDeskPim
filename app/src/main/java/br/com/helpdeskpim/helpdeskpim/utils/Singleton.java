package br.com.helpdeskpim.helpdeskpim.utils;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

public class Singleton {

    private static Singleton instance;
    private Activity activity;

    public static Singleton getInstance( ) {
        if ( instance == null ) {
            instance = new Singleton( );
        }
        return instance;
    }


    public void alert(Context context, String s) {
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
    }

}
