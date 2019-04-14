package com.example.drgame;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class registerRequest extends StringRequest {
    private static final String ruta = "https://alamanu.com/juegodr/registerDR.php";
    private Map<String, String> parametros;
    public registerRequest(String nombre, String email, String pass, String sexo, String alcohol, String spinn, Response.Listener<String> listener){
        super(Request.Method.POST, ruta, listener, null);
        parametros = new HashMap<>();
        parametros.put("nombre",nombre+"");
        parametros.put("email",email+"");
        parametros.put("pass",pass+"");
        parametros.put("sexo",sexo+"");
        parametros.put("alcohol",alcohol+"");
        parametros.put("spinn",spinn+"");

    }

    @Override
    protected Map<String, String> getParams() {
        return parametros;
    }
}
