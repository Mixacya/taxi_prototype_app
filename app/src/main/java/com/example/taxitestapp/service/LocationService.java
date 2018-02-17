package com.example.taxitestapp.service;

import android.Manifest;
import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;

import com.example.taxitestapp.network.GPSManager;
import com.example.taxitestapp.utils.PermissionHelper;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.tasks.OnSuccessListener;

/**
 * Created by mihai on 2/17/2018.
 */

public class LocationService extends Service {

    private static final String TAG = "LocationService";

    private FusedLocationProviderClient mFusedLocationClient;
    private LocationRequest mLocationRequest;

    @Override
    public void onCreate() {
        super.onCreate();
        if (mFusedLocationClient == null && mLocationRequest == null) {
            mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
            getLastKnownLocation();
            createLocationRequest();
            startRegisterUpdates();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopLocationUpdates();
    }

    @Nullable
    @Override
    public IBinder onBind(final Intent intent) {
        return null;
    }

    protected LocationSettingsRequest createLocationRequest() {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(3000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(mLocationRequest);
        return builder.build();
    }

    private void getLastKnownLocation() {
        if (PermissionHelper.isLocationPermissionGranted(this)) {
            mFusedLocationClient.getLastLocation()
                    .addOnSuccessListener(new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(final Location location) {
                            if (location != null) {
                                GPSManager.getInstance().setLocation(location);
                            }
                        }
                    });
        }
    }

    private void startRegisterUpdates() {
        if (PermissionHelper.isLocationPermissionGranted(this)) {
            mFusedLocationClient.requestLocationUpdates(mLocationRequest, mLocationCallback, null);
        }
    }

    private void stopLocationUpdates() {
        if (mFusedLocationClient != null) {
            mFusedLocationClient.removeLocationUpdates(mLocationCallback);
        }
    }

    private final LocationCallback mLocationCallback = new LocationCallback() {
        @Override
        public void onLocationResult(final LocationResult locationResult) {
            if (locationResult != null && locationResult.getLocations().size() > 0) {
                GPSManager.getInstance().setLocation(locationResult.getLocations().get(0));
            }
        }
    };

}
