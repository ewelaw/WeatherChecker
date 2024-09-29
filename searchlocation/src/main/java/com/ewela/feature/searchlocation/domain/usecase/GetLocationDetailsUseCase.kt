package com.ewela.feature.searchlocation.domain.usecase

import com.ewela.feature.searchlocation.domain.model.Location
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.net.FetchPlaceRequest
import com.google.android.libraries.places.api.net.PlacesClient

class GetLocationDetailsUseCase(
    private val placesClient: PlacesClient
) {

    operator fun invoke(
        location: Location,
        onSuccess: (Location) -> Unit,
        onFailure: () -> Unit
    ) {
        placesClient.fetchPlace(
            FetchPlaceRequest.newInstance(
                location.id,
                listOf(Place.Field.LAT_LNG)
            )
        ).addOnSuccessListener { response ->
            val updatedLocation = location.copy(
                latitude = response.place.location?.latitude,
                longitude = response.place.location?.longitude
            )
            onSuccess(updatedLocation)
        }.addOnFailureListener {
            onFailure()
        }
    }
}