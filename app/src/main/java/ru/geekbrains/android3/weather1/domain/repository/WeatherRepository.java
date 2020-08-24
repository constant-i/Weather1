package ru.geekbrains.android3.weather1.domain.repository;

import io.reactivex.Single;

public interface WeatherRepository {

    Single getWeather5Days(String cityCountry);

    Single getWeatherByCoord5Days(String latitude, String longitude);

    Single loadWeatherFor14Days (String cityCountry);
}
