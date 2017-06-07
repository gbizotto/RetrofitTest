package com.gbizotto.retrofittest.model

import java.io.Serializable

class Datum : Serializable {

    var time: Long = 0
    var summary: String? = null
    var temperatureMin: Double = 0.toDouble()
    var temperatureMax: Double = 0.toDouble()
}
