package com.ewela.feature.weatherdetails.navigation.destination

import kotlinx.serialization.Serializable

@Serializable
data class WeatherDetailsRoute(val locationName: String, val latitude: String, val longitude: String)