package ru.geekbrains.android3.weather1.presentation.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.ViewModelProviders;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

import ru.geekbrains.android3.weather1.R;
import ru.geekbrains.android3.weather1.data.geo.Geolocation;

public class MainActivity extends AppCompatActivity implements MainView {

    private TextInputEditText editCity;
    private TextView textLatitude;
    private TextView textLongitude;
    private Button btnGetWeather;
    private Button btnLocalWeather;
    private String cityName;

//    private MainPresenter mainPresenter;
    private GeoViewModel geoViewModel;


    /**/
//    private LocationManager locationManager;
//    private String provider;
//    private String latitude;
//    private String longitude;

    private static final int PERMISSION_REQUEST_CODE = 10;
    /**/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViewModel();

//        mainPresenter = new MainPresenterImpl(this);
        
        initGui();
    }

    private void initGui() {
        editCity = findViewById(R.id.editCity);
        textLatitude = findViewById(R.id.latitude_view);
        textLongitude = findViewById(R.id.longitude_view);
        btnGetWeather = findViewById(R.id.btn_get_weather);
        btnGetWeather.setOnClickListener(v -> {
            cityName = editCity.getText().toString();
//            mainPresenter.showWeatherForCity(cityName);
            geoViewModel.showWeatherForCity(cityName);
        });

        btnLocalWeather = findViewById(R.id.btn_get_location_weather);
        btnLocalWeather.setOnClickListener(v -> {
            // TODO
        });
    }

    private void initViewModel() {
        Geolocation geolocation = new Geolocation(this);
        geoViewModel = ViewModelProviders.of(this, new GeoViewModelFactory(this, geolocation)).get(GeoViewModel.class);
    }

/* Geo */
//    private void initCoordinate() {
//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
//                || ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
//            // запросим координаты
//            requestLocation();
//            //Log.d("MyTAG", "requestLocation()");
//        } else {
//            // пермиссии нет, будем запрашивать у пользователя
//            requestLocationPermissions();
//        }
//    }

//    private void requestLocation() {
//
//        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
//        Criteria criteria = new Criteria();
//        criteria.setAccuracy(Criteria.ACCURACY_COARSE);
//        provider = locationManager.getBestProvider(criteria, true);
//        if (provider != null) {
//            // Будем получать геоположение через каждые 10 секунд или каждые 10 метров
//            locationManager.requestLocationUpdates(provider, 10000, 10, listener);
//            //Log.d("MyTAG", "inside requestLocation()");
//        }
//    }

//    private LocationListener listener = new LocationListener() {
//        @Override
//        public void onLocationChanged(Location location) {
//            Log.d("MyTAG", "inside onLocationChanged");
//            latitude = Double.toString(location.getLatitude());  // Широта
//            longitude = Double.toString(location.getLongitude());// Долгота
////            textLatitude.setText(latitude);
////            textLongitude.setText(longitude);
//        }
//
//        @Override
//        public void onStatusChanged(String provider, int status, Bundle extras) {
//        }
//
//        @Override
//        public void onProviderEnabled(String provider) {
//        }
//
//        @Override
//        public void onProviderDisabled(String provider) {
//        }
//    };

    @Override
    protected void onStart() {
        super.onStart();
//        mainPresenter.initCoordinate();
        geoViewModel.initCoordinate();
    }

    @Override
    protected void onStop() {
        super.onStop();
//        mainPresenter.stopCoordinate();
        geoViewModel.stopCoordinate();
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PERMISSION_REQUEST_CODE) {   // Это та самая пермиссия, что мы запрашивали?
            if (grantResults.length == 2 &&
                    (grantResults[0] == PackageManager.PERMISSION_GRANTED || grantResults[1] == PackageManager.PERMISSION_GRANTED)) {
                // Все препоны пройдены и пермиссия дана
//                mainPresenter.requestLocation();
                geoViewModel.requestLocation();
            }
        }
    }

    @Override
    public MainView getView() {
        return this;
    }

    @Override
    public void setLatitude(String latitude) {
        if (textLatitude.getVisibility() != View.VISIBLE) textLatitude.setVisibility(View.VISIBLE);
        textLatitude.setText(latitude);
    }

    @Override
    public void setLongitude(String longitude) {
        if (textLongitude.getVisibility() != View.VISIBLE) textLongitude.setVisibility(View.VISIBLE);
        textLongitude.setText(longitude);
    }

    @Override
    public void makeGeoBtnVisible() {
        //if (!btnLocalWeather.isActivated())
            btnLocalWeather.setActivated(true);
            btnLocalWeather.setEnabled(true);
    }

    /*Geo*/
}
