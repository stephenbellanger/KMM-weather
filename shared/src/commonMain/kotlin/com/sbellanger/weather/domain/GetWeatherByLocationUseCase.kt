package com.sbellanger.weather.domain

import com.sbellanger.weather.data.IWeatherRepository
import com.sbellanger.weather.data.RawWeather
import com.sbellanger.weather.data.Response

class GetWeatherByLocationUseCase(
    private val repository: IWeatherRepository
) {

    ///////////////////////////////////////////////////////////////////////////
    // PUBLIC API
    ///////////////////////////////////////////////////////////////////////////

    suspend fun execute(city: String): WeatherEntity? {
        val response = repository.getWeather(city)
        return if (response is Response.Success<*>) {
            val data = response.data as RawWeather
            WeatherEntity(
                temperature = StringBuilder()
                    .append(data.temperature.temp.toInt())
                    .append("°")
                    .toString(),
                description = data.weather.first().description,
                city = data.city,
                date = GetFormattedTimeUseCase().execute(),
                pressure = StringBuilder()
                    .append(data.temperature.pressure)
                    .append("hPa")
                    .toString(),
                humidity = StringBuilder()
                    .append(data.temperature.humidity)
                    .append("%")
                    .toString(),
                wind = StringBuilder()
                    .append(data.wind.speed)
                    .append("m/s")
                    .toString()
            )
        } else null
    }
}
