package com.gbizotto.retrofittest.viewModel

import android.content.Context
import android.databinding.BaseObservable
import com.gbizotto.retrofittest.R
import com.gbizotto.retrofittest.model.Datum

class DetailViewModel(datum: Datum?, context: Context) : BaseObservable() {

    var minimunTemperature: Double? = datum?.temperatureMin
    var maximunTemperature: Double? = datum?.temperatureMax
    private val context: Context = context!!

    val rangeCelsius: String
        get() {
            return context.getString(R.string.temperature_range_celsius, convertToCelsius(minimunTemperature)?.toString(), convertToCelsius(maximunTemperature)?.toString())
        }

    val rangeFarenheit: String
        get() {
            return context.getString(R.string.temperature_range_farenheit, minimunTemperature?.toString(), maximunTemperature?.toString())
        }

    private fun convertToCelsius(temperature: Double?): Int? {
        return ((temperature?.minus(32))?.times(5))?.div(9)?.toInt()
    }
}