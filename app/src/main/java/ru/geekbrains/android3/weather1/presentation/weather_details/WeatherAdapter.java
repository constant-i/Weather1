package ru.geekbrains.android3.weather1.presentation.weather_details;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

import ru.geekbrains.android3.weather1.R;
import ru.geekbrains.android3.weather1.data.model.List;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.ViewHolder> {
    private java.util.List<List> WeatherList = new ArrayList<>();

    void setWeatherList(java.util.List<List> wList) {
        WeatherList.clear();
        WeatherList.addAll(wList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_weather_common, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int pos) {
        List oneElement = WeatherList.get(pos);

        float celsiusTemp = oneElement.getMain().getTemp();
        celsiusTemp = (float) new BigDecimal(celsiusTemp).setScale(1, RoundingMode.UP).doubleValue();
        holder.textTemper.setText((celsiusTemp + holder.itemView.getContext().getResources().getString(R.string.celsius_degree)));

        holder.textMainWeather.setText(oneElement.getWeather().get(0).getMain() + ": "
                + oneElement.getWeather().get(0).getDescription());
        holder.textTime.setText(oneElement.getDtTxt());
    }

    @Override
    public int getItemCount() {
        return WeatherList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView textTime;
        TextView textTemper;
        TextView textMainWeather;
        CardView ItemCommonWeather;

        ViewHolder(View v) {
            super(v);
            textTime = v.findViewById(R.id.item_text_time);
            textTemper = v.findViewById(R.id.item_text_temper);
            textMainWeather = v.findViewById(R.id.item_text_main_weather);
            ItemCommonWeather = v.findViewById(R.id.item_common_weather);
            ItemCommonWeather.setOnClickListener(item -> {
                item.getId();
                // сюда добавить слушателей нажатий
            });
        }
    }
}
