package com.example.drgame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.drgame.Utils.BottomNavigationViewHelper;
import com.example.drgame.Utils.Preferences;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

public class PerfilActivity extends AppCompatActivity {

    private Button btnCerrarSesion;
    private TextView nombre;
    private TextView genero;
    private TextView usuario;
    private TextView sexo;
    private TextView facu;
    private TextView alcohol;
    private TextView nameTop;

    private String NOMBRE;
    private String ID;
    private String USUARIO;
    private String SEXO;
    private String FACULTAD;
    private String ALCOHOL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        btnCerrarSesion = (Button)findViewById(R.id.btnCerrarSesion);
        nombre = (TextView)findViewById(R.id.textViewNombre);
        facu = (TextView)findViewById(R.id.textViewFacul);
        genero = (TextView)findViewById(R.id.textViewGenero);
      //  sexo = (TextView)findViewById(R.id.tVsex);
      //  facultad = (TextView)findViewById(R.id.tVfacu);
        alcohol = (TextView)findViewById(R.id.textViewAlcohol);
        nameTop = (TextView)findViewById(R.id.tVNombreTopTab);

        NOMBRE = Preferences.obtenerPreferenceString(this, Preferences.PREFERENCE_NAME_LOGIN);
        ID = Preferences.obtenerPreferenceString(this, Preferences.PREFERENCE_ID_LOGIN);
        USUARIO = Preferences.obtenerPreferenceString(this, Preferences.PREFERENCE_USUARIO_LOGIN);
        SEXO = Preferences.obtenerPreferenceString(this, Preferences.PREFERENCE_SEX_LOGIN);
        FACULTAD = Preferences.obtenerPreferenceString(this, Preferences.PREFERENCE_FACU_LOGIN);
        ALCOHOL = Preferences.obtenerPreferenceString(this, Preferences.PREFERENCE_ALCO_LOGIN);

       // nombre.setText("Hola "+NOMBRE+" estos son su datos de usuario");
      //  id.setText("su id: "+ID);
      //  usuario.setText("su nickname: "+USUARIO);
     //   sexo.setText("Genero que lo identifica: "+SEXO);
     //   facultad.setText("Su facultad: "+FACULTAD);
     //   alcohol.setText("Tu "+ALCOHOL+" consumes alcohol");
        nameTop.setText(USUARIO);
        nombre.setText(NOMBRE);
        facu.setText("Facultad: "+FACULTAD);
        genero.setText("Genero: "+SEXO);
        alcohol.setText("Consumes bebidas alcoholicas: "+ALCOHOL);

        btnCerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Preferences.savePreferenceBoolean(PerfilActivity.this, false, Preferences.PREFERENCE_ESTADO_BUTTON_SESION);
                Intent intent = new Intent(PerfilActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        setUpBottoNavigationView();

    }

    private void setUpBottoNavigationView(){
        Log.d("CreateActivity", "SetUpBottomNavigationView");

        BottomNavigationViewEx bottomNavigationViewEx = (BottomNavigationViewEx)findViewById(R.id.bottomNavViewBar);
        BottomNavigationViewHelper.setUpButtomNavigationViewHelper(bottomNavigationViewEx);
        BottomNavigationViewHelper.enableNavigation(PerfilActivity.this, bottomNavigationViewEx);
    }
}
