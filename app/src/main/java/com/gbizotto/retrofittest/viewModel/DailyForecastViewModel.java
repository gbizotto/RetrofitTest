package com.gbizotto.retrofittest.viewModel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.gbizotto.retrofittest.model.Datum;

/**
 * Created by gabrielabizotto on 14/11/16.
 */

public class DailyForecastViewModel extends BaseObservable {

    private String summary;
    private Double minTemperature;
    private Double maxTemperature;
    private String date;

    public DailyForecastViewModel(Datum datum) {
        summary = datum.getSummary();
        minTemperature = datum.getTemperatureMin();
        maxTemperature = datum.getTemperatureMax();
        date = String.valueOf(datum.getTime());
    }

    @Bindable
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Bindable
    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    @Bindable
    public Double getMinTemperature() {
        return minTemperature;
    }

    public void setMinTemperature(Double minTemperature) {
        this.minTemperature = minTemperature;
    }

    @Bindable
    public Double getMaxTemperature() {
        return maxTemperature;
    }

    public void setMaxTemperature(Double maxTemperature) {
        this.maxTemperature = maxTemperature;
    }

    public String getFormattedMaxTemperature(){
        return String.valueOf(maxTemperature);
    }
    public String getFormattedMinTemperature(){
        return String.valueOf(minTemperature);
    }

}
