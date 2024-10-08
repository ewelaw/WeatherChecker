package com.ewela.data.weather.repository

import com.ewela.data.weather.providers.WeatherApiProvider
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.unmockkAll
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class WeatherRepositoryImplTest {

    private val weatherApi = mockk<WeatherApiProvider>(relaxed = true)
    private val repository = WeatherRepositoryImpl(weatherApi)

    @Before
    fun setUp() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
    }

    @After
    fun cleanUp() {
        unmockkAll()
    }

    @Test
    fun `Given latitude and longitude, when getWeatherDetails, then should call weatherApi with correct parameters`() =
        runTest {
            //given
            val latitude = "53.123"
            val longitude = "23.456"
            //when
            repository.getWeatherDetails(latitude = latitude, longitude = longitude)
            //then
            coVerify {
                weatherApi.weatherApi.getCurrentWeather(latitude = latitude, longitude = longitude)
            }
        }
}