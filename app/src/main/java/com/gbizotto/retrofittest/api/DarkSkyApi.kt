package com.gbizotto.retrofittest.api

import com.gbizotto.retrofittest.model.Forecast

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface DarkSkyApi {

    @GET("forecast/{apiKey}/{latitude_longitude}")
    fun getForecast(@Path("apiKey") apiKey: String, @Path("latitude_longitude") latitudeLongitude: String): Call<Forecast>
}
