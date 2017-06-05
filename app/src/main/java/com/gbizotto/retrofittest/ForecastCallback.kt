package com.gbizotto.retrofittest

import java.util.Date

interface ForecastCallback {
    fun formatPrecipitation(value: Double): String
    fun formatDate(date: Date): String
}