package ru.geekbrains.android3.weather1.domain.usecase;

import io.reactivex.Single;
import ru.geekbrains.android3.weather1.data.model.List;
import ru.geekbrains.android3.weather1.domain.repository.WeatherRepository;

public class WeatherInteractor {

    private WeatherRepository weatherRepository;

    public WeatherInteractor(WeatherRepository repository) {
        weatherRepository = repository;
    }

    public Single<java.util.List<List>> getWeaterForCity(String city) {
        return weatherRepository.getWeather5Days(city);
    }

}
