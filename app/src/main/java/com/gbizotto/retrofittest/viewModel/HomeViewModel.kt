package com.gbizotto.retrofittest.viewModel

import android.databinding.BaseObservable
import android.databinding.Bindable
import com.gbizotto.retrofittest.ForecastCallback
import com.gbizotto.retrofittest.model.Forecast

class HomeViewModel(forecast: Forecast, callback: ForecastCallback) : BaseObservable() {

    @get:Bindable
    var summary: String? = null
    @get:Bindable
    var precipitationProbability: Double? = 0.0
    val mCallback: ForecastCallback

    init {
        summary = forecast.currently?.summary
        precipitationProbability = forecast.currently?.precipProbability
        mCallback = callback
    }

    val formattedPrecipitationProbability: String
        get() {
            return mCallback.formatPrecipitation(precipitationProbability!!)
        }
}