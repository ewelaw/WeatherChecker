package com.ewela.feature.weatherdetails.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ewela.common.ui.ErrorContent
import com.ewela.common.ui.LoaderContent
import com.ewela.feature.weatherdetails.R
import org.koin.androidx.compose.koinViewModel

@Composable
fun WeatherDetailsLayout(
    viewModel: WeatherDetailsViewModel = koinViewModel()
) {
    val isLoading = viewModel.isLoadingWeatherDetails.collectAsStateWithLifecycle()
    val errorLoading = viewModel.errorLoadingWeatherDetails.collectAsStateWithLifecycle()
    val weather = viewModel.weather.collectAsStateWithLifecycle()
    Column(modifier = Modifier.padding(24.dp)) {
        when {
            isLoading.value -> LoaderContent(Modifier.fillMaxSize())
            errorLoading.value -> ErrorContent(
                message = stringResource(id = R.string.weather_details_error_loading),
                onRefresh = viewModel::refresh,
                modifier = Modifier.fillMaxSize()
            )

            else ->
                weather.value?.let {
                    WeatherDetailsContent(weather = it, name = viewModel.locationName)
                }
        }
    }
}