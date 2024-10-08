package com.ewela.data.weather.api

import com.ewela.core.network.model.WeatherApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

internal interface WeatherApi {

    @GET("/data/2.5/weather")
    suspend fun getCurrentWeather(
        @Query("lat") latitude: String,
        @Query("lon") longitude: String
    ): WeatherApiResponse
}