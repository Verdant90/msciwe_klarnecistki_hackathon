package com.szymon.hackathonapplication.activities;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.szymon.hackathonapplication.R;
import com.szymon.hackathonapplication.interfaces.MapMVP;
import com.szymon.hackathonapplication.presenters.MapActivityPresenter;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback, MapMVP.View {

    private GoogleMap mMap;
    private MapMVP.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        presenter = new MapActivityPresenter(this);
    }


    @Override
    public void onMapReady(final GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        // TODO TCI should be replace by dedicated button on map layout
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                startActivity(new Intent(MapActivity.this, ShopActivity.class));
            }
        });
    }
}
