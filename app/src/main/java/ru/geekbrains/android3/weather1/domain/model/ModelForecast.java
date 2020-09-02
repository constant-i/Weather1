package ru.geekbrains.android3.weather1.domain.model;

public class ModelForecast {

    private String date;
    private String description;
    private Integer high;
    private Integer low;
    private String iconUrl;

    public ModelForecast(String date, String description, Integer high, Integer low, String iconUrl) {
        this.date = date;
        this.description = description;
        this.high = high;
        this.low = low;
        this.iconUrl = iconUrl;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getHigh() {
        return high;
    }

    public void setHigh(Integer high) {
        this.high = high;
    }

    public Integer getLow() {
        return low;
    }

    public void setLow(Integer low) {
        this.low = low;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }
}
