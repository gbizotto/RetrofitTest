package com.gbizotto.retrofittest.viewModel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.gbizotto.retrofittest.ForecastCallback;
import com.gbizotto.retrofittest.model.Forecast;

public class ForecastViewModel extends BaseObservable{

    private String summary;
    private Double precipitationProbability;

    private final ForecastCallback mCallback;

    public ForecastViewModel(Forecast forecast, ForecastCallback callback) {
        summary = forecast.getCurrently().getSummary();
        precipitationProbability = forecast.getCurrently().getPrecipProbability();

        mCallback = callback;
    }

    @Bindable
    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    @Bindable
    public Double getPrecipitationProbability() {
        return precipitationProbability;
    }

    public void setPrecipitationProbability(Double precipitationProbability) {
        this.precipitationProbability = precipitationProbability;
    }

    public String getFormattedPrecipitationProbability(){
        return mCallback.formatPrecipitation(precipitationProbability);
    }
}
