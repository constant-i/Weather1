package ru.geekbrains.android3.weather1.di.WeatherDetails;

import android.content.Context;

import dagger.Component;
import ru.geekbrains.android3.weather1.di.ActivityScope;
import ru.geekbrains.android3.weather1.di.AppComponent;
import ru.geekbrains.android3.weather1.domain.repository.WeatherRepository;
import ru.geekbrains.android3.weather1.domain.usecase.WeatherInteractor;
import ru.geekbrains.android3.weather1.presentation.weather_details.WeatherAdapter;
import ru.geekbrains.android3.weather1.presentation.weather_details.WeatherDetailsActivity;

@Component(modules = WeatherDetailsModule.class,
        dependencies = AppComponent.class
)
@ActivityScope
public interface WeatherDetailsComponent {

//    WeatherInteractor getWeatherInteractor();
//    WeatherRepository getWeatherRepository();
//    WeatherAdapter getWeatherAdapter();
//    Context getContext(); // можно получить, т.к. AppComponent предоставляет Context

    void inject(WeatherDetailsActivity activity);
}
