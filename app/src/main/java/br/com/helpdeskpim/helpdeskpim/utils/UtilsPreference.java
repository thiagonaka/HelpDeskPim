package br.com.helpdeskpim.helpdeskpim.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

public class UtilsPreference {

    public static final String PREFERENCES_NAME = "HelpDeskPim_PREFERENCES";
    public static final String CONTADOR_OS = "CONTADOR_OS";
    public static final String ORDEM_SERVICO_LIST = "ORDEM_SERVICO_LIST";
    public static final String EDITADO = "EDITADO";

    private static SharedPreferences getSharedPreference() {
        Context context = MyAplication.getAppContext();
        SharedPreferences prefs = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
        return prefs;
    }

    private static void saveSharedPreference(String key, String value) {
        SharedPreferences prefs = getSharedPreference();
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(key, value);
        editor.commit();
    }

    private static String getSharedPreference(String key) {
        SharedPreferences prefs = getSharedPreference();
        String pref = prefs.getString(key, null);
        return pref;
    }


    public static String getContadorOs(){
        String savedValue = getSharedPreference(CONTADOR_OS);
        return (TextUtils.isEmpty(savedValue))? "" : savedValue;
    }

    public static void setContadorPedido(String json){
        saveSharedPreference(CONTADOR_OS, json);
    }

    public static String getOrdemServicoList(){
        String savedValue = getSharedPreference(ORDEM_SERVICO_LIST);
        return (TextUtils.isEmpty(savedValue))? "" : savedValue;
    }

    public static void setOrdemServicoList(String json){
        saveSharedPreference(ORDEM_SERVICO_LIST, json);
    }

    public static void saveEdit(boolean edit) {
        saveSharedPreference(EDITADO, String.valueOf(edit));
    }

    public static boolean getEdit() {
        String savedValue = getSharedPreference(EDITADO);
        return (TextUtils.isEmpty(savedValue)) ? null : Boolean.valueOf(savedValue);
    }
}
