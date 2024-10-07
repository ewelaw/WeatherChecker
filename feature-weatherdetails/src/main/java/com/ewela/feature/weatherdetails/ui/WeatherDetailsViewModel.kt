package com.ewela.feature.weatherdetails.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.ewela.data.weather.model.Weather
import com.ewela.data.weather.repository.WeatherRepository
import com.ewela.feature.weatherdetails.navigation.destination.WeatherDetailsRoute
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class WeatherDetailsViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val weatherRepository: WeatherRepository
) : ViewModel() {

    private val args = savedStateHandle.toRoute<WeatherDetailsRoute>()
    private val latitude = args.latitude
    private val longitude = args.longitude
    val locationName = args.locationName
    val weather = MutableStateFlow<Weather?>(null)
    val isLoadingWeatherDetails = MutableStateFlow(false)
    val errorLoadingWeatherDetails = MutableStateFlow(false)

    init {
        initData()
    }

    fun refresh() {
        loadWeatherDetails()
    }

    private fun initData() {
        loadWeatherDetails()
    }

    private fun loadWeatherDetails() {
        isLoadingWeatherDetails.value = true
        viewModelScope.launch {
            try {
                weather.value = weatherRepository.getWeatherDetails(latitude, longitude)
                errorLoadingWeatherDetails.emit(false)
            } catch (exception: Exception) {
                if (exception is CancellationException)
                    throw exception
                else
                    errorLoadingWeatherDetails.emit(true)
            }
        }
        isLoadingWeatherDetails.value = false
    }
}