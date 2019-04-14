package com.example.drgame.Utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.drgame.MainActivity;

public class Preferences {

    public static final String STRING_PREFERENCES = "DERE.USERS.PREFERENCES";
    public static final String PREFERENCE_ESTADO_BUTTON_SESION = "estado.boton.sesion";
    public static final String PREFERENCE_USUARIO_LOGIN = "usuario.login";
    public static final String PREFERENCE_ID_LOGIN = "id.login";
    public static final String PREFERENCE_NAME_LOGIN = "name.login";
    public static final String PREFERENCE_SEX_LOGIN = "sex.login";
    public static final String PREFERENCE_ALCO_LOGIN = "alco.login";
    public static final String PREFERENCE_FACU_LOGIN = "facu.login";


    public static void savePreferenceBoolean(Context context, boolean bool, String key){
        SharedPreferences preferences = context.getSharedPreferences(STRING_PREFERENCES, context.MODE_PRIVATE);
        preferences.edit().putBoolean(key, bool).apply();

    }

    public static void savePreferenceString(Context context, String str, String key){
        SharedPreferences preferences = context.getSharedPreferences(STRING_PREFERENCES, context.MODE_PRIVATE);
        preferences.edit().putString(key, str).apply();

    }



    public static boolean obtenerPreferenceBoolean(Context context, String key){
        SharedPreferences preferences = context.getSharedPreferences(STRING_PREFERENCES, context.MODE_PRIVATE);
        return preferences.getBoolean(key, false); //si es que nunca se ha guardado nada en esta key retornara false
    }

    public static String obtenerPreferenceString(Context context, String key){
        SharedPreferences preferences = context.getSharedPreferences(STRING_PREFERENCES, context.MODE_PRIVATE);
        return preferences.getString(key, ""); //si es que nunca se ha guardado nada en esta key retornara una cadena vacía
    }
}
