package ru.geekbrains.android3.weather1.presentation.main;

import android.content.Context;

public interface MainView {
    MainView getView();
    void setLongitude(String longitude);
    void setLatitude (String latitude);
    void makeGeoBtnVisible();
}
