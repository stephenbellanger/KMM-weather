package com.sbellanger.weather.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RawWeather(
    @SerialName("weather") val weather: List<RawWeatherData>,
    @SerialName("main") val temperature: RawMainData
)

@Serializable
data class RawWeatherData(
    @SerialName("main") val main: String,
    @SerialName("description") val description: String,
)

@Serializable
data class RawMainData(
    @SerialName("temp") val temp: Double,
)
