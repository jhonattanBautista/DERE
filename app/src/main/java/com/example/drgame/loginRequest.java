package com.example.drgame;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class loginRequest extends StringRequest {

    private static final String ruta = "https://alamanu.com/juegodr/loginDR.php";
    private Map<String, String> parametros;
    public loginRequest(String email, String pass, Response.Listener<String> listener){
        super(Request.Method.POST, ruta, listener, null);
        parametros = new HashMap<>();
        parametros.put("email",email+"");
        parametros.put("pass",pass+"");

    }

    @Override
    protected Map<String, String> getParams() {
        return parametros;
    }
}
