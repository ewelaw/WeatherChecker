package com.ewela.feature.weatherdetails.ui

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.toRoute
import com.ewela.data.weather.model.Weather
import com.ewela.data.weather.repository.WeatherRepository
import com.ewela.feature.weatherdetails.navigation.destination.WeatherDetailsRoute
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.unmockkAll
import junit.framework.TestCase.assertNotNull
import junit.framework.TestCase.assertNull
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class WeatherDetailsViewModelTest {

    private val weatherRepository = mockk<WeatherRepository>()
    private val savedStateHandle = mockk<SavedStateHandle>(relaxed = true)
    private val weatherDetailsRoute = WeatherDetailsRoute(
        locationName = "Lublin",
        latitude = "51.246452",
        longitude = "22.568445"
    )
    private val weatherDetails = Weather(
        chanceOrRain = 20.0,
        clouds = 82,
        description = listOf("przelotne opady", "możliwe burze"),
        temperature = -1,
        humidity = 10,
        temperatureFeelsLike = -10
    )

    @Before
    fun setUp() {
        mockkStatic(("androidx.navigation.SavedStateHandleKt"))
        coEvery { savedStateHandle.toRoute<WeatherDetailsRoute>() } returns weatherDetailsRoute
        Dispatchers.setMain(UnconfinedTestDispatcher())
    }

    @After
    fun cleanUp() {
        unmockkAll()
    }

    @Test
    fun `Given weatherRepository returning weather, when init viewmodel, then should set weather`() {
        //given
        coEvery {
            weatherRepository.getWeatherDetails(
                latitude = weatherDetailsRoute.latitude,
                longitude = weatherDetailsRoute.longitude
            )
        } returns weatherDetails
        //when
        val viewModel = WeatherDetailsViewModel(savedStateHandle, weatherRepository)
        //then
        assertNotNull(viewModel.weather.value)
    }

    @Test
    fun `Given weatherRepository throwing exception, when init viewmodel, then should set errorLoadingWeatherDetails`() {
        //given
        coEvery {
            weatherRepository.getWeatherDetails(
                latitude = weatherDetailsRoute.latitude,
                longitude = weatherDetailsRoute.longitude
            )
        } throws Exception()
        //when
        val viewModel = WeatherDetailsViewModel(savedStateHandle, weatherRepository)
        //then
        assertTrue(viewModel.errorLoadingWeatherDetails.value)
    }

    @Test
    fun `Given weatherRepository throwing exception, when init viewmodel, then weather should be null`() {
        //given
        coEvery {
            weatherRepository.getWeatherDetails(
                latitude = weatherDetailsRoute.latitude,
                longitude = weatherDetailsRoute.longitude
            )
        } throws Exception()
        //when
        val viewModel = WeatherDetailsViewModel(savedStateHandle, weatherRepository)
        //then
        assertNull(viewModel.weather.value)
    }
}