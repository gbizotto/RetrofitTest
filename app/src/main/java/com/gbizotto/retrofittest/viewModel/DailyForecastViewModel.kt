package com.gbizotto.retrofittest.viewModel

import android.content.Context
import android.databinding.BaseObservable
import android.databinding.Bindable
import android.text.format.DateFormat

import com.gbizotto.retrofittest.R
import com.gbizotto.retrofittest.model.Datum

import java.util.Date

class DailyForecastViewModel(datum: Datum, private val mContext: Context) : BaseObservable() {

    @get:Bindable
    var summary: String? = null
    @get:Bindable
    var minTemperature: Double? = null
    @get:Bindable
    var maxTemperature: Double? = null
    @get:Bindable
    var date: Long = 0

    init {
        summary = datum.summary
        minTemperature = datum.temperatureMin
        maxTemperature = datum.temperatureMax
        date = datum.time
    }

    val formattedMaxTemperature: String
        get() = mContext.getString(R.string.temperature, java.lang.Double.toString(maxTemperature!!))
    val formattedMinTemperature: String
        get() = mContext.getString(R.string.temperature, java.lang.Double.toString(minTemperature!!))

    val formattedDate: String
        get() {
            val dateTime = date * 1000
            return DateFormat.getDateFormat(mContext).format(Date(dateTime))
        }
}
