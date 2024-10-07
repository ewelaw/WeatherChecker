package com.ewela.feature.weatherdetails.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.ewela.data.weather.model.Weather
import com.ewela.feature.weatherdetails.R

@Composable
internal fun WeatherDetailsContent(
    name: String,
    weather: Weather,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.verticalScroll(rememberScrollState())) {
        Column(Modifier.fillMaxWidth()) {
            Image(
                painter = painterResource(id = weather.getImage()),
                contentDescription = "Weather icon",
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .size(200.dp)
                    .padding(bottom = 12.dp)
            )
            Text(
                text = name,
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier
                    .padding(bottom = 12.dp)
                    .align(Alignment.CenterHorizontally),
                textAlign = TextAlign.Center
            )
            Text(
                text = weather.description.joinToString(", "),
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier
                    .padding(bottom = 12.dp)
                    .align(Alignment.CenterHorizontally),
                textAlign = TextAlign.Center
            )
        }
        Text(
            text = stringResource(id = R.string.weather_details_temperature, weather.temperature),
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 12.dp),
            color = weather.temperature.getTemperatureColor()
        )
        Text(
            text = stringResource(
                id = R.string.weather_details_temperature_feels_like,
                weather.temperatureFeelsLike
            ),
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 12.dp),
            color = weather.temperature.getTemperatureColor()
        )
        Text(
            text = stringResource(R.string.weather_details_clouds, weather.clouds),
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 12.dp)
        )
        Text(
            text = stringResource(id = R.string.weather_details_humidity, weather.humidity),
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 12.dp)
        )
        val chanceOfRainText = if (weather.chanceOrRain != null) stringResource(
            id = R.string.weather_details_chance_of_rain,
            weather.chanceOrRain ?: 0
        ) else stringResource(id = R.string.weather_details_no_chance_of_rain)
        Text(
            text = chanceOfRainText,
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 12.dp)
        )
    }
}

private fun Weather.getImage() =
    when {
        clouds > 80 -> R.drawable.clouds
        clouds > 40 -> R.drawable.sun_clouds
        else -> R.drawable.sun
    }

private fun Int.getTemperatureColor() = when {
    this < 10 -> Color.Blue
    this in 10..20 -> Color.Black
    else -> Color.Red
}

@Preview
@Composable
private fun WeatherDetailsContentPreview(
    @PreviewParameter(WeatherDetailsPreviewProvider::class) weather: Weather
) {
    WeatherDetailsContent(name = "Warszawa", weather = weather)
}