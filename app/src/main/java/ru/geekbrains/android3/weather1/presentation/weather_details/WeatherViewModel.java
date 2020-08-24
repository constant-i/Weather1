package ru.geekbrains.android3.weather1.presentation.weather_details;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import ru.geekbrains.android3.weather1.data.model.List;
import ru.geekbrains.android3.weather1.data.model.ResponseClasses;
import ru.geekbrains.android3.weather1.data.model.WeatherResponse5DayModel;
import ru.geekbrains.android3.weather1.domain.usecase.WeatherInteractor;

public class WeatherViewModel extends ViewModel {

    private WeatherInteractor weatherInteractor;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    MutableLiveData<java.util.List<List>> weatherLiveData = new MutableLiveData<>();
    MutableLiveData<String> cityNameLiveData = new MutableLiveData<>();

    WeatherViewModel(WeatherInteractor wInteractor) {
        weatherInteractor = wInteractor;
    }

    void getWeatherForCity(String city) {
        compositeDisposable.add(
                weatherInteractor.getWeatherForCity(city)
                        .subscribe(new Consumer<WeatherResponse5DayModel>() {
                                       @Override
                                       public void accept(WeatherResponse5DayModel result) throws Exception {
                                           weatherLiveData.postValue(result.getList());
                                           cityNameLiveData.postValue(result.getCity().getName());
                                       }
                                   },
                                throwable -> System.out.println(throwable))
        );
    }

    void getWeatherFor14Days(String city) {
        compositeDisposable.add(
                weatherInteractor.getWeatherFor14Days(city)
                        .subscribe(new Consumer<ResponseClasses.ForecastResult>() {
                                       @Override
                                       public void accept(ResponseClasses.ForecastResult forecastResult) throws Exception {
                                           cityNameLiveData.postValue(forecastResult.getCity().getName());
                                       }
                                   },
                                throwable -> System.out.println(throwable))
        );
    }


    void getWeatherForCoordinates(String latitude, String longitude) {
        compositeDisposable.add(
                weatherInteractor.getWeatherByCoord(latitude, longitude)
                        .subscribe(result -> {
                                    weatherLiveData.postValue(result.getList());
                                    cityNameLiveData.postValue(result.getCity().getName());
                                },
                                throwable -> System.out.println(throwable))
        );
    }

    @Override
    protected void onCleared() {
        compositeDisposable.clear();
        super.onCleared();
    }
}
