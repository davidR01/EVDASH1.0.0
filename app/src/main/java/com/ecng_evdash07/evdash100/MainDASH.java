package com.ecng_evdash07.evdash100;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.BatteryManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.List;


public class MainDASH extends AppCompatActivity implements OnMapReadyCallback {

    ImageView imageView_Battery;////////////////////////////////////////
    TextView textView_Battery;/////////////////////////////////////////

    Handler handler;///////////////////////////////////////////
    Runnable runnable;///////////////////////////////////////


    GoogleMap dashGoogleMap;
    private final static int MY_PERMISSION_FINE_LOCATION = 101;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_dash);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        if (googleServicesAvaliable()) {
            Toast.makeText(this, "Checking for play_services", Toast.LENGTH_SHORT).show();
        }
        initMap();


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        imageView_Battery = (ImageView) findViewById(R.id.imageView_Battery);///////////////////
        textView_Battery = (TextView) findViewById(R.id.textView_Battery);///////////////////////

        runnable = new Runnable() {
            @Override
            public void run() {

                int level = (int) batterylevel();

                textView_Battery.setText("BATTERY: " + level + "%");

                if (level > 75) {
                    imageView_Battery.setImageResource(R.drawable.battery_full);
                }

                if (level > 50 && level <= 75) {
                    imageView_Battery.setImageResource(R.drawable.battery_eighty);
                }

                if (level > 25 && level <= 50) {
                    imageView_Battery.setImageResource(R.drawable.battery_fifty);
                }

                if (level <= 25) {
                    imageView_Battery.setImageResource(R.drawable.battery_twentyfive);
                }

                handler.postDelayed(runnable, 5000);
            }
        };

        handler = new Handler();
        handler.postDelayed(runnable, 0);
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    }

    private void initMap() {
        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.mapFragment);
        mapFragment.getMapAsync(this);

    }


    public void onButtonClick(View v) {
        if (v.getId() == R.id.DiagnosticButton) ;

        Intent i = new Intent(MainDASH.this, loginTSO.class);
        startActivity(i);
    }


    public boolean googleServicesAvaliable() {
        GoogleApiAvailability api = GoogleApiAvailability.getInstance();
        int isAvaliable = api.isGooglePlayServicesAvailable(this);
        if (isAvaliable == ConnectionResult.SUCCESS) {
            return true;
        } else if (api.isUserResolvableError(isAvaliable)) {
            Dialog dialog = api.getErrorDialog(this, isAvaliable, 0);
            dialog.show();
        } else {
            Toast.makeText(this, "Cannot connect to play services", Toast.LENGTH_LONG).show();
        }
        return false;

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        dashGoogleMap = googleMap;    ///////////////////////////////////////////
        goToLocationZoom(10.6918, -61.2225, 9);  ////////////////////////////////////
        dashGoogleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            dashGoogleMap.setMyLocationEnabled(true);
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSION_FINE_LOCATION);
            }

        }


      /*  if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            //to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            dashGoogleMap.setMyLocationEnabled(true);
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSION_FINE_LOCATION);
            }
        }*/
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case MY_PERMISSION_FINE_LOCATION:

                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

                        dashGoogleMap.setMyLocationEnabled(true);
                    }

                } else {
                    Toast.makeText(getApplicationContext(), "The MAP requires Location Permissions to be granted", Toast.LENGTH_LONG).show();
                }

                break;
        }
    }


    private void goToLocation(double latitude, double longitude) {
        LatLng ll = new LatLng(latitude, longitude);
        CameraUpdate update = CameraUpdateFactory.newLatLng(ll);
        dashGoogleMap.moveCamera(update);
    }


    private void goToLocationZoom(double latitude, double longitude, float zoom)//zooms in to the set  coordinates in map view
    {
        LatLng ll = new LatLng(latitude, longitude);
        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(ll, zoom);
        dashGoogleMap.moveCamera(update);
    }

    public void geoLocate(View view) throws IOException {
        EditText et = (EditText) findViewById(R.id.txtSearchLocation);
        String location = et.getText().toString();  /// what user enters as string

        Geocoder geocode = new Geocoder(this); //takes any string and converts into latitude and long
        List<Address> list = geocode.getFromLocationName(location, 1);

        Address address = list.get(0);

        String locality = address.getLocality();
        Toast.makeText(this, locality, Toast.LENGTH_LONG).show();

        double latitude = address.getLatitude();
        double longitude = address.getLongitude();

        goToLocationZoom(latitude, longitude, 13);

    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public float batterylevel() {
        Intent batteryIntent = registerReceiver(null, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        int level = batteryIntent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
        int scale = batteryIntent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);

        if (level == -1 || scale == -1) {
            return 50;
        }

        return ((float) level / (float) scale) * 100;
    }
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    ////DECLARATION OF VARIABLES FOR RANDOM SPEED VALUE GENERATOR
    //int random;





    /////////////////////////////////////////////////////////////////////////////////////////////////////////

}