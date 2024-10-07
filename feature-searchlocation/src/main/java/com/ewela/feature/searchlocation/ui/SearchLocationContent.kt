package com.ewela.feature.searchlocation.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.ewela.common.ui.LoaderContent
import com.ewela.feature.searchlocation.R
import com.ewela.feature.searchlocation.domain.model.Location
import com.ewela.feature.searchlocation.ui.locationsuggestions.LocationSuggestionsContent

@Composable
fun SearchLocationContent(
    isLoadingBlockUi: Boolean,
    isLoadingLocationSuggestions: Boolean,
    searchQuery: String,
    shouldShowLocationQueryError: Boolean,
    errorLoadingLocationSuggestions: Boolean,
    locationSuggestions: List<Location>,
    onQueryChanged: (String) -> Unit,
    onClearClick: () -> Unit,
    onRefreshSuggestionClick: () -> Unit,
    onLocationClick: (Location) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        if (isLoadingBlockUi) {
            LoaderContent()
        } else {
            Column {
                QueryTextField(
                    query = searchQuery,
                    shouldShowLocationQueryError = shouldShowLocationQueryError,
                    onQueryChanged = onQueryChanged,
                    onClearClick = onClearClick
                )
                LocationSuggestionsContent(
                    onLocationClick = onLocationClick,
                    locationSuggestions = locationSuggestions,
                    errorLoadingLocationSuggestions = errorLoadingLocationSuggestions,
                    onRefreshSuggestionClick = onRefreshSuggestionClick,
                    isLoadingLocationSuggestions = isLoadingLocationSuggestions
                )
            }
        }
    }
}

@Composable
private fun QueryTextField(
    query: String,
    shouldShowLocationQueryError: Boolean,
    onQueryChanged: (String) -> Unit,
    onClearClick: () -> Unit
) {
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth(),
        value = query,
        onValueChange = onQueryChanged,
        singleLine = true,
        label = {
            Text(stringResource(R.string.search_location_label))
        },
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_search),
                contentDescription = "Search icon"
            )
        },
        trailingIcon = {
            if (query.isNotEmpty()) {
                Icon(
                    modifier = Modifier.clickable(
                        enabled = true,
                        onClick = onClearClick
                    ),
                    painter = painterResource(
                        id = R.drawable.ic_clear
                    ),
                    contentDescription = "Clear icon"
                )
            }
        },
        supportingText = {
            if (shouldShowLocationQueryError) {
                Text(
                    text = stringResource(id = R.string.search_location_query_error),
                    color = MaterialTheme.colorScheme.error
                )
            }
        },
    )
}

@Preview
@Composable
private fun SearchLocationContentPreview(
    @PreviewParameter(LocationsPreviewProvider::class) locations: List<Location>
) {
    SearchLocationContent(
        shouldShowLocationQueryError = false,
        searchQuery = "",
        onQueryChanged = {},
        onClearClick = {},
        onLocationClick = {},
        locationSuggestions = locations,
        errorLoadingLocationSuggestions = false,
        onRefreshSuggestionClick = {},
        isLoadingBlockUi = false,
        isLoadingLocationSuggestions = false
    )
}

@Preview
@Composable
private fun SearchLocationContentPreview() {
    SearchLocationContent(
        shouldShowLocationQueryError = true,
        searchQuery = "a2",
        onQueryChanged = {},
        onClearClick = {},
        onLocationClick = {},
        locationSuggestions = listOf(),
        errorLoadingLocationSuggestions = false,
        onRefreshSuggestionClick = {},
        isLoadingBlockUi = true,
        isLoadingLocationSuggestions = false
    )
}

@Preview
@Composable
private fun SearchLocationContentLoadingLocationsPreview() {
    SearchLocationContent(
        shouldShowLocationQueryError = true,
        searchQuery = "a2",
        onQueryChanged = {},
        onClearClick = {},
        onLocationClick = {},
        locationSuggestions = listOf(),
        errorLoadingLocationSuggestions = false,
        onRefreshSuggestionClick = {},
        isLoadingBlockUi = false,
        isLoadingLocationSuggestions = true
    )
}