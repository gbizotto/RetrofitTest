package com.gbizotto.retrofittest.model

class Currently {

    var time: Int = 0
    var summary: String? = null
    var icon: String? = null
    var nearestStormDistance: Int = 0
    var precipIntensity: Double = 0.toDouble()
    var precipIntensityError: Double = 0.toDouble()
    var precipProbability: Double = 0.toDouble()
    var precipType: String? = null
    var temperature: Double = 0.toDouble()
    var apparentTemperature: Double = 0.toDouble()
    var dewPoint: Double = 0.toDouble()
    var humidity: Double = 0.toDouble()
    var windSpeed: Double = 0.toDouble()
    var windBearing: Int = 0
    var visibility: Double = 0.toDouble()
    var cloudCover: Double = 0.toDouble()
    var pressure: Double = 0.toDouble()
    var ozone: Double = 0.toDouble()
}
