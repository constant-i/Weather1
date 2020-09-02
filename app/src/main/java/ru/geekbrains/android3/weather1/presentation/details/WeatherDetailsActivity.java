package ru.geekbrains.android3.weather1.presentation.details;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import javax.inject.Inject;

import ru.geekbrains.android3.weather1.App;
import ru.geekbrains.android3.weather1.di.weather_details.DaggerWeatherDetailsComponent;
import ru.geekbrains.android3.weather1.di.weather_details.WeatherDetailsModule;
import ru.geekbrains.android3.weather1.domain.usecase.WeatherInteractor;
import ru.geekbrains.android3.weather1.R;

import static ru.geekbrains.android3.weather1.presentation.main.MainViewModel.INTENT_CITY_NAME;
import static ru.geekbrains.android3.weather1.presentation.main.MainViewModel.INTENT_LATITUDE;
import static ru.geekbrains.android3.weather1.presentation.main.MainViewModel.INTENT_LONGITUDE;


public class WeatherDetailsActivity extends AppCompatActivity {

    WeatherViewModel weatherViewModel;

    @Inject
    WeatherAdapter weatherAdapter;
    @Inject
    WeatherInteractor weatherInteractor;

    private String city;
    private String latitude;
    private String longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_details);

        injectWeatherDetailsDependencies();
        initViewModel();
        initRecycleView();

        if (getIntent().getStringExtra(INTENT_CITY_NAME) != null) {
            city = getIntent().getStringExtra(INTENT_CITY_NAME);
            weatherViewModel.getWeatherFor14Days(city);
        } else if (getIntent().getStringExtra(INTENT_LATITUDE) != null
                && getIntent().getStringExtra(INTENT_LONGITUDE) != null) {
            latitude = getIntent().getStringExtra(INTENT_LATITUDE);
            longitude = getIntent().getStringExtra(INTENT_LONGITUDE);
            weatherViewModel.getWeatherByCoordinates(latitude, longitude);
        }

        weatherViewModel.cityNameLiveData.observe(this,
                data -> ((TextView) findViewById(R.id.city_name)).setText(data));
        weatherViewModel.forecastLiveData.observe(this,
                data -> weatherAdapter.setModelForecastList(data));

    }

    private void injectWeatherDetailsDependencies() {
        DaggerWeatherDetailsComponent.builder()
                .weatherDetailsModule(new WeatherDetailsModule())
                .appComponent(((App) getApplication()).getAppComponent())
                .build()
                .inject(this);
    }

    private void initViewModel() {
        weatherViewModel = ViewModelProviders.of(this, new WeatherViewModelFactory(weatherInteractor))
                        .get(WeatherViewModel.class);
    }


    private void initRecycleView() {
        RecyclerView recyclerWeather = findViewById(R.id.weather_rec_view);
        recyclerWeather.setLayoutManager(new LinearLayoutManager(this));
        recyclerWeather.setAdapter(weatherAdapter);
    }
}