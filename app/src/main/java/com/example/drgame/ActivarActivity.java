package com.example.drgame;

import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class ActivarActivity extends AppCompatActivity {


    Button btnEnviarCod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activar);

        final EditText codigoT = (EditText)findViewById(R.id.editTextCod);
        btnEnviarCod = (Button)findViewById(R.id.buttonCod);

        btnEnviarCod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String codigo = codigoT.getText().toString().trim();

                Response.Listener<String> respuesta = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonRespuesta = new JSONObject(response);
                            boolean ok = jsonRespuesta.getBoolean("success");
                            if(ok == true){
                                Intent i = new Intent(ActivarActivity.this, MainActivity.class);
                                ActivarActivity.this.startActivity(i);
                                ActivarActivity.this.finish();
                            }else{
                                AlertDialog.Builder alerta = new AlertDialog.Builder(ActivarActivity.this);
                                alerta.setMessage("fallo en la activació")
                                        .setNegativeButton("Reintentar", null)
                                        .create()
                                        .show();
                            }
                        }catch (JSONException e){
                            e.getMessage();
                        }

                    }
                };

                ActivarRequest r = new ActivarRequest(codigo, respuesta);
                RequestQueue cola = Volley.newRequestQueue(ActivarActivity.this);
                cola.add(r);

            }
        });

    }
}
