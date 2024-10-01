package com.ewela.data.weather.repository

import com.ewela.data.weather.model.Weather

interface WeatherRepository {

    suspend fun getWeatherDetails(latitude: String, longitude: String): Weather
}