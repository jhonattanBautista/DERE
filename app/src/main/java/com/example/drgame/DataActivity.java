package com.example.drgame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.drgame.Utils.BottomNavigationViewHelper;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

public class DataActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        setUpBottoNavigationView();
    }

    private void setUpBottoNavigationView(){
        Log.d("CreateActivity", "SetUpBottomNavigationView");

        BottomNavigationViewEx bottomNavigationViewEx = (BottomNavigationViewEx)findViewById(R.id.bottomNavViewBar);
        BottomNavigationViewHelper.setUpButtomNavigationViewHelper(bottomNavigationViewEx);
        BottomNavigationViewHelper.enableNavigation(DataActivity.this, bottomNavigationViewEx);
    }
}
