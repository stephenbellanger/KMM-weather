package com.sbellanger.weather.data.client

import io.ktor.client.*

expect class ApiService() {
    fun build(): HttpClient
}
