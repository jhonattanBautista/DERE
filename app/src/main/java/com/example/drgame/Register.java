package com.example.drgame;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Register extends AppCompatActivity {



    private Spinner facultades;

    private static String selectedItemText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText nombreT = (EditText)findViewById(R.id.nombreRegistro);
        final EditText emailT = (EditText)findViewById(R.id.emailRegistro);
        final EditText passT = (EditText)findViewById(R.id.passRegistro);
        final EditText pass2T = (EditText)findViewById(R.id.passRegistro2);
        Button btnRegistroT = (Button)findViewById(R.id.btnRegistro);
        final RadioButton rBSexH = (RadioButton)findViewById(R.id.radioButtonSexH);
        final RadioButton rBSexM = (RadioButton)findViewById(R.id.radioButtonSexM);
        final RadioButton rBSexL = (RadioButton)findViewById(R.id.radioButtonSexL);
        final RadioButton rBAlcSi = (RadioButton)findViewById(R.id.radioButtonAlcSi);
        final RadioButton rBAlcNo = (RadioButton)findViewById(R.id.radioButtonAlcNo);

         facultades = (Spinner)findViewById(R.id.sP01);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(Register.this, R.array.facultades, android.R.layout.simple_spinner_item);
        facultades.setAdapter(adapter);






        btnRegistroT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String nombre   = nombreT.getText().toString().trim();
                String email    = emailT.getText().toString().trim();
                String pass     = passT.getText().toString().trim();
                String pass2     = pass2T.getText().toString().trim();
                String sexo = "";
                String alcohol = "";
                String spinn = facultades.getSelectedItem().toString();
                selectedItemText = String.valueOf(facultades.getSelectedItem());

         if(nombre.isEmpty() || email.isEmpty() || pass.isEmpty() || pass2.isEmpty()){

             Toast.makeText(Register.this, "Ningún campo puede estar vacío.", Toast.LENGTH_LONG).show();

         }else{

             if(!pass.equals(pass2)){

                 Toast.makeText(Register.this, "Las contraseñas no coinciden.", Toast.LENGTH_LONG).show();

             }else {

                 if (rBSexH.isChecked()) {
                     sexo = rBSexH.getText().toString();
                 } else if (rBSexM.isChecked()) {
                     sexo = rBSexM.getText().toString();
                 } else if (rBSexL.isChecked()) {
                     sexo = rBSexL.getText().toString();
                 }

                 if (rBAlcSi.isChecked()) {
                     alcohol = rBAlcSi.getText().toString();
                 } else if (rBAlcNo.isChecked()) {
                     alcohol = rBAlcNo.getText().toString();
                 }

                 Response.Listener<String> respuesta = new Response.Listener<String>() {
                     @Override
                     public void onResponse(String response) {

                         try {
                             JSONObject jsonRespuesta = new JSONObject(response);
                             boolean ok = jsonRespuesta.getBoolean("success");
                             if (ok == true) {
                                 Intent i = new Intent(Register.this, ActivarActivity.class);
                                 Register.this.startActivity(i);
                                 Register.this.finish();
                             } else {
                                 AlertDialog.Builder alerta = new AlertDialog.Builder(Register.this);
                                 alerta.setMessage("fallo en el registro")
                                         .setNegativeButton("Reintentar", null)
                                         .create()
                                         .show();
                             }
                         } catch (JSONException e) {
                             e.getMessage();
                         }

                     }
                 };

                 registerRequest r = new registerRequest(nombre, email, pass, sexo, alcohol, selectedItemText, respuesta);
                 RequestQueue cola = Volley.newRequestQueue(Register.this);
                 cola.add(r);

                 }

               }
            }
        });
    }



}
