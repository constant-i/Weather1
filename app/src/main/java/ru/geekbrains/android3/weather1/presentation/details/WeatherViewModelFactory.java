package ru.geekbrains.android3.weather1.presentation.details;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import ru.geekbrains.android3.weather1.domain.usecase.WeatherInteractor;

public class WeatherViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private WeatherInteractor weatherInteractor;

    public WeatherViewModelFactory(WeatherInteractor wInteractor) {
        weatherInteractor = wInteractor;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if(modelClass == WeatherViewModel.class) {
            return (T) new WeatherViewModel(weatherInteractor);
        }
        return null;
    }
}
