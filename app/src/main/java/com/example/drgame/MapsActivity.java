package com.example.drgame;

import android.Manifest;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.drgame.Utils.BottomNavigationViewHelper;
import com.example.drgame.Utils.Preferences;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import javax.security.auth.login.LoginException;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {


    private String NOMBRE;

    private LocationListener locationListener;

    private LocationManager locationManager;

    private final long MIN_TIME = 1000; //1 segundo

    private final long MIN_DISTANCE = 5; //5 metros

    private LatLng latLng;

    private static final String TAG = "MapsActivity";
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        setUpBottoNavigationView();

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PackageManager.PERMISSION_GRANTED);
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, PackageManager.PERMISSION_GRANTED);
        NOMBRE = Preferences.obtenerPreferenceString(this, Preferences.PREFERENCE_NAME_LOGIN);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        try{

            boolean success = googleMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(this, R.raw.mapstyle));

            if(!success){
                Log.e("MapsActivity","Style parsing failed");
            }

        }catch (Resources.NotFoundException e){
            Log.e(TAG, "No se encontró el estilo. Error: ", e);
        }

        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                String imp = NOMBRE;
                try{
                    latLng = new LatLng(location.getLatitude(), location.getLongitude());
                    mMap.addMarker(new MarkerOptions().position(latLng).title(imp));
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                    mMap.moveCamera(CameraUpdateFactory.zoomTo(18));
                }catch (SecurityException e){
                    e.printStackTrace();
                }


            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }

        };

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        try{
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, MIN_TIME, MIN_DISTANCE, locationListener);
        }catch (SecurityException e){
            e.printStackTrace();
        }

    }

    private void setUpBottoNavigationView(){
        Log.d("CreateActivity", "SetUpBottomNavigationView");

        BottomNavigationViewEx bottomNavigationViewEx = (BottomNavigationViewEx)findViewById(R.id.bottomNavViewBar);
        BottomNavigationViewHelper.setUpButtomNavigationViewHelper(bottomNavigationViewEx);
        BottomNavigationViewHelper.enableNavigation(MapsActivity.this, bottomNavigationViewEx);
    }
}
