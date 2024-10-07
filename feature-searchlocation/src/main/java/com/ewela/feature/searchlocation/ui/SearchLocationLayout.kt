package com.ewela.feature.searchlocation.ui

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ewela.feature.searchlocation.R
import com.ewela.feature.searchlocation.domain.model.Location
import org.koin.androidx.compose.koinViewModel

@Composable
fun SearchLocationLayout(
    goToDetails: (Location) -> Unit,
    viewModel: SearchLocationViewModel = koinViewModel<SearchLocationViewModel>()
) {
    val searchQuery = viewModel.locationQuery.collectAsStateWithLifecycle()
    val shouldShowLocationQueryError =
        viewModel.isQueryValid.collectAsStateWithLifecycle()
    val locationSuggestions = viewModel.locationSuggestions.collectAsStateWithLifecycle()
    val errorLoadingLocationSuggestions =
        viewModel.errorLoadingLocationSuggestions.collectAsStateWithLifecycle()
    val isLoadingBlockUi = viewModel.isLoadingLocationDetails.collectAsStateWithLifecycle()
    val isLoadingLocationSuggestions =
        viewModel.isLoadingLocationSuggestions.collectAsStateWithLifecycle()
    val context = LocalContext.current

    SearchLocationContent(
        searchQuery = searchQuery.value,
        onQueryChanged = viewModel::onQueryChanged,
        onClearClick = viewModel::onClearClick,
        shouldShowLocationQueryError = shouldShowLocationQueryError.value.not(),
        locationSuggestions = locationSuggestions.value,
        errorLoadingLocationSuggestions = errorLoadingLocationSuggestions.value,
        onLocationClick = {
            viewModel.onLocationClick(
                location = it,
                onSuccess = { location ->
                    goToDetails(location)
                },
                onFailure = {
                    Toast.makeText(
                        context,
                        R.string.search_location_details_error,
                        Toast.LENGTH_LONG
                    )
                        .show()
                })
        },
        onRefreshSuggestionClick = viewModel::onRefreshSuggestionsClick,
        isLoadingBlockUi = isLoadingBlockUi.value,
        isLoadingLocationSuggestions = isLoadingLocationSuggestions.value
    )
}