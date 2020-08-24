package ru.geekbrains.android3.weather1.data.geo;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.LocationManager;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.util.Log;

import androidx.core.app.ActivityCompat;

import io.reactivex.subjects.PublishSubject;

import static android.content.Context.LOCATION_SERVICE;

public class Geolocation {

    private LocationManager locationManager;
    private String provider;

    PublishSubject<Location> geoSubject;

    private static final int PERMISSION_REQUEST_CODE = 10;

    Context context;

    public Geolocation(Context cont) {
        context = cont;
        geoSubject = PublishSubject.create();
    }

    public PublishSubject<Location> getGeoSubject() {
        return geoSubject;
    }

    public void initCoordinate() {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            requestLocation();              // запросим координаты
//            Log.d("MyTAG", "requestLocation()");
        } else {
            requestLocationPermissions();   // пермиссии нет, будем запрашивать у пользователя
        }
    }

    public void requestLocation() {
        locationManager = (LocationManager) context.getSystemService(LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_COARSE);
        provider = locationManager.getBestProvider(criteria, true);
        if (provider != null) {
            locationManager.requestLocationUpdates(provider, 10000, 10, listener);//получать геоположение через каждые 10 секунд или каждые 10 метров
            Log.d("MyTAG", "inside requestLocation()");
        }
    }


    private LocationListener listener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            Log.d("MyTAG", "inside onLocationChanged");
            geoSubject.onNext(location);
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
    };

    // Запрос пермиссии для геолокации
    private void requestLocationPermissions() {
        if (!ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, Manifest.permission.CALL_PHONE)) {
            ActivityCompat.requestPermissions((Activity) context,
                    new String[]{
                            Manifest.permission.ACCESS_COARSE_LOCATION,
                            Manifest.permission.ACCESS_FINE_LOCATION
                    },
                    PERMISSION_REQUEST_CODE);
        }
    }

    public void stopCoordinate() {
        locationManager.removeUpdates(listener);
        Log.d("MyTAG", "stopCoordinate()");
    }
}
