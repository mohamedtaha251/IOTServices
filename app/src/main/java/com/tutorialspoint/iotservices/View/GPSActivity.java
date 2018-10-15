package com.tutorialspoint.iotservices.View;


import android.Manifest;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.tutorialspoint.iotservices.Utilities.GPSTracker;
import com.tutorialspoint.iotservices.R;

public class GPSActivity extends AppCompatActivity {
    Button btnStartUpdate;
    Button btnGetLocation;
    TextView tvLat;
    TextView tvLng;
    TextView tvSats;
    GPSTracker gpsTracker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gps_layout);

        btnStartUpdate = (Button) findViewById(R.id.btn_start_update);
        btnGetLocation = (Button) findViewById(R.id.btn_get_location);
        tvLat = (TextView) findViewById(R.id.tv_lat);
        tvLng = (TextView) findViewById(R.id.tv_lng);
        tvSats = (TextView) findViewById(R.id.tv_sats);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION};
            requestPermissions(permissions, 1);
        }

        gpsTracker = new GPSTracker(GPSActivity.this);

        btnStartUpdate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                if (GPSTracker.isGPSEnabled == false) {
                    GPSTracker.showSettingsAlert();
                }
            }
        });

        btnGetLocation.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {


                gpsTracker.updateLocation();

                if (GPSTracker.location != null) {
                    tvLng.setText(GPSTracker.location.getLongitude() + "");
                    tvLat.setText(GPSTracker.location.getLatitude() + "");
                    tvSats.setText(GPSTracker.location.getExtras().getInt("satellites")+"");
                }
            }
        });
    }

}