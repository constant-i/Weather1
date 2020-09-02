package ru.geekbrains.android3.weather1.presentation.main;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import ru.geekbrains.android3.weather1.data.geo.Geolocation;

public class MainViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private Geolocation geolocation;
    Context context;

    MainViewModelFactory(Context context, Geolocation geolocation) {
        this.context = context;
        this.geolocation = geolocation;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass == MainViewModel.class) {
            return (T) new MainViewModel(geolocation);
        }
        return null;
    }
}
