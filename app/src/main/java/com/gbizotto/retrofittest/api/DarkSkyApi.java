package com.gbizotto.retrofittest.api;

import com.gbizotto.retrofittest.model.Forecast;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by gabrielabizotto on 11/11/16.
 */

public interface DarkSkyApi {

    @GET("forecast/{apiKey}/{latitude_longitude}")
    Call<Forecast> getForecast(@Path("apiKey") String apiKey, @Path("latitude_longitude") String latitudeLongitude);
}
