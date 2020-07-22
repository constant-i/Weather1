package ru.geekbrains.android3.weather1.di;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.geekbrains.android3.weather1.data.network.Api;
import ru.geekbrains.android3.weather1.data.repository.WeatherRepositoryImpl;
import ru.geekbrains.android3.weather1.domain.repository.WeatherRepository;

@Module
public class DataModule {

    @Provides
    @Singleton
    WeatherRepository provideWeatherRepository(Api api) {
        return new WeatherRepositoryImpl(api);
    }
}
