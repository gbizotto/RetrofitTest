package com.gbizotto.retrofittest.model

class Datum {

    var time: Long = 0
    var summary: String? = null
    var icon: String? = null
    var sunriseTime: Int = 0
    var sunsetTime: Int = 0
    var moonPhase: Double = 0.toDouble()
    var precipIntensity: Double = 0.toDouble()
    var precipIntensityMax: Double = 0.toDouble()
    var precipIntensityMaxTime: Int = 0
    var precipProbability: Double = 0.toDouble()
    var precipType: String? = null
    var temperatureMin: Double = 0.toDouble()
    var temperatureMinTime: Int = 0
    var temperatureMax: Double = 0.toDouble()
    var temperatureMaxTime: Int = 0
    var apparentTemperatureMin: Double = 0.toDouble()
    var apparentTemperatureMinTime: Int = 0
    var apparentTemperatureMax: Double = 0.toDouble()
    var apparentTemperatureMaxTime: Int = 0
    var dewPoint: Double = 0.toDouble()
    var humidity: Double = 0.toDouble()
    var windSpeed: Double = 0.toDouble()
    var windBearing: Int = 0
    var visibility: Double = 0.toDouble()
    var cloudCover: Double = 0.toDouble()
    var pressure: Double = 0.toDouble()
    var ozone: Double = 0.toDouble()
}
