package ru.geekbrains.android3.weather1.data.network;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;
import ru.geekbrains.android3.weather1.data.model.ForecastResult;

public interface Api {

    String APPID_LEIVA = "15646a06818f61f7b8d7823ca833e1ce";

    @GET("data/2.5/forecast/daily?mode=json&units=metric&cnt=14&APPID=" + APPID_LEIVA)
    Single<ForecastResult> loadWeatherFor14Days(@Query("q") String city);

    @GET("data/2.5/forecast/daily?mode=json&units=metric&cnt=14&APPID=" + APPID_LEIVA)
    Single<ForecastResult> loadWeatherByCoordinate(@Query("lat") String latitude,
                                                   @Query("lon") String longitude);
}
