package com.szymon.hackathonapplication.activities;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.szymon.hackathonapplication.R;
import com.szymon.hackathonapplication.interfaces.MapMVP;
import com.szymon.hackathonapplication.models.fruits.Fruit;
import com.szymon.hackathonapplication.presenters.MapActivityPresenter;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.szymon.hackathonapplication.helpers.SystemServiceManager.requestFineLocationPermission;

public class MapActivity extends FragmentActivity implements LocationListener, OnMapReadyCallback, MapMVP.View {

    private GoogleMap mMap;
    private MapMVP.Presenter presenter;

    @OnClick(R.id.btn_challenges)
    public void goToChallengeActivity() {
        Intent intent = new Intent(MapActivity.this, ChallengeActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        ButterKnife.bind(this);
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

        createLocationUpdates();
    }

    private void createLocationUpdates() {
        final LocationManager locationManager = getLocationManagerService();
        if (locationManager == null) return;

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestFineLocationPermission(this);
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 5, this);
    }

    private LocationManager getLocationManagerService() {
        return (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
    }

    @Override
    public void showGeneratedFruits(final List<Fruit> fruits) {
        Marker fruitMarker;
        for (Fruit fruit : fruits) {
            addFruitToMap(fruit);
            fruitMarker = mMap.addMarker(new MarkerOptions()
                    .position(fruit.location));

            fruitMarker.setIcon(fruit.getFruitIcon());
        }
    }

    @OnClick(R.id.button_shop)
    public void goToShopButton() {
        startActivity(new Intent(MapActivity.this, ShopActivity.class));
    }


    private void addFruitToMap(final Fruit fruit) {
        final Marker fruitMarker = mMap.addMarker(new MarkerOptions()
                .position(fruit.location));

        fruitMarker.setIcon(fruit.getFruitIcon());
    }

    @Override
    public void onLocationChanged(final Location location) {
    }

    @Override
    public void onStatusChanged(final String s, final int i, final Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(final String s) {

    }

    @Override
    public void onProviderDisabled(final String s) {

    }
}
