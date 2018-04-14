package com.szymon.hackathonapplication.models;

import com.google.android.gms.maps.model.LatLng;

public class LocationFactory {

    public LatLng getRandomLocation(final LatLng northWest, final LatLng southEast) {
        return new LatLng(54,18);
    }
}
