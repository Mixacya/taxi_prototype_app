package com.example.taxitestapp.network;

import android.location.Location;

/**
 * Created by mihai on 2/17/2018.
 */

public class GPSManager {

    private static final GPSManager INSTANCE = new GPSManager();

    private Location mLocation;

    private GPSManager() { }

    public static GPSManager getInstance() {
        return INSTANCE;
    }

    public Location getLocation() {
        return mLocation;
    }

    public void setLocation(final Location location) {
        this.mLocation = location;
    }
}
