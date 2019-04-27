package com.example.nfc;

import android.Manifest.permission;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.widget.Toast;

public class GPSLocationManager {
    private LocationManager locationManager;
    private Context context;

    private static GPSLocationManager instance;

    public static GPSLocationManager instance(Context context) {
        if (instance == null) {
            instance = new GPSLocationManager(context);
        }

        return instance;
    }

    private GPSLocationManager(Context context) {
        this.context = context;
        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
    }

    public Location getLocation() {
        if (context.checkSelfPermission(permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED
                && context.checkSelfPermission(permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            // TODO: Consider calling
            //    Activity#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for Activity#requestPermissions for more details.
            Toast.makeText(context, "Please enable location permissions", Toast.LENGTH_LONG).show();
            return null;
        }

        Criteria locationCriteria = new Criteria();
        locationCriteria.setAccuracy(Criteria.ACCURACY_FINE);
        String bestLocationProvider = locationManager.getBestProvider(locationCriteria, true);
        return locationManager.getLastKnownLocation(bestLocationProvider);
    }
}
