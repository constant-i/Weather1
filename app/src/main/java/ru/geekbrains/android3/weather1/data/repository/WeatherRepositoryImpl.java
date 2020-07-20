package ru.geekbrains.android3.weather1.data.repository;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import ru.geekbrains.android3.weather1.data.model.List;
import ru.geekbrains.android3.weather1.data.network.Api;
import ru.geekbrains.android3.weather1.domain.repository.WeatherRepository;

public class WeatherRepositoryImpl implements WeatherRepository {

    private Api api;

    public WeatherRepositoryImpl(Api api) {
        this.api = api;
    }

    @Override
    public Single<java.util.List<List>> getWeather5Days(String cityCountry) {
        return api.loadWeather5Days(cityCountry)
                .subscribeOn(Schedulers.io())
                .map(response -> response.getList());
    }
}
