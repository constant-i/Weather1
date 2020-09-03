package ru.geekbrains.android3.weather1.presentation.details;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import ru.geekbrains.android3.weather1.domain.model.ModelForecast;
import ru.geekbrains.android3.weather1.domain.usecase.WeatherInteractor;


public class WeatherViewModel extends ViewModel {

    private WeatherInteractor weatherInteractor;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    MutableLiveData<String> cityNameLiveData = new MutableLiveData<>();
    MutableLiveData<List<ModelForecast>> forecastLiveData = new MutableLiveData<>();

    WeatherViewModel(WeatherInteractor weatherInteractor) {
        this.weatherInteractor = weatherInteractor;
    }

    void getWeatherFor14Days(String city) {
        compositeDisposable.add(
                weatherInteractor.getWeatherFor14Days(city)
                        .subscribe(forecastList -> {
                                    cityNameLiveData.postValue(forecastList.getCity() + ", " + forecastList.getCountry());
                                    forecastLiveData.postValue(forecastList.getDailyForecast());
                                },
                                throwable -> {
                                    if (throwable.getMessage().contains("404"))
                                        cityNameLiveData.postValue("city not found: " + city);
                                    else
                                        cityNameLiveData.postValue(throwable.getMessage());
                                })
        );
    }

    void getWeatherByCoordinates(String latitude, String longitude) {
        compositeDisposable.add(
                weatherInteractor.getWeatherByCoord(latitude, longitude)
                        .subscribe(forecastList -> {
                                    cityNameLiveData.postValue(forecastList.getCity() + ", " + forecastList.getCountry());
                                    forecastLiveData.postValue(forecastList.getDailyForecast());
                                },
                                System.out::println)
        );
    }

    @Override
    protected void onCleared() {
        compositeDisposable.clear();
        super.onCleared();
    }
}
