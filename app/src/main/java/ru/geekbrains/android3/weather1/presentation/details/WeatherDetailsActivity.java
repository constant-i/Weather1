package ru.geekbrains.android3.weather1.presentation.details;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import ru.geekbrains.android3.weather1.R;
import ru.geekbrains.android3.weather1.data.network.Api;
import ru.geekbrains.android3.weather1.data.network.RetrofitInit;
import ru.geekbrains.android3.weather1.data.repository.WeatherRepositoryImpl;
import ru.geekbrains.android3.weather1.domain.repository.WeatherRepository;
import ru.geekbrains.android3.weather1.domain.usecase.WeatherInteractor;

import static ru.geekbrains.android3.weather1.presentation.main.MainPresenterImpl.INTENT_CITY_NAME;
import static ru.geekbrains.android3.weather1.presentation.main.MainPresenterImpl.INTENT_LATITUDE;
import static ru.geekbrains.android3.weather1.presentation.main.MainPresenterImpl.INTENT_LONGITUDE;

public class WeatherDetailsActivity extends AppCompatActivity {

    WeatherAdapter wAdapter;
    WeatherViewModel wViewModel;

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
        Api api = RetrofitInit.newApiInstance();
        WeatherRepository wRepository = new WeatherRepositoryImpl(api);
        WeatherInteractor wInteractor = new WeatherInteractor(wRepository);

        wViewModel = ViewModelProviders.of(this, new WeatherViewModelFactory(wInteractor)).get(WeatherViewModel.class);
    }

    private void initRecycleView() {
        wAdapter = new WeatherAdapter();
        RecyclerView rv = findViewById(R.id.weather_rec_view);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(wAdapter);
    }
}
