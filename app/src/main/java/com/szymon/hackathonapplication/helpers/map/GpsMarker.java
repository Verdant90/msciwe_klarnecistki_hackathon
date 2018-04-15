package com.szymon.hackathonapplication.helpers.map;


import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.szymon.hackathonapplication.R;
import com.szymon.hackathonapplication.helpers.AppPreferences;
import com.szymon.hackathonapplication.helpers.AppResources;

public class GpsMarker {

    private static final int GPS_FILL_COLOR_ID = R.color.gpsFillColor;
    private static final int GPS_ICON_ID = R.drawable.ic_gps_location;
    private static final float CIRCLE_STROKE_WIDTH = 5;
    private static final int GPS_STROKE_COLOR_ID = R.color.gpsStrokeColor;

    private Marker marker;
    private Circle circle;
    private LatLng latLng;

    private final GoogleMap googleMap;
    // TODO: check capabilities of mapRipple effect

    public GpsMarker(final GoogleMap googleMap, final LatLng latLng) {
        this.googleMap = googleMap;
        MarkerOptions markerOptions = new MarkerOptions()
                .icon(AppResources.getBitmapDescriptor(GPS_ICON_ID))
                .position(latLng)
                .anchor(0.5f, 0.5f);
        this.marker = googleMap.addMarker(markerOptions);

        CircleOptions circleOptions = new CircleOptions()
                .center(latLng)
                .radius(AppPreferences.getExplorationRange())
                .zIndex(Float.MAX_VALUE)
                .fillColor(AppResources.getColor(GPS_FILL_COLOR_ID))
                .strokeColor(AppResources.getColor(GPS_STROKE_COLOR_ID))
                .strokeWidth(CIRCLE_STROKE_WIDTH);

        circle = googleMap.addCircle(circleOptions);
    }

    public void changePosition(final LatLng latLng) {
        this.latLng = latLng;
        marker.setPosition(latLng);
        circle.setCenter(latLng);
    }

    public LatLng getPosition() {
        return this.latLng;
    }

    public void hideRange(){
        circle.setVisible(false);
    }
    public void showRange(){
        circle.setVisible(true);
    }
}
