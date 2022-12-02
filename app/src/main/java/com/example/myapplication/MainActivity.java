package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        else
            initMap();
    }

    @Override
    public void onRequestPermissionsResult(
            int requestCode,
            @NonNull String[] permission,
            @NonNull int[] grantResults
    ) {
        super.onRequestPermissionsResult(requestCode, permission, grantResults);
        if (requestCode == 1) {
            for (int result : grantResults) {
                if (result != PackageManager.PERMISSION_GRANTED)
                    finish();
                else
                    initMap();
            }
        }
    }

    private void initMap() {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(GoogleMap map){
        if (ActivityCompat.checkSelfPermission(MainActivity.this,
                android.Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(MainActivity.this,
                        android.Manifest.permission.ACCESS_COARSE_LOCATION) !=
                        PackageManager.PERMISSION_GRANTED)
            return;
        map.setMyLocationEnabled(true);
        MarkerOptions m1 = new MarkerOptions();
        m1.position(new LatLng(25.033611, 121.565000));
        m1.title("台北101");
        m1.draggable(true);
        map.addMarker(m1);

        MarkerOptions m2 = new MarkerOptions();
        m2.position(new LatLng(25.047924, 121.517081));
        m1.title("台北車站");
        m1.draggable(true);
        map.addMarker(m2);

        PolylineOptions polylineOpt = new PolylineOptions();
        polylineOpt.add(new LatLng(25.033611,121.565000));
        polylineOpt.add(new LatLng(25.032728,21.5651377));
        polylineOpt.add(new LatLng(25.047924,121.517081));
        polylineOpt.color(Color.BLUE);
        Polyline polyline = map.addPolyline(polylineOpt);
        polyline.setWidth(10);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(
                new LatLng(25.034,121.545),13));
    }
}