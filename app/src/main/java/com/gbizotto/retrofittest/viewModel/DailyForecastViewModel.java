package com.gbizotto.retrofittest.viewModel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.text.format.DateFormat;

import com.gbizotto.retrofittest.R;
import com.gbizotto.retrofittest.model.Datum;

import java.util.Date;

/**
 * Created by gabrielabizotto on 14/11/16.
 */

public class DailyForecastViewModel extends BaseObservable {

    private String summary;
    private Double minTemperature;
    private Double maxTemperature;
    private long date;
    private Context mContext;

    public DailyForecastViewModel(Datum datum, Context context) {
        summary = datum.getSummary();
        minTemperature = datum.getTemperatureMin();
        maxTemperature = datum.getTemperatureMax();
        date = datum.getTime();
        mContext = context;
    }

    @Bindable
    public long getDate() {
        return date;
    }

    public void setDate(long date) {
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
        return mContext.getString(R.string.temperature, Double.toString(maxTemperature));
    }
    public String getFormattedMinTemperature(){
        return mContext.getString(R.string.temperature, Double.toString(minTemperature));
    }

    public String getFormattedDate(){
        long dateTime = date * 1000;
        return DateFormat.getDateFormat(mContext).format(new Date(dateTime));
    }


    /*
    mCurrentlySummary.setText(forecast.getCurrently().getSummary());
//                mCurrentlyPrecipProbability.setText(getString(R.string.precipitation_probability, Integer.toString(forecast.getCurrently().getPrecipProbability())));
//
//                mDailyForecastAdapter = new DailyForecastAdapter(mContext, R.layout.daily_row,forecast.getDaily().getData());
//                mLstDailyForecast.setAdapter(mDailyForecastAdapter);

     */

}
