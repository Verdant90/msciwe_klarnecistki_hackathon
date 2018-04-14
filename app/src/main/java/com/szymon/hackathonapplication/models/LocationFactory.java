package com.szymon.hackathonapplication.models;

import com.google.android.gms.maps.model.LatLng;

import java.util.Random;

public class LocationFactory {

    public LatLng getRandomLocation(final LatLng northWest, final LatLng southEast) {
        Random rand = new Random();
        final double minLat = northWest.latitude;
        final double minLng = northWest.longitude;
        final double latRange = southEast.latitude - minLat;
        final double lngRange = southEast.longitude - minLng;

        return new LatLng(minLat + (rand.nextDouble() * latRange),
                minLng + (rand.nextDouble() * lngRange));
    }
}
