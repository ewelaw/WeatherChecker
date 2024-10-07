package com.ewela.feature.searchlocation.domain.model

import com.google.android.libraries.places.api.model.AutocompletePrediction

data class Location(
    val name: String,
    val id: String,
    val latitude: Double? = null,
    val longitude: Double? = null
)

fun List<AutocompletePrediction>.toLocations() = map {
    Location(it.getFullText(null).toString(), it.placeId)
}