package ru.geekbrains.android3.weather1.presentation.main;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.textfield.TextInputEditText;

import javax.inject.Inject;

import ru.geekbrains.android3.weather1.App;
import ru.geekbrains.android3.weather1.data.geo.Geolocation;
import ru.geekbrains.android3.weather1.R;
import ru.geekbrains.android3.weather1.di.main_activity.DaggerMainActivityComponent;
import ru.geekbrains.android3.weather1.di.main_activity.MainActivityModule;

public class MainActivity extends AppCompatActivity {

    private TextInputEditText editCity;
    private TextView textLatitude;
    private TextView textLongitude;
    private Button btnGetWeather;
    private Button btnLocalWeather;
    private String cityName;

    private MainViewModel mainViewModel;

    private static final int PERMISSION_REQUEST_CODE = 10;

    @Inject
    Geolocation geolocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        injectMainActivityDependencies();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initViewModel();
        initGui();
        mainViewModel.initCoordinate();
        Log.d("MyTAG", "onResume() initCoordinate()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        mainViewModel.stopCoordinate();
        mainViewModel.disposeGeoSubject();
        Log.d("MyTAG", "onPause() stopCoordinate(); clear();");
    }

    private void injectMainActivityDependencies() {
        DaggerMainActivityComponent.builder()
                .mainActivityModule(new MainActivityModule())
                .appComponent(((App)getApplication()).getAppComponent())
                .build()
                .inject(this);
    }

    private void initViewModel() {
        mainViewModel = ViewModelProviders.of(this, new MainViewModelFactory(this, geolocation)).get(MainViewModel.class);
    }

    private void initGui() {
        editCity = findViewById(R.id.editCity);
        textLatitude = findViewById(R.id.latitude_view);
        textLongitude = findViewById(R.id.longitude_view);

        btnGetWeather = findViewById(R.id.btn_get_weather);
        btnGetWeather.setOnClickListener(v -> {
            cityName = editCity.getText().toString();
            mainViewModel.showWeatherForCity(this, cityName);
        });

        btnLocalWeather = findViewById(R.id.btn_get_local_weather);
        btnLocalWeather.setOnClickListener(v -> {
            mainViewModel.showWeatherByCoord(this);
        });

        mainViewModel.liveDataLatitude.observe(this, data -> {
            textLatitude.setVisibility(View.VISIBLE);
            textLatitude.setText(data);
            btnLocalWeather.setEnabled(true);
        });

        mainViewModel.liveDataLongitude.observe(this, data -> {
            textLongitude.setVisibility(View.VISIBLE);
            textLongitude.setText(data);
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length == 2 &&
                    (grantResults[0] == PackageManager.PERMISSION_GRANTED || grantResults[1] == PackageManager.PERMISSION_GRANTED)) {
                mainViewModel.requestLocation();
            }
        }
    }
}