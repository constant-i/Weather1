package ru.geekbrains.android3.weather1.di.main_activity;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import ru.geekbrains.android3.weather1.data.geo.Geolocation;

@Module
public class MainActivityModule {
    @Provides
    @MainActivityScope
    Geolocation provideGeolocation(Context context) {
        return new Geolocation(context);
    }
}
