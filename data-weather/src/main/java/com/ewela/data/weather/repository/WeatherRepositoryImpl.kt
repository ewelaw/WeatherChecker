package com.ewela.data.weather.repository

import com.ewela.data.weather.model.toWeather
import com.ewela.data.weather.providers.WeatherApiProvider

internal class WeatherRepositoryImpl(private val weatherApi: WeatherApiProvider) :
    WeatherRepository {

    override suspend fun getWeatherDetails(latitude: String, longitude: String) =
        weatherApi.weatherApi.getCurrentWeather(latitude = latitude, longitude = longitude).toWeather()
}
