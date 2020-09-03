package ru.geekbrains.android3.weather1.di.main_activity;

import dagger.Component;
import ru.geekbrains.android3.weather1.data.geo.Geolocation;
import ru.geekbrains.android3.weather1.di.AppComponent;
import ru.geekbrains.android3.weather1.presentation.main.MainActivity;


@Component(modules = {MainActivityModule.class},
        dependencies = {AppComponent.class})
@MainActivityScope
public interface MainActivityComponent {
//    Geolocation getGeolocation();
    void inject(MainActivity activity);
}
