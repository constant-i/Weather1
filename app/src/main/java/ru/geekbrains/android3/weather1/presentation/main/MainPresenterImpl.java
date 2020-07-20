package ru.geekbrains.android3.weather1.presentation.main;

import android.content.Context;
import android.content.Intent;

import io.reactivex.disposables.CompositeDisposable;
import ru.geekbrains.android3.weather1.data.geo.Geolocation;
import ru.geekbrains.android3.weather1.presentation.details.WeatherDetailsActivity;

public class MainPresenterImpl implements MainPresenter {

    public static final String INTENT_CITY_NAME = "CityName";
    public static final String INTENT_LATITUDE = "Latitude";
    public static final String INTENT_LONGITUDE = "Longitude";

    private Context context;
    private Geolocation geolocation;

    private String latitude;
    private String longitude;

    private MainView mainView;


    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public MainPresenterImpl(Context context) {
        this.context = context;

        mainView = (MainView) context;

        geolocation = new Geolocation(context);
        compositeDisposable.add(
                geolocation.getGeoSubject()
                        .doOnNext(location -> mainView.makeGeoBtnVisible())
                        .subscribe(location -> {
                                    latitude = Double.toString(location.getLatitude());
                                    mainView.setLatitude(latitude);
                                    longitude = Double.toString(location.getLongitude());
                                    mainView.setLongitude(longitude);
                                },
                                throweble -> System.out.println(throweble))
        );
    }

    @Override
    public void showWeatherForCity(String cityName) {
        Intent intent = new Intent(context, WeatherDetailsActivity.class);
        intent.putExtra(INTENT_CITY_NAME, cityName);
        context.startActivity(intent);
    }


    @Override
    public void initCoordinate() {
        geolocation.initCoordinate();
    }

    @Override
    public void stopCoordinate() {
        geolocation.stopCoordinate();
        compositeDisposable.dispose();
    }

    @Override
    public void requestLocation() {
        geolocation.requestLocation();
    }
}
