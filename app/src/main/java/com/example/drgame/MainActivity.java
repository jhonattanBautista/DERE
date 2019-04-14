package com.example.drgame;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.drgame.Utils.Preferences;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private Boolean isActivateRadioButton;

    private static String ID;
    private static String USER;
    private static String NAME;
    private static String SEX;
    private static String ALCO;
    private static String FACU;


    private static  RadioButton rBSesion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(Preferences.obtenerPreferenceBoolean(this, Preferences.PREFERENCE_ESTADO_BUTTON_SESION)){
            Intent bienvenido = new Intent(MainActivity.this, PerfilActivity.class);
            startActivity(bienvenido);
            finish();
        }

        TextView registro = (TextView)findViewById(R.id.textViewRegistrate);
        TextView validar = (TextView)findViewById(R.id.textViewValidar);
        Button btnLogin = (Button)findViewById(R.id.btnLogueo);
        final EditText emailT = (EditText)findViewById(R.id.emailLogueo);
        final EditText passT = (EditText)findViewById(R.id.passLogueo);
        rBSesion = (RadioButton)findViewById(R.id.rBSesion);

        registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent registro = new Intent(MainActivity.this, Register.class);
                MainActivity.this.startActivity(registro);
                finish();
            }
        });

        validar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent valida = new Intent(MainActivity.this, ActivarActivity.class);
                MainActivity.this.startActivity(valida);
                finish();
            }
        });

        isActivateRadioButton = rBSesion.isChecked();

        rBSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isActivateRadioButton){
                    rBSesion.setChecked(false);
                }

                isActivateRadioButton = rBSesion.isChecked();
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String email = emailT.getText().toString().trim();
                final String pass = passT.getText().toString().trim();

                if(email.isEmpty() || pass.isEmpty()){

                    Toast.makeText(MainActivity.this, "Debes rellenar todos los campos.", Toast.LENGTH_LONG).show();

                }else{

                Response.Listener<String> respuesta = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Preferences.savePreferenceBoolean(MainActivity.this, rBSesion.isChecked(), Preferences.PREFERENCE_ESTADO_BUTTON_SESION);

                        try {
                            JSONObject jsonRespuesta = new JSONObject(response);
                            boolean ok = jsonRespuesta.getBoolean("success");
                            if(ok == true){
                                String activo = jsonRespuesta.getString("activo");
                                ID = jsonRespuesta.getString("idUser");
                                USER = jsonRespuesta.getString("email");
                                NAME = jsonRespuesta.getString("nombre");
                                SEX = jsonRespuesta.getString("sexo");
                                ALCO = jsonRespuesta.getString("alcohol");
                                FACU = jsonRespuesta.getString("facultad");

                                Preferences.savePreferenceString(MainActivity.this, ID, Preferences.PREFERENCE_ID_LOGIN); //guarda en la variable global PREFERENCE_ID_LOGIN el id del usuario que se loguee, con esta variable se pide el id usuario.
                                Preferences.savePreferenceString(MainActivity.this, USER, Preferences.PREFERENCE_USUARIO_LOGIN);
                                Preferences.savePreferenceString(MainActivity.this, NAME, Preferences.PREFERENCE_NAME_LOGIN);
                                Preferences.savePreferenceString(MainActivity.this, SEX, Preferences.PREFERENCE_SEX_LOGIN);
                                Preferences.savePreferenceString(MainActivity.this, ALCO, Preferences.PREFERENCE_ALCO_LOGIN);
                                Preferences.savePreferenceString(MainActivity.this, FACU, Preferences.PREFERENCE_FACU_LOGIN);

                                if(activo.equals("1")){
                                    Intent bienvenido = new Intent(MainActivity.this, PerfilActivity.class);
                                    MainActivity.this.startActivity(bienvenido);
                                    MainActivity.this.finish();

                                }else{
                                    AlertDialog.Builder alerta = new AlertDialog.Builder(MainActivity.this);
                                    alerta.setMessage("Debes Activar tu cuenta con el codigo que te enviamos al correo")
                                            .setNegativeButton("Reintentar", null)
                                            .create()
                                            .show();
                                }

                            }else{
                                AlertDialog.Builder alerta = new AlertDialog.Builder(MainActivity.this);
                                alerta.setMessage("Usuario o contraseña incorrectos.")
                                        .setNegativeButton("Reintentar", null)
                                        .create()
                                        .show();
                            }
                        }catch (JSONException e){
                            e.getMessage();
                        }

                    }
                };
                loginRequest r = new loginRequest(email, pass, respuesta);
                RequestQueue cola = Volley.newRequestQueue(MainActivity.this);
                cola.add(r);

                }

            }

        });
    }

}
