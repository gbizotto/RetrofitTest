package com.gbizotto.retrofittest.model

class Forecast {

    var latitude: Double = 0.toDouble()
    var longitude: Double = 0.toDouble()
    var timezone: String? = null
    var currently: Currently? = null
    var daily: Daily? = null
}
