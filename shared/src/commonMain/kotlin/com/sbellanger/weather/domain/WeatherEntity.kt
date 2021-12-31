package com.sbellanger.weather.domain

data class WeatherEntity(
    val temperature: String,
    val description: String,
    val pressure: String,
    val humidity: String,
    val wind: String,
    val city: String,
    val date: String
)
