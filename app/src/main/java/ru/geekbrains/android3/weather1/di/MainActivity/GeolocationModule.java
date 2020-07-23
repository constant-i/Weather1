package ru.geekbrains.android3.weather1.di.MainActivity;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import ru.geekbrains.android3.weather1.data.geo.Geolocation;

@Module
public class GeolocationModule {

    @Provides
    Geolocation providesGeolocation(Context context) {
        return new Geolocation(context);
    }
}
