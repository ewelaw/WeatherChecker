package com.ewela.feature.searchlocation.domain.usecase

import com.ewela.feature.searchlocation.domain.model.Location
import com.ewela.feature.searchlocation.domain.model.toLocations
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest
import com.google.android.libraries.places.api.net.PlacesClient

class GetLocationSuggestionUseCase(private val placesClient: PlacesClient) {

    private val predictionTypesFilter = listOf("geocode")

    operator fun invoke(
        query: String,
        onSuccess: (List<Location>) -> Unit,
        onFailure: () -> Unit
    ) {
        val request =
            FindAutocompletePredictionsRequest.builder()
                .setTypesFilter(predictionTypesFilter)
                .setQuery(query)
                .build()
        placesClient.findAutocompletePredictions(request).addOnSuccessListener {
            onSuccess(it.autocompletePredictions.toLocations())
        }.addOnFailureListener {
            onFailure()
        }
    }
}