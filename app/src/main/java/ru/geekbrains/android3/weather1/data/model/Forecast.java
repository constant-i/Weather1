package ru.geekbrains.android3.weather1.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Forecast {

    @SerializedName("dt")
    @Expose
    private Long dt;

    @SerializedName("temp")
    @Expose
    private Temperature temp;

    @SerializedName("pressure")
    @Expose
    private Float pressure;

    @SerializedName("humidity")
    @Expose
    private Integer humidity;

    @SerializedName("weather")
    @Expose
    private List<Weather> weather;

    @SerializedName("speed")
    @Expose
    private Float speed;

    @SerializedName("deg")
    @Expose
    private Integer deg;

    @SerializedName("clouds")
    @Expose
    private Integer clouds;

    @SerializedName("rain")
    @Expose
    private Float rain;

    public Long getDt() {
        return dt;
    }

    public void setDt(Long dt) {
        this.dt = dt;
    }

    public Temperature getTemp() {
        return temp;
    }

    public void setTemp(Temperature temp) {
        this.temp = temp;
    }

    public Float getPressure() {
        return pressure;
    }

    public void setPressure(Float pressure) {
        this.pressure = pressure;
    }

    public Integer getHumidity() {
        return humidity;
    }

    public void setHumidity(Integer humidity) {
        this.humidity = humidity;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }

    public Float getSpeed() {
        return speed;
    }

    public void setSpeed(Float speed) {
        this.speed = speed;
    }

    public Integer getDeg() {
        return deg;
    }

    public void setDeg(Integer deg) {
        this.deg = deg;
    }

    public Integer getClouds() {
        return clouds;
    }

    public void setClouds(Integer clouds) {
        this.clouds = clouds;
    }

    public Float getRain() {
        return rain;
    }

    public void setRain(Float rain) {
        this.rain = rain;
    }
}
