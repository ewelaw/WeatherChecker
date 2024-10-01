package com.ewela.data.weather.model

import com.ewela.core.network.model.WeatherApiResponse
import kotlin.math.roundToInt

data class Weather(
    val temperature: Int,
    val temperatureFeelsLike: Int,
    val chanceOrRain: Double?,
    val clouds: Int,
    val humidity: Int,
    val description: List<String>
)

internal fun WeatherApiResponse.toWeather() = Weather(
    temperature = main.temp.roundToInt(),
    temperatureFeelsLike = main.feels_like.roundToInt(),
    description = weather.map { it.description },
    chanceOrRain = rain?.`1h`,
    clouds = clouds.all,
    humidity = main.humidity
)

