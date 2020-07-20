package ru.geekbrains.android3.weather1.domain.repository;

import io.reactivex.Single;

public interface WeatherRepository {

    Single getWeather5Days (String cityCountry);

}
