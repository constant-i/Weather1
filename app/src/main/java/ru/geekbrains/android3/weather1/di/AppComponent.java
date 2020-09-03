package ru.geekbrains.android3.weather1.di;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import ru.geekbrains.android3.weather1.domain.repository.WeatherRepository;

@Component(modules = {
        AppComponentModule.class,
        ContextModule.class,
        DataModule.class
})
@Singleton
public interface AppComponent {
    WeatherRepository getWeatherRepository();
    Context getContext();
}
