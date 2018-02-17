package com.example.taxitestapp.utils;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;

/**
 * Created by mihai on 2/17/2018.
 */

public class PermissionHelper {

    public static boolean isPermissionGranted(final Context context, final String... permissions) {
        if (permissions != null && context != null) {
            for (final String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean isLocationPermissionGranted(final Context context) {
        return isPermissionGranted(context, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION);
    }

}
