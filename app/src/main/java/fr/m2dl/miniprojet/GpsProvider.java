package fr.m2dl.miniprojet; /**
 * Created by mfaure on 21/01/15.
 */
import java.util.ArrayList;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

public class GpsProvider {

    private double longitude;
    private double latitude;
    protected  LocationManager lm;
    protected  LocationListener ll;

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }



    public GpsProvider(Context context) {

        lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        ll = new LocationListener() {

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {
                System.out.println("statut : " + status);

            }

            @Override
            public void onProviderEnabled(String provider) {
                System.out.println("Provider enabled : " + provider);
            }

            @Override
            public void onProviderDisabled(String provider) {
                System.out.println("Provider disabled : " + provider);

            }

            @Override
            public void onLocationChanged(Location location) {
                setLatitude(location.getLatitude());
                setLongitude(location.getLongitude());


            }
        };
        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, ll);
        lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 0, ll);
    }

    public void destroy(){
        lm.removeUpdates(ll);
        lm = null;
    }

    public boolean isEnabled(){
        return lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }

}
