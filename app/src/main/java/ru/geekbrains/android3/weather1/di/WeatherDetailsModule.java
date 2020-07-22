package ru.geekbrains.android3.weather1.di;

import dagger.Module;
import dagger.Provides;
import ru.geekbrains.android3.weather1.domain.repository.WeatherRepository;
import ru.geekbrains.android3.weather1.domain.usecase.WeatherInteractor;

@Module
public class WeatherDetailsModule {

    @Provides
    @ActivityScope
    WeatherInteractor provideWeatherInteractor (WeatherRepository weatherRepo) {
        return new WeatherInteractor(weatherRepo);
    }
}
