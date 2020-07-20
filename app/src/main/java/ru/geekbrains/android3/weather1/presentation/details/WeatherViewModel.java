package ru.geekbrains.android3.weather1.presentation.details;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import io.reactivex.disposables.CompositeDisposable;
import ru.geekbrains.android3.weather1.data.model.List;
import ru.geekbrains.android3.weather1.domain.usecase.WeatherInteractor;

//public class WeatherViewModel extends ViewModel implements LifecycleObserver {
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
                        .subscribe(result -> {
                                    weatherLiveData.postValue(result.getList());
                                    cityNameLiveData.postValue(result.getCity().getName());
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
