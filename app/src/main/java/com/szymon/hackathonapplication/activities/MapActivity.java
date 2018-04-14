package com.szymon.hackathonapplication.activities;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
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

        final LatLng gdanskLatLng = new LatLng(54.35, 18.6);
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(gdanskLatLng)
                .zoom(16)
                .build();
        mMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        mMap.getUiSettings().setRotateGesturesEnabled(false);
        mMap.getUiSettings().setCompassEnabled(false);
        mMap.getUiSettings().setMapToolbarEnabled(false);
        presenter.loadFruits();
    }
}
