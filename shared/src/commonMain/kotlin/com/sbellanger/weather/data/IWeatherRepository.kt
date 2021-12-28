package com.sbellanger.weather.data

interface IWeatherRepository {
    suspend fun getWeather(city: String): Response
}
