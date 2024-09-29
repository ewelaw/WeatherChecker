package com.ewela.feature.searchlocation.ui.locationsuggestions

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.ewela.feature.searchlocation.domain.model.Location
import com.ewela.feature.searchlocation.ui.LocationsPreviewProvider

@Composable
fun LocationSuggestionsContent(
    isLoadingLocationSuggestions: Boolean,
    errorLoadingLocationSuggestions: Boolean,
    locationSuggestions: List<Location>,
    onLocationClick: (Location) -> Unit,
    onRefreshSuggestionClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier.fillMaxSize()
    ) {
        when {
            isLoadingLocationSuggestions ->
                CircularProgressIndicator(
                    modifier = Modifier
                        .width(64.dp)
                        .align(Alignment.CenterHorizontally),
                    color = MaterialTheme.colorScheme.secondary,
                    trackColor = MaterialTheme.colorScheme.surfaceVariant,
                )

            errorLoadingLocationSuggestions ->
                LocationSuggestionsErrorContent(onRefreshSuggestionClick, modifier)

            else ->
                locationSuggestions.forEachIndexed { index, prediction ->
                    Column(
                        Modifier
                            .clickable(true, onClick = { onLocationClick(prediction) })
                    ) {
                        Text(
                            text = prediction.name,
                            modifier = Modifier
                                .padding(vertical = 12.dp),
                            fontSize = MaterialTheme.typography.titleMedium.fontSize
                        )
                        HorizontalDivider()
                    }
                }
        }
    }
}

@Preview
@Composable
private fun LocationSuggestionsContentPreview(
    @PreviewParameter(LocationsPreviewProvider::class) locations: List<Location>
) {
    LocationSuggestionsContent(
        locationSuggestions = locations,
        onLocationClick = {},
        errorLoadingLocationSuggestions = false,
        onRefreshSuggestionClick = {},
        isLoadingLocationSuggestions = false
    )
}

@Preview
@Composable
private fun LocationSuggestionsContentPreview() {
    LocationSuggestionsContent(
        locationSuggestions = emptyList(),
        onLocationClick = {},
        errorLoadingLocationSuggestions = false,
        onRefreshSuggestionClick = {},
        isLoadingLocationSuggestions = true
    )
}