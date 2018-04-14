package com.szymon.hackathonapplication.helpers.map;


import android.content.Context;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.szymon.hackathonapplication.R;
import com.szymon.hackathonapplication.helpers.AppResources;

public class GpsMarker {
    private Marker marker;
    private Circle circle;
    private LatLng latLng;
    private static final int gpsIconId = R.drawable.ic_gps_location;
    // TODO: check capabilities of mapRipple effect

    public GpsMarker(final GoogleMap googleMap, final Context context, final LatLng latLng) {
        MarkerOptions markerOptions = new MarkerOptions()
                .icon(AppResources.getBitmapDescriptor(gpsIconId));
    }
}
