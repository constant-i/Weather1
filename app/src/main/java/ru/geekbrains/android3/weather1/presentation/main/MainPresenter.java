package ru.geekbrains.android3.weather1.presentation.main;

public interface MainPresenter {
    void showWeatherForCity(String cityName);

    void initCoordinate();
    void stopCoordinate();
    void requestLocation();
}
