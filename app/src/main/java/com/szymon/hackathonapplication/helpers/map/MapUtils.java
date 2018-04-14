package com.szymon.hackathonapplication.helpers.map;


import android.location.Location;
import android.location.LocationManager;

import com.google.android.gms.maps.model.LatLng;

public class MapUtils {

    public static float distanceBetween(final LatLng latLng1, final LatLng latLng2) {
        final Location loc1 = new Location(LocationManager.GPS_PROVIDER);
        final Location loc2 = new Location(LocationManager.GPS_PROVIDER);

        loc1.setLatitude(latLng1.latitude);
        loc1.setLongitude(latLng1.longitude);

        loc2.setLatitude(latLng2.latitude);
        loc2.setLongitude(latLng2.longitude);

        return loc1.distanceTo(loc2);
    }
}
