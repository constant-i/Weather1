package ru.geekbrains.android3.weather1.presentation.details;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import ru.geekbrains.android3.weather1.domain.model.ModelForecast;
import ru.geekbrains.android3.weather1.R;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.ViewHolder> {
    private List<ModelForecast> modelForecastList = new ArrayList<>();

    void setModelForecastList(List<ModelForecast> mForecastList) {
        this.modelForecastList.clear();
        this.modelForecastList.addAll(mForecastList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_weather, parent, false);
        return new ViewHolder(view, parent);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindForecast(modelForecastList.get(position));
    }

    @Override
    public int getItemCount() {
        return modelForecastList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView date;
        TextView description;
        TextView maxTemp;
        TextView minTemp;
        ImageView icon;

        ViewGroup parent;

        ViewHolder(View view, ViewGroup parent) {
            super(view);
            date = view.findViewById(R.id.date);
            description = view.findViewById(R.id.description);
            maxTemp = view.findViewById(R.id.maxTemp);
            minTemp = view.findViewById(R.id.minTemp);
            icon = view.findViewById(R.id.icon);
//            itemView.setOnClickListener(item -> {
//                item.getId();
//                // сюда добавить слушателей нажатий
//            });
            this.parent = parent;
        }

        void bindForecast(ModelForecast forecast) {
            date.setText(forecast.getDate());
            description.setText(forecast.getDescription());
            maxTemp.setText(forecast.getHigh().toString() + "°C");
            minTemp.setText(forecast.getLow().toString() + "°C");
            Picasso.with(itemView.getContext())
                    .load(forecast.getIconUrl())
                    .into(icon);
        }
    }
}
