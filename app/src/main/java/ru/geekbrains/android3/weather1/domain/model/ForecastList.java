package ru.geekbrains.android3.weather1.domain.model;

import java.util.List;

public class ForecastList {

    private String city;
    private String country;
    private List<ModelForecast> dailyForecast;

    public ForecastList(String city, String country, List<ModelForecast> dailyForecast) {
        this.city = city;
        this.country = country;
        this.dailyForecast = dailyForecast;
    }

    public ModelForecast get(Integer position) {
        return dailyForecast.get(position);
    }

    public Integer size() {
        return dailyForecast.size();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<ModelForecast> getDailyForecast() {
        return dailyForecast;
    }

    public void setDailyForecast(List<ModelForecast> dailyForecast) {
        this.dailyForecast = dailyForecast;
    }
}
