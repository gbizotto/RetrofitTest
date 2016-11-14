package com.gbizotto.retrofittest.viewModel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.gbizotto.retrofittest.AccessActivityInterface;
import com.gbizotto.retrofittest.model.Forecast;

/**
 * Created by gabrielabizotto on 14/11/16.
 */

public class ForecastViewModel extends BaseObservable{

    private String summary;
    private Integer precipitationProbability;

    AccessActivityInterface mActivity;

    public ForecastViewModel(Forecast forecast, AccessActivityInterface activityInterface) {
        summary = forecast.getCurrently().getSummary();
        precipitationProbability = forecast.getCurrently().getPrecipProbability();

        mActivity = activityInterface;
    }

    @Bindable
    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    @Bindable
    public Integer getPrecipitationProbability() {
        return precipitationProbability;
    }

    public void setPrecipitationProbability(Integer precipitationProbability) {
        this.precipitationProbability = precipitationProbability;
    }

    public String getFormattedPrecipitationProbability(){
        return mActivity.formatPrecipitaion(precipitationProbability);
    }
}
