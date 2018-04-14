package com.szymon.hackathonapplication.helpers;

import android.Manifest;
import android.app.Activity;
import android.support.v4.app.ActivityCompat;

public class SystemServiceManager {

    private static final int LOCATION_PERMISSIONS_REQUEST_CODE = 1;

    public static void requestFineLocationPermission(final Activity activity) {
        ActivityCompat.requestPermissions(activity,
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                LOCATION_PERMISSIONS_REQUEST_CODE);
    }
}
