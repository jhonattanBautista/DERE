package com.example.drgame.Utils;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.drgame.R;
import com.example.drgame.WebViewActivity;
import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class LectorActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {
  private ZXingScannerView escannerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lector);

        escannerView = new ZXingScannerView(this);
        setContentView(escannerView);
        escannerView.setResultHandler(this);
        escannerView.startCamera();
    }

    @Override
    public void handleResult(Result result) {
        Intent intent = new Intent(LectorActivity.this, WebViewActivity.class);
        intent.putExtra("resultado", result.getText()+"");
        startActivity(intent);
        finish();

        escannerView.resumeCameraPreview(LectorActivity.this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        escannerView.stopCamera();
    }

}
