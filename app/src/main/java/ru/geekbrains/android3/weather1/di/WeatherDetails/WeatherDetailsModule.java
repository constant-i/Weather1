package ru.geekbrains.android3.weather1.di.WeatherDetails;

import dagger.Module;
import dagger.Provides;
import ru.geekbrains.android3.weather1.di.ActivityScope;
import ru.geekbrains.android3.weather1.domain.repository.WeatherRepository;
import ru.geekbrains.android3.weather1.domain.usecase.WeatherInteractor;
import ru.geekbrains.android3.weather1.presentation.weather_details.WeatherAdapter;

@Module
public class WeatherDetailsModule {

    @Provides
    @ActivityScope
    WeatherInteractor provideWeatherInteractor(WeatherRepository weatherRepo) {
        return new WeatherInteractor(weatherRepo);
    }

    @Provides
    @ActivityScope
    WeatherAdapter provideWeatherAdapter() {
        return new WeatherAdapter();
    }
}
