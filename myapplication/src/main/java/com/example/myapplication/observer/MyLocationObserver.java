package com.example.myapplication.observer;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

import androidx.core.app.ActivityCompat;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

/**
 * @Author zhang tao
 * @Date 9/21/21 10:36 PM
 * @Desc
 */
public class MyLocationObserver implements LifecycleObserver {
    private final Context context;
    private LocationManager locationManager;
    private static final String TAG = "MyLocationObserver";
    private MyLocationListener myLocationListener;

    public MyLocationObserver(Context context) {
        this.context = context;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void startGetLocation() {
        Log.e(TAG, "startGetLocation: ");
        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        myLocationListener = new MyLocationListener();
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1, 1, myLocationListener);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void stopGetLocation() {
        Log.e(TAG, "stopGetLocation: ");
        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        locationManager.removeUpdates(myLocationListener);
    }

    static class MyLocationListener implements LocationListener {
        @Override
        public void onLocationChanged(Location location) {
            Log.e(TAG, "onLocationChanged: ");
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    }

}
