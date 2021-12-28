package com.sbellanger.weather.data

class WeatherRepository : IWeatherRepository {
    override suspend fun getWeather(city: String): Response {
        return WeatherRequest().execute(city)
    }
}
