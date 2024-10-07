package com.ewela.feature.searchlocation.ui

import com.ewela.feature.searchlocation.domain.usecase.GetLocationDetailsUseCase
import com.ewela.feature.searchlocation.domain.usecase.GetLocationSuggestionUseCase
import com.ewela.feature.searchlocation.domain.usecase.IsSearchQueryValidUseCase
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.verify
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import junitparams.JUnitParamsRunner
import junitparams.Parameters
import junitparams.naming.TestCaseName
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(JUnitParamsRunner::class)
class SearchLocationViewModelTest {

    private val isSearchQueryValidUseCase: IsSearchQueryValidUseCase = mockk(relaxed = true)
    private val getLocationSuggestionUseCase: GetLocationSuggestionUseCase = mockk(relaxed = true)
    private val getLocationDetailsUseCase: GetLocationDetailsUseCase = mockk(relaxed = true)
    private lateinit var viewModel: SearchLocationViewModel

    @Before
    fun setUp() {
        viewModel = SearchLocationViewModel(
            isSearchQueryValidUseCase,
            getLocationSuggestionUseCase,
            getLocationDetailsUseCase
        )
    }

    @After
    fun cleanUp() {
        clearAllMocks()
    }

    @Test
    fun `Given query, when onQueryChanged, then locationQuery should change`() {
        //given
        val inputQuery = "War"
        //when
        viewModel.onQueryChanged(inputQuery)
        //then
        assertEquals(inputQuery, viewModel.locationQuery.value)
    }

    @Test
    @Parameters(value = ["true", "false"])
    @TestCaseName("Given query, when onQueryChanged, then isSearchQueryValid should equal = {0}")
    fun `Given query, when onQueryChanged, then isSearchQueryValid should update`(isSearchQueryValid: Boolean) {
        //given
        val query = "Warszawa1"
        coEvery { isSearchQueryValidUseCase(query) } returns isSearchQueryValid
        //when
        viewModel.onQueryChanged(query)
        //then
        assertEquals(isSearchQueryValid, viewModel.isQueryValid.value)
    }

    @Test
    fun `Given filled query, when onClear, then should clear locationQuery`() {
        //given
        viewModel.onQueryChanged("Warszawa")
        //when
        viewModel.onClearClick()
        //then
        assertEquals("", viewModel.locationQuery.value)
    }

    @Test
    fun `Given filled query, when onClear, then should clear locationSuggestions`() {
        //given
        viewModel.onQueryChanged("Warszawa")
        //when
        viewModel.onClearClick()
        //then
        assert(viewModel.locationSuggestions.value.isEmpty())
    }

    @Test
    fun `Given invalid query, when onClear, then should clear isQueryValid`() {
        //given
        val query = "Warszawa1"
        coEvery { isSearchQueryValidUseCase(query) } returns false
        viewModel.onQueryChanged(query)
        //when
        viewModel.onClearClick()
        //then
        assertTrue(viewModel.isQueryValid.value)
    }

    @Test
    fun `When onQueryChanged, then should validate it`(){
        //given
        val query = "War"
        //when
        viewModel.onQueryChanged(query)
        //then
        verify(exactly = 1) {
            isSearchQueryValidUseCase(query)
        }
    }
}