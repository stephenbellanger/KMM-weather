package com.sbellanger.weather.domain

import com.sbellanger.weather.data.IWeatherRepository
import com.sbellanger.weather.data.RawWeather
import com.sbellanger.weather.data.Response
import com.sbellanger.weather.data.WeatherRepository

class GetWeatherByLocationUseCase {

    suspend fun execute(city: String): WeatherEntity? {
        val response = (WeatherRepository() as IWeatherRepository).getWeather(city)
        return if (response is Response.Success<*>) {
            val data = response.data as RawWeather
            WeatherEntity(
                data.temperature.temp,
                data.weather.first().description
            )
        } else null
    }
}
