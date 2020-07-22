package ru.geekbrains.android3.weather1.presentation.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

import ru.geekbrains.android3.weather1.R;
import ru.geekbrains.android3.weather1.data.geo.Geolocation;

public class MainActivity extends AppCompatActivity {

    private TextInputEditText editCity;
    private TextView textLatitude;
    private TextView textLongitude;
    private Button btnGetWeather;
    private Button btnLocalWeather;
    private String cityName;

    private GeoViewModel geoViewModel;

    private static final int PERMISSION_REQUEST_CODE = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViewModel();

        initGui();
//
//        geoViewModel.latitude.observe(this, data -> {
//            textLatitude.setVisibility(View.VISIBLE);
//            textLatitude.setText(data);
//            btnLocalWeather.setEnabled(true);
//        });
//
//        geoViewModel.longitude.observe(this, data -> {
//            textLongitude.setVisibility(View.VISIBLE);
//            textLongitude.setText(data);
//            btnLocalWeather.setEnabled(true);
//        });

    }

    private void initGui() {
        editCity = findViewById(R.id.editCity);
        textLatitude = findViewById(R.id.latitude_view);
        textLongitude = findViewById(R.id.longitude_view);
        btnGetWeather = findViewById(R.id.btn_get_weather);
        btnGetWeather.setOnClickListener(v -> {
            cityName = editCity.getText().toString();
            geoViewModel.showWeatherForCity(cityName);
        });

        btnLocalWeather = findViewById(R.id.btn_get_location_weather);
        btnLocalWeather.setOnClickListener(v -> {
            geoViewModel.showWeatherByCoordinates();
        });

        geoViewModel.latitude.observe(this, data -> {
            textLatitude.setVisibility(View.VISIBLE);
            textLatitude.setText(data);
            btnLocalWeather.setEnabled(true);
        });

        geoViewModel.longitude.observe(this, data -> {
            textLongitude.setVisibility(View.VISIBLE);
            textLongitude.setText(data);
            btnLocalWeather.setEnabled(true);
        });

    }

    private void initViewModel() {
        Geolocation geolocation = new Geolocation(this);
        geoViewModel = ViewModelProviders.of(this, new GeoViewModelFactory(this, geolocation)).get(GeoViewModel.class);
    }

    @Override
    protected void onStart() {
        super.onStart();
        geoViewModel.initCoordinate();
    }

    @Override
    protected void onStop() {
        super.onStop();
        geoViewModel.stopCoordinate();
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PERMISSION_REQUEST_CODE) {   // Это та самая пермиссия, что мы запрашивали?
            if (grantResults.length == 2 &&
                    (grantResults[0] == PackageManager.PERMISSION_GRANTED || grantResults[1] == PackageManager.PERMISSION_GRANTED)) {
                geoViewModel.requestLocation(); // пермиссия дана
            }
        }
    }
}
