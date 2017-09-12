package com.shihab.service;

import android.app.IntentService;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

/**
 * Created by Shihab on 9/12/2017.
 */

public class MyTestService extends IntentService {

    MyTestService myTestService = this;
    private FusedLocationProviderClient mFusedLocationClient;
    private static final String ACTION_PROCESS_UPDATES =
            "com.shihab.service.action" +
                    ".PROCESS_UPDATES";
    private static final String TAG = MyTestService.class.getSimpleName();

    public MyTestService() {
        super("MyTestService");
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Log.e("MyTestService", " onCreate called:::");


    }

    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        Log.e("MyTestService", " onStartCommand called:::");

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(getApplicationContext());
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            Log.e("asdsdasd", "");
            mFusedLocationClient.getLastLocation()
                    .addOnSuccessListener(new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            // Got last known location. In some rare situations this can be null.
                            if (location != null) {

                                Log.e("onSuccess", "Lat" + location.getLatitude() + "Long: " + location.getLongitude());
                            } else {

                                Log.e("onSuccess", "Null");

                            }
                        }
                    });
            //return;
        } else {



        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        // Do the task here
        Log.e("MyTestService", "onHandleIntent called");
        Log.e("MyTestService", "Service running");

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("MyTestService", "Service running");
    }
}