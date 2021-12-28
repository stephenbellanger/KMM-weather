package com.sbellanger.weather.data

sealed class Response {
    data class Success<out T : Any>(val data: T) : Response()
    data class Error(val errorMessage: String) : Response()
}
