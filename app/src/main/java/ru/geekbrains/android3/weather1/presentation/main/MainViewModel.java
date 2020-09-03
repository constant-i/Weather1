package ru.geekbrains.android3.weather1.presentation.main;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import io.reactivex.disposables.CompositeDisposable;
import ru.geekbrains.android3.weather1.data.geo.Geolocation;
import ru.geekbrains.android3.weather1.presentation.details.WeatherDetailsActivity;

public class MainViewModel extends ViewModel {

    public static final String INTENT_CITY_NAME = "CityName";
    public static final String INTENT_LATITUDE = "Latitude";
    public static final String INTENT_LONGITUDE = "Longitude";
//    public static final String LAT_MAMONTOVKA = "56.0"
//    public static final String LON_MAMONTOVKA = "37.82"

    private Geolocation geolocation;

    MutableLiveData<String> liveDataLatitude = new MutableLiveData<>();
    MutableLiveData<String> liveDataLongitude = new MutableLiveData<>();
    private String latitude;
    private String longitude;

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    MainViewModel(Geolocation geolocation) {
        this.geolocation = geolocation;
    }

    void showWeatherForCity(Context context, String cityName) {
        Intent intent = new Intent(context, WeatherDetailsActivity.class);
        intent.putExtra(INTENT_CITY_NAME, cityName);
        context.startActivity(intent);
    }

    void showWeatherByCoord(Context context) {
        Intent intent = new Intent(context, WeatherDetailsActivity.class);
        intent.putExtra(INTENT_LATITUDE, latitude);
        intent.putExtra(INTENT_LONGITUDE, longitude);
        context.startActivity(intent);
    }

    void initCoordinate() {
        geolocation.initCoordinate();
        compositeDisposable.add(
                geolocation.getGeoSubject()
                        .subscribe(location -> {
                                    latitude = Double.toString(location.getLatitude());
                                    longitude = Double.toString(location.getLongitude());
                                    liveDataLatitude.postValue("Lat: " + latitude.substring(0, 10));
                                    liveDataLongitude.postValue("Lon: " + longitude.substring(0, 10));
                                },
                                throwable -> System.out.println(throwable))
        );
    }

    void stopCoordinate() {
        geolocation.stopCoordinate();
    }

    void disposeGeoSubject() {
        compositeDisposable.clear();
    }

    void requestLocation() {
        geolocation.requestLocation();
    }

}
