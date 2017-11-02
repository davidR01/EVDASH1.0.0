package com.ecng_evdash07.evdash100;

import android.app.Dialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
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

    GoogleMap dashGoogleMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_dash);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        if(googleServicesAvaliable())
        {Toast.makeText(this, "Checking for play_services", Toast.LENGTH_SHORT).show();}
        initMap();
    }

    private void initMap()
    {
        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.mapFragment);
        mapFragment.getMapAsync(this);

    }


    public void onButtonClick(View v)
    {
        if(v.getId()==R.id.DiagnosticButton);

        Intent i = new Intent(MainDASH.this, loginTSO.class);
        startActivity(i);
    }


    public boolean googleServicesAvaliable()
    {
        GoogleApiAvailability api = GoogleApiAvailability.getInstance();
        int isAvaliable = api.isGooglePlayServicesAvailable(this);
        if (isAvaliable== ConnectionResult.SUCCESS)
        {
            return true;
        }
        else if (api.isUserResolvableError(isAvaliable))
        {
            Dialog dialog = api.getErrorDialog(this,isAvaliable,0);
            dialog.show();
        }
        else
        {
            Toast.makeText(this, "Cannot connect to play services", Toast.LENGTH_LONG).show();
        }
        return false;

    }


    @Override
    public void onMapReady(GoogleMap googleMap)
    {
            dashGoogleMap = googleMap;    ///////////////////////////////////////////
            goToLocationZoom(10.6918, -61.2225,10);  ////////////////////////////////////
    }

    private void goToLocation(double latitude, double longitude)
    {
        LatLng ll = new LatLng(latitude,longitude);
        CameraUpdate update = CameraUpdateFactory.newLatLng(ll);
        dashGoogleMap.moveCamera(update);

    }
    private void goToLocationZoom(double latitude, double longitude,float zoom)//zooms in to the set  coordinates in map view
    {
        LatLng ll = new LatLng(latitude,longitude);
        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(ll,zoom);
        dashGoogleMap.moveCamera(update);

    }

    public void geoLocate(View view) throws IOException {
        EditText et = (EditText) findViewById(R.id.txtSearchLocation);
        String location = et.getText().toString();  /// what user enters as string

        Geocoder geocode = new Geocoder(this); //takes any string and converts into latitude and long
        List<Address>list = geocode.getFromLocationName(location,1);

        Address address = list.get(0);

        String locality = address.getLocality();
        Toast.makeText(this,locality,Toast.LENGTH_LONG).show();

        double latitude  = address.getLatitude();
        double longitude = address.getLongitude();

        goToLocationZoom(latitude,longitude, 12);




    }
}
