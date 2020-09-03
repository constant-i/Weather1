package ru.geekbrains.android3.weather1.di.weather_details;

import dagger.Component;
import ru.geekbrains.android3.weather1.di.AppComponent;
import ru.geekbrains.android3.weather1.domain.repository.WeatherRepository;
import ru.geekbrains.android3.weather1.domain.usecase.WeatherInteractor;
import ru.geekbrains.android3.weather1.presentation.details.WeatherAdapter;
import ru.geekbrains.android3.weather1.presentation.details.WeatherDetailsActivity;

@Component(modules = WeatherDetailsModule.class,
        dependencies = AppComponent.class
)
@WeatherDetailsScope
public interface WeatherDetailsComponent {
//    WeatherInteractor getWeatherInteractor();
//    WeatherRepository getWeatherRepository();
//    WeatherAdapter getWeatherAdapter();
    void inject(WeatherDetailsActivity activity);
}
