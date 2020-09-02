package ru.geekbrains.android3.weather1.domain.usecase;

import io.reactivex.Single;
import ru.geekbrains.android3.weather1.domain.model.ForecastList;
import ru.geekbrains.android3.weather1.domain.repository.WeatherRepository;

public class WeatherInteractor {

    private WeatherRepository weatherRepository;

    public WeatherInteractor(WeatherRepository repository) {
        weatherRepository = repository;
    }

    public Single<ForecastList> getWeatherFor14Days (String city) {
        return weatherRepository.getWeatherFor14Days(city);
    }

    public Single<ForecastList> getWeatherByCoord (String latitude, String longitude) {
        return weatherRepository.getWeatherByCoordinates(latitude, longitude);
    }
}
