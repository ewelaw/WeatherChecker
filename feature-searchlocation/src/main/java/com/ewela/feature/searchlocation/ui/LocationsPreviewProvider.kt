package com.ewela.feature.searchlocation.ui

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.ewela.feature.searchlocation.domain.model.Location

class LocationsPreviewProvider : PreviewParameterProvider<List<Location>> {

    override val values: Sequence<List<Location>>
        get() = sequenceOf(
            listOf(
                Location("Lublin, Poland", "1"),
                Location("Warszawa, Poland", "2"),
                Location("Poznań, Poland", "3"),
                Location("Łódź, Poland", "4")
            ),
            listOf(
                Location("Lublin, Poland", "1"),
                Location("Warszawa, Poland", "2")
            ),
            listOf(),
        )
}