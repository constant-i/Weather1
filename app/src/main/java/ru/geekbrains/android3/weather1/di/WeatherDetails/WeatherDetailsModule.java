package ru.geekbrains.android3.weather1.di.WeatherDetails;

import dagger.Module;
import dagger.Provides;
import ru.geekbrains.android3.weather1.domain.repository.WeatherRepository;
import ru.geekbrains.android3.weather1.domain.usecase.WeatherInteractor;
import ru.geekbrains.android3.weather1.presentation.details.WeatherAdapter;

@Module
public class WeatherDetailsModule {

    @Provides
    @WeatherDetailsScope
    WeatherInteractor provideWeatherInteractor(WeatherRepository weatherRepo) {
        return new WeatherInteractor(weatherRepo);
    }

    @Provides
    @WeatherDetailsScope
    WeatherAdapter provideWeatherAdapter() {
        return new WeatherAdapter();
    }
}
