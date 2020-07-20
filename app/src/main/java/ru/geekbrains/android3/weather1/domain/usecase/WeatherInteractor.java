package ru.geekbrains.android3.weather1.domain.usecase;

import io.reactivex.Single;
import ru.geekbrains.android3.weather1.data.model.List;
import ru.geekbrains.android3.weather1.data.model.WeatherResponse5DayModel;
import ru.geekbrains.android3.weather1.domain.repository.WeatherRepository;

public class WeatherInteractor {

    private WeatherRepository weatherRepository;

    public WeatherInteractor(WeatherRepository repository) {
        weatherRepository = repository;
    }

    public Single<WeatherResponse5DayModel/*java.util.List<List>*/> getWeatherForCity(String city) {
        return weatherRepository.getWeather5Days(city);
    }

    public Single<WeatherResponse5DayModel/*java.util.List<List>*/> getWeatherByCoord(String latitude, String longitude) {
        return weatherRepository.getWeatherByCoord5Days(latitude, longitude);
    }

}
