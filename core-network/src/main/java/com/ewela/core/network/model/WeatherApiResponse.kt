package com.ewela.core.network.model

data class WeatherApiResponse(
    val clouds: Clouds,
    val main: Main,
    val rain: Rain?,
    val weather: List<WeatherDescription>,
)