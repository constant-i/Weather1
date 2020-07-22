package ru.geekbrains.android3.weather1.presentation.main;

import android.content.Context;
import android.content.Intent;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import io.reactivex.disposables.CompositeDisposable;
import ru.geekbrains.android3.weather1.data.geo.Geolocation;
import ru.geekbrains.android3.weather1.presentation.weather_details.WeatherDetailsActivity;

public class GeoViewModel extends ViewModel {

    public static final String INTENT_CITY_NAME = "CityName";
    public static final String INTENT_LATITUDE = "Latitude";
    public static final String INTENT_LONGITUDE = "Longitude";

    private Context context;
    private Geolocation geolocation;

    MutableLiveData<String> latitude = new MutableLiveData<>();
    MutableLiveData<String> longitude = new MutableLiveData<>();

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    GeoViewModel(Context cont, Geolocation geo) {
        context = cont;
        geolocation = geo;
    }

    void showWeatherForCity(String cityName) {
        Intent intent = new Intent(context, WeatherDetailsActivity.class);
        intent.putExtra(INTENT_CITY_NAME, cityName);
        context.startActivity(intent);
    }

    void showWeatherByCoordinates() {
        Intent intent = new Intent(context, WeatherDetailsActivity.class);
        intent.putExtra(INTENT_LATITUDE, latitude.getValue());
        intent.putExtra(INTENT_LONGITUDE, longitude.getValue());
        context.startActivity(intent);
    }

    void initCoordinate() {
        geolocation.initCoordinate();

        compositeDisposable.add(
                geolocation.getGeoSubject()
                        .subscribe(location -> {
                                    latitude.setValue(Double.toString(location.getLatitude()));
                                    longitude.setValue(Double.toString(location.getLongitude()));
                                },
                                throwable -> System.out.println(throwable))
        );
    }

    void stopCoordinate() {
        geolocation.stopCoordinate();
        compositeDisposable.dispose();
    }

    void requestLocation() {
        geolocation.requestLocation();
    }

}
