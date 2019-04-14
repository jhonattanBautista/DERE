package com.example.drgame.Utils;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.MenuItem;

import android.support.design.widget.BottomNavigationView;

import com.example.drgame.CreateActivity;
import com.example.drgame.DataActivity;
import com.example.drgame.MapsActivity;
import com.example.drgame.PerfilActivity;
import com.example.drgame.R;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

public class BottomNavigationViewHelper {

    private static final String TAG = "BottomNavigationViewHel";

    public static void setUpButtomNavigationViewHelper(BottomNavigationViewEx bottomNavigationViewEx){
        Log.d(TAG, "setUpButtomNavigationViewHelper: ");


        bottomNavigationViewEx.enableAnimation(false);
        bottomNavigationViewEx.enableItemShiftingMode(false);
        bottomNavigationViewEx.enableShiftingMode(false);
        bottomNavigationViewEx.setTextVisibility(false);

    }

    public static void enableNavigation(final Context context, BottomNavigationViewEx viewEx){

        viewEx.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()){

                    case R.id.groupItem:
                       Intent intent = new Intent(context, CreateActivity.class);
                       context.startActivity(intent);
                     break;

                    case R.id.gameItem:
                        Intent intent2 = new Intent(context, DataActivity.class);
                        context.startActivity(intent2);
                        break;

                    case R.id.mapItem:
                        Intent intent3 = new Intent(context, MapsActivity.class);
                        context.startActivity(intent3);
                        break;

                    case R.id.lectorItem:
                        Intent intent4 = new Intent(context, LectorActivity.class);
                        context.startActivity(intent4);
                        break;

                    case R.id.perfilItem:
                        Intent intent5 = new Intent(context, PerfilActivity.class);
                        context.startActivity(intent5);
                        break;

                }

                return false;
            }
        });

    }

}
