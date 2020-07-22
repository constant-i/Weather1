package ru.geekbrains.android3.weather1;

import android.app.Application;

import ru.geekbrains.android3.weather1.di.AppComponent;
import ru.geekbrains.android3.weather1.di.AppComponentModule;
import ru.geekbrains.android3.weather1.di.ContextModule;
import ru.geekbrains.android3.weather1.di.DaggerAppComponent;
import ru.geekbrains.android3.weather1.di.DataModule;

public class App extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerAppComponent
                .builder()
                .appComponentModule(new AppComponentModule())
                .contextModule(new ContextModule(this))
                .dataModule(new DataModule())
                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
