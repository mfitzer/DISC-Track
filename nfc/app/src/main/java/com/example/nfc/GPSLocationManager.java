package com.example.nfc;

import android.Manifest.permission;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class GPSLocationManager implements LocationListener {
    private LocationManager locationManager;
    private Context context;
    private Location lastKnownLocation;

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
        }
        else
        {
            //locationManager.requestLocationUpdates(getProvider(), 0, 0, this);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, this);
        }
    }

    public void onLocationChanged(Location location) {
        if (location != null) {
            //Toast.makeText(context, "Location changed: " + location, Toast.LENGTH_LONG).show();
            Toast.makeText(context, "Location updated.", Toast.LENGTH_LONG).show();
            lastKnownLocation = location;
            //Log.v("Location Changed", location.getLatitude() + " and " + location.getLongitude());
            //locationManager.removeUpdates(this);
        }
        else
        {
            Toast.makeText(context, "Location changed, but null", Toast.LENGTH_LONG).show();
        }
    }

    // Required functions
    public void onProviderDisabled(String arg0) {}
    public void onProviderEnabled(String arg0) {}
    public void onStatusChanged(String arg0, int arg1, Bundle arg2) {}

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

        /*Location gpsLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        Location networkLocation = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        if (gpsLocation != null)
        {
            return gpsLocation;
        }
        else if (networkLocation != null)
        {
            return networkLocation;
        }*/

        return lastKnownLocation;
    }

    private String getProvider()
    {
        String bestLocationProvider;

        Criteria providerCriteria = new Criteria();
        providerCriteria.setAccuracy(Criteria.ACCURACY_FINE);
        providerCriteria.setPowerRequirement(Criteria.POWER_LOW);
        providerCriteria.setAltitudeRequired(true);
        providerCriteria.setBearingRequired(false);
        providerCriteria.setSpeedRequired(false);
        providerCriteria.setCostAllowed(false);

        boolean enabledOnly = true;
        bestLocationProvider = locationManager.getBestProvider(providerCriteria, enabledOnly);

        if (bestLocationProvider.equals(null) || bestLocationProvider.equals(""))
        {
            providerCriteria.setAccuracy(Criteria.ACCURACY_HIGH);
            bestLocationProvider = locationManager.getBestProvider(providerCriteria, enabledOnly);
        }

        if (bestLocationProvider.equals(null) || bestLocationProvider.equals(""))
        {
            providerCriteria.setAccuracy(Criteria.ACCURACY_MEDIUM);
            bestLocationProvider = locationManager.getBestProvider(providerCriteria, enabledOnly);
        }

        if (bestLocationProvider.equals(null) || bestLocationProvider.equals(""))
        {
            providerCriteria.setAccuracy(Criteria.ACCURACY_LOW);
            bestLocationProvider = locationManager.getBestProvider(providerCriteria, enabledOnly);
        }

        if (bestLocationProvider.equals(null) || bestLocationProvider.equals(""))
        {
            providerCriteria.setAccuracy(Criteria.ACCURACY_COARSE);
            bestLocationProvider = locationManager.getBestProvider(providerCriteria, enabledOnly);
        }

        if (bestLocationProvider.equals(null) || bestLocationProvider.equals(""))
        {
            Toast.makeText(context, "Location provider could not be found", Toast.LENGTH_LONG).show();
            return null;
        }
        else
        {
            Toast.makeText(context, "Location provider found: " + bestLocationProvider, Toast.LENGTH_LONG).show();
        }

        return bestLocationProvider;
    }
}
