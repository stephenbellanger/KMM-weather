package com.sbellanger.weather.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RawWeather(
    @SerialName("weather") val weather: List<RawWeatherData>,
    @SerialName("main") val temperature: RawMainData,
    @SerialName("wind") val wind: RawWindData,
    @SerialName("name") val city: String
)

@Serializable
data class RawWeatherData(
    @SerialName("main") val main: String,
    @SerialName("description") val description: String,
)

@Serializable
data class RawMainData(
    @SerialName("temp") val temp: Double,
    @SerialName("pressure") val pressure: Int,
    @SerialName("humidity") val humidity: Int,
)

@Serializable
data class RawWindData(
    @SerialName("speed") val speed: Double
)
