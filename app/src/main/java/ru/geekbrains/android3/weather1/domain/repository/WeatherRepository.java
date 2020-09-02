package ru.geekbrains.android3.weather1.domain.repository;

import io.reactivex.Single;

public interface WeatherRepository {

    Single getWeatherFor14Days(String city);

    Single getWeatherByCoordinates(String latitude, String longitude);
}
