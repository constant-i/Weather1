package ru.geekbrains.android3.weather1.data.repository;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import ru.geekbrains.android3.weather1.data.mapper.ForecastDataMapper;
import ru.geekbrains.android3.weather1.data.network.Api;
import ru.geekbrains.android3.weather1.domain.model.ForecastList;
import ru.geekbrains.android3.weather1.domain.repository.WeatherRepository;

public class WeatherRepositoryImpl implements WeatherRepository {

    private Api api;

    public WeatherRepositoryImpl(Api api) {
        this.api = api;
    }

    @Override
    public Single<ForecastList> getWeatherFor14Days(String city) {
        return api.loadWeatherFor14Days(city)
                .subscribeOn(Schedulers.io())
                .map(forecastResult -> ForecastDataMapper.convertFromDataModel(forecastResult));
    }

    @Override
    public Single<ForecastList> getWeatherByCoordinates(String latitude, String longitude) {
        return api.loadWeatherByCoordinate(latitude, longitude)
                .subscribeOn(Schedulers.io())
                .map(forecastResult -> ForecastDataMapper.convertFromDataModel(forecastResult));
    }
}
