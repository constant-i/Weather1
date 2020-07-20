package ru.geekbrains.android3.weather1.presentation.main;

import android.content.Context;
import android.content.Intent;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import io.reactivex.disposables.CompositeDisposable;
import ru.geekbrains.android3.weather1.data.geo.Geolocation;
import ru.geekbrains.android3.weather1.presentation.details.WeatherDetailsActivity;

public class GeoViewModel extends ViewModel {

    public static final String INTENT_CITY_NAME = "CityName";
    public static final String INTENT_LATITUDE = "Latitude";
    public static final String INTENT_LONGITUDE = "Longitude";

    private Context context;
    private Geolocation geolocation;

    MutableLiveData<String> latitude = new MutableLiveData<>();
    MutableLiveData<String> longitude = new MutableLiveData<>();

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public GeoViewModel(Context cont, Geolocation geo) {
        context = cont;
        geolocation = geo;
    }

    public void showWeatherForCity(String cityName) {
        Intent intent = new Intent(context, WeatherDetailsActivity.class);
        intent.putExtra(INTENT_CITY_NAME, cityName);
        context.startActivity(intent);
    }

    public void initCoordinate() {
        geolocation.initCoordinate();

        compositeDisposable.add(
                geolocation.getGeoSubject()
//                        .doOnNext(location -> mainView.makeGeoBtnVisible())
                        .subscribe(location -> {
                                    latitude.setValue(Double.toString(location.getLatitude()));
                                    longitude.setValue(Double.toString(location.getLongitude()));
                                },
                                throweble -> System.out.println(throweble))
        );
    }

    public void stopCoordinate() {
        geolocation.stopCoordinate();
        compositeDisposable.dispose();
    }

    public void requestLocation() {
        geolocation.requestLocation();
    }

}
