package ru.geekbrains.android3.weather1.di.MainActivity;

import dagger.Component;
import ru.geekbrains.android3.weather1.data.geo.Geolocation;
import ru.geekbrains.android3.weather1.di.ActivityScope;
import ru.geekbrains.android3.weather1.di.AppComponent;
import ru.geekbrains.android3.weather1.presentation.main.MainActivity;

@Component(modules = {GeolocationModule.class},
        dependencies = AppComponent.class
)
@ActivityScope
public interface GeolocationComponent {

    Geolocation getGeolocation();

    void inject(MainActivity activity);
}
