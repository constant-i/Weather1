package ru.geekbrains.android3.weather1.data.mapper;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import ru.geekbrains.android3.weather1.data.model.Forecast;
import ru.geekbrains.android3.weather1.data.model.ForecastResult;
import ru.geekbrains.android3.weather1.domain.model.ForecastList;
import ru.geekbrains.android3.weather1.domain.model.ModelForecast;


public class ForecastDataMapper {

    private static String BASE_IMG_URL = "http://openweathermap.org/img/w/";
    private static String DOT_IMG = ".png";

    public static ForecastList convertFromDataModel(ForecastResult forecast) {
        return new ForecastList(
                forecast.getCity().getName(),
                forecast.getCity().getCountry(),
                convertForecastListToDomain(forecast.getList()));
    }

    private static List<ModelForecast> convertForecastListToDomain(List<Forecast> list) {
        List<ModelForecast> modelForecastList = new ArrayList<>();
        for (Forecast forecast : list)
            modelForecastList.add(convertForecastItemToDomain(forecast));
        return modelForecastList;
    }

    private static ModelForecast convertForecastItemToDomain(Forecast forecast) {
        return new ModelForecast(
                convertDate(forecast.getDt()),
                forecast.getWeather().get(0).getDescription(),
                (Integer) Math.round(forecast.getTemp().getMax()),
                (Integer) Math.round(forecast.getTemp().getMin()),
                generateIconUrl(forecast.getWeather().get(0).getIcon()));
    }

    private static String convertDate(Long date) {
        DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.getDefault());
        return df.format(date * 1000);
    }

    private static String generateIconUrl(String iconCode) {
        return BASE_IMG_URL + iconCode + DOT_IMG;
    }
}
