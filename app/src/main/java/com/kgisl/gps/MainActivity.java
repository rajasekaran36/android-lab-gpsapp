package com.kgisl.gps;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements LocationListener {

    LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,3000,10,this);
        Location loc = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        Log.d("gpsapp",loc.getLatitude()+"   "+loc.getLongitude());

    }

    @Override
    public void onLocationChanged(Location location) {
        Toast.makeText(getApplicationContext(),"LocationChanged",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        Toast.makeText(getApplicationContext(),"StatusChanged",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onProviderEnabled(String provider) {
        Toast.makeText(getApplicationContext(),"ProviderEnabled",Toast.LENGTH_LONG).show();
        Log.d("gpsapp","ProEnable");
    }

    @Override
    public void onProviderDisabled(String provider) {
        Toast.makeText(getApplicationContext(),"ProviderDisabled",Toast.LENGTH_LONG).show();
        Log.d("gpsapp","ProDisable");
    }
}
