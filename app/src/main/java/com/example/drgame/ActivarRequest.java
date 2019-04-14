package com.example.drgame;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class ActivarRequest extends StringRequest {

    private static final String ruta = "https://alamanu.com/juegodr/activarDR.php";
    private Map<String, String> parametros;
    public ActivarRequest(String codigo, Response.Listener<String> listener){
        super(Request.Method.POST, ruta, listener, null);
        parametros = new HashMap<>();
        parametros.put("codigo",codigo+"");


    }

    @Override
    protected Map<String, String> getParams() {
        return parametros;
    }
}
