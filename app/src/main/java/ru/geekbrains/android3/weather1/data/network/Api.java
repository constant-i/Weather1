package ru.geekbrains.android3.weather1.data.network;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;
import ru.geekbrains.android3.weather1.data.model.ResponseClasses;
import ru.geekbrains.android3.weather1.data.model.WeatherResponse5DayModel;

public interface Api {

    String APPID = "29fb8d6056fe0f462c0731feda71d342";
    String APPID_LEIVA = "15646a06818f61f7b8d7823ca833e1ce";

//  запрос на 5 дней город
//  http://api.openweathermap.org/data/2.5/forecast?q=Moscow&units=metric&appid=29fb8d6056fe0f462c0731feda71d342
    @GET("data/2.5/forecast?appid=" + APPID + "&units=metric")
    Single<WeatherResponse5DayModel> loadWeather5Days(@Query("q") String cityCountry);

//  запрос на 5 дней широта-долгота
//  http://api.openweathermap.org/data/2.5/forecast?lat=35&lon=139&units=metric&appid=29fb8d6056fe0f462c0731feda71d342
    @GET("data/2.5/forecast?appid=" + APPID + "&units=metric")
    Single<WeatherResponse5DayModel> loadCoordinateWeather5Days(@Query("lat") String latitude,
                                                                @Query("lon") String longitude);

//    private val URL = "http://api.openweathermap.org/data/2.5/forecast/daily?mode=json&units=metric&cnt=14" // todo тут задаю кол-во дней
//    private val COMPLETE_URL = "$URL&APPID=$APP_ID&q="
//        val URL = "http://api.openweathermap.org/data/2.5/forecast?q=Moscow&units=metric&appid=29fb8d6056fe0f462c0731feda71d342"

    @GET("data/2.5/forecast/daily?mode=json&units=metric&cnt=14&APPID=" + APPID_LEIVA)
    Single<ResponseClasses.ForecastResult> loadWeatherFor14Days (@Query("q") String cityCountry);



}
