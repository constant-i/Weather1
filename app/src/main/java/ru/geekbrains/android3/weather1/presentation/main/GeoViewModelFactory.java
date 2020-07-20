package ru.geekbrains.android3.weather1.presentation.main;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import ru.geekbrains.android3.weather1.data.geo.Geolocation;

public class GeoViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    Context context;
    private Geolocation geolocation;

    public GeoViewModelFactory (Context cont,Geolocation geo) {
        context = cont;
        geolocation = geo;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass == GeoViewModel.class) {
            return (T) new GeoViewModel(context ,geolocation);
        }
        return null;
    }
}
