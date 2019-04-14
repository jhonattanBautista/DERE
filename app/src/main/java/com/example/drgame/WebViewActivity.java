package com.example.drgame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import java.util.Random;

public class WebViewActivity extends AppCompatActivity {

    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        Random aleatorio = new Random(System.currentTimeMillis());

        Bundle parametros = this.getIntent().getExtras();
        String UrlResult = parametros.getString("resultado");

        webView = (WebView) findViewById(R.id.webview);

        int intAletorio = aleatorio.nextInt(10000000)+1000000;

        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setDisplayZoomControls(false);
        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setDefaultZoom(WebSettings.ZoomDensity.FAR);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.setInitialScale(1);

        webView.loadUrl(UrlResult+"?"+intAletorio);
    }
}
