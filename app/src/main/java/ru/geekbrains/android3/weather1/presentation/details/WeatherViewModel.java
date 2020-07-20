package ru.geekbrains.android3.weather1.presentation.details;

import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import io.reactivex.disposables.CompositeDisposable;
import ru.geekbrains.android3.weather1.data.model.List;
import ru.geekbrains.android3.weather1.domain.usecase.WeatherInteractor;

//public class WeatherViewModel extends ViewModel implements LifecycleObserver {
public class WeatherViewModel extends ViewModel {

    private WeatherInteractor weatherInteractor;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public MutableLiveData<java.util.List<List>> weatherLiveData = new MutableLiveData<>();
    public MutableLiveData<String> cityNameLiveData = new MutableLiveData<>();

    public WeatherViewModel (WeatherInteractor wInteractor) {
        weatherInteractor = wInteractor;
    }

    public void getWeatherForCity(String city) {
        compositeDisposable.add(
                weatherInteractor.getWeaterForCity(city)
                .subscribe(result -> weatherLiveData.postValue(result),
                throweble -> System.out.println(throweble))
        );
    }

    @Override
    protected void onCleared() {
        compositeDisposable.clear();
        super.onCleared();
    }

}
