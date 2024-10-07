package com.ewela.feature.weatherdetails.ui

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.ewela.data.weather.model.Weather

class WeatherDetailsPreviewProvider : PreviewParameterProvider<Weather> {

    override val values: Sequence<Weather>
        get() = sequenceOf(
            Weather(
                chanceOrRain = 20.0,
                clouds = 82,
                description = listOf("przelotne opady", "możliwe burze"),
                temperature = -1,
                humidity = 10,
                temperatureFeelsLike = -10
            ),
            Weather(
                chanceOrRain = null,
                clouds = 45,
                description = emptyList(),
                temperature = 12,
                humidity = 6,
                temperatureFeelsLike = 10
            ),
            Weather(
                chanceOrRain = 5.0,
                clouds = 10,
                description = emptyList(),
                temperature = 25,
                humidity = 0,
                temperatureFeelsLike = 22
            )
        )
}