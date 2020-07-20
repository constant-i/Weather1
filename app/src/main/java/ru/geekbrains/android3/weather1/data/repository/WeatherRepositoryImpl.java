package ru.geekbrains.android3.weather1.data.repository;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import ru.geekbrains.android3.weather1.data.model.WeatherResponse5DayModel;
import ru.geekbrains.android3.weather1.data.network.Api;
import ru.geekbrains.android3.weather1.domain.repository.WeatherRepository;

public class WeatherRepositoryImpl implements WeatherRepository {

    private Api api;

    public WeatherRepositoryImpl(Api api) {
        this.api = api;
    }

    @Override
    public Single<WeatherResponse5DayModel> getWeather5Days(String cityCountry) {
        return api.loadWeather5Days(cityCountry)
                .subscribeOn(Schedulers.io())
                .map(response -> response);
    }

    @Override
    public Single<WeatherResponse5DayModel> getWeatherByCoord5Days(String latitude, String longitude) {
        return api.loadCoordinateWeather5Days(latitude, longitude)
                .subscribeOn(Schedulers.io())
                .map(response -> response);
    }
}
