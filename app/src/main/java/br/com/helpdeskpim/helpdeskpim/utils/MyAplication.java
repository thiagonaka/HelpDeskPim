package br.com.helpdeskpim.helpdeskpim.utils;

import android.content.Context;
import android.support.multidex.MultiDexApplication;

public class MyAplication extends MultiDexApplication {

    private static Context context;
    public void onCreate() {
        super.onCreate();
        MyAplication.context = getApplicationContext();
    }

    public static Context getAppContext() {
        return MyAplication.context;
    }

}
