package ru.geekbrains.android3.weather1.presentation.weather_details;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import javax.inject.Inject;

import ru.geekbrains.android3.weather1.App;
import ru.geekbrains.android3.weather1.R;
import ru.geekbrains.android3.weather1.data.network.Api;
import ru.geekbrains.android3.weather1.data.network.RetrofitInit;
import ru.geekbrains.android3.weather1.data.repository.WeatherRepositoryImpl;
import ru.geekbrains.android3.weather1.di.AppComponent;
import ru.geekbrains.android3.weather1.di.DaggerWeatherDetailsComponent;
import ru.geekbrains.android3.weather1.di.WeatherDetailsComponent;
import ru.geekbrains.android3.weather1.di.WeatherDetailsModule;
import ru.geekbrains.android3.weather1.domain.repository.WeatherRepository;
import ru.geekbrains.android3.weather1.domain.usecase.WeatherInteractor;

import static ru.geekbrains.android3.weather1.presentation.main.GeoViewModel.INTENT_CITY_NAME;
import static ru.geekbrains.android3.weather1.presentation.main.GeoViewModel.INTENT_LATITUDE;
import static ru.geekbrains.android3.weather1.presentation.main.GeoViewModel.INTENT_LONGITUDE;

public class WeatherDetailsActivity extends AppCompatActivity {

    WeatherAdapter wAdapter;
    WeatherViewModel wViewModel;

    @Inject
    WeatherInteractor wInteractor;

    String city;
    String latitude;
    String longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_details);

        InitViewModel();
        initRecycleView();

        if (getIntent().getStringExtra(INTENT_CITY_NAME) != null) {
            city = getIntent().getStringExtra(INTENT_CITY_NAME);
            wViewModel.getWeatherForCity(city);
        } else if (getIntent().getStringExtra(INTENT_LATITUDE) != null && getIntent().getStringExtra(INTENT_LONGITUDE) != null) {
            latitude = getIntent().getStringExtra(INTENT_LATITUDE);
            longitude = getIntent().getStringExtra(INTENT_LONGITUDE);
            wViewModel.getWeatherForCoordinates(latitude, longitude);
        }

        wViewModel.cityNameLiveData.observe(this, data -> ((TextView) findViewById(R.id.city_name)).setText(data));

        wViewModel.weatherLiveData.observe(this, data -> wAdapter.setWeatherList(data));
    }

    private void InitViewModel() {
        AppComponent appComponent = ((App) getApplication()).getAppComponent();

        DaggerWeatherDetailsComponent.builder()
                .weatherDetailsModule(new WeatherDetailsModule())
                .appComponent(appComponent)
                .build()
                .inject(this);

        wViewModel = ViewModelProviders.of(this, new WeatherViewModelFactory(wInteractor)).get(WeatherViewModel.class);
    }

    private void initRecycleView() {
        wAdapter = new WeatherAdapter();
        RecyclerView rv = findViewById(R.id.weather_rec_view);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(wAdapter);
    }
}
