package com.tutorialspoint.iotservices.Utilities;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.widget.Toast;

public class GPSTracker extends Service implements LocationListener {
    public static Context context;
    public static Boolean isGPSEnabled = false;
    public static Location location;


    LocationManager locationManager = null;
    static final float minUpdateDistance = 0;
    static final long minUpdateTime = 0;

    public GPSTracker(Context context) {
        GPSTracker.context = context;
        locationManager = (LocationManager) context.getSystemService(LOCATION_SERVICE);
        updateLocation();
    }

    @SuppressLint("MissingPermission")
    public void updateLocation() {


        if (locationManager != null) {
            isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

            if (isGPSEnabled) {
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, minUpdateTime, minUpdateDistance, this);
                location=locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            }
            else
            {
                Toast.makeText(context, "GPS Disapled", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public static void showSettingsAlert() {
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onLocationChanged(Location location) {
        GPSTracker.location = location;
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}
