package com.sbellanger.weather.data

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.observer.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class WeatherRequest {

    companion object {
        private const val APP_ID_QUERY_KEY = "appid"
        private const val CITY_QUERY_KEY = "q"
        private const val UNITS_QUERY_KEY = "units"
        private const val UNITS_PARAMETER = "metric"
        private const val LANGUAGE_QUERY_KEY = "lang"
        private const val LANGUAGE_PARAMETER = "fr"
    }

    ///////////////////////////////////////////////////////////////////////////
    // DATA
    ///////////////////////////////////////////////////////////////////////////

    private val apiKey = "3e1c6012a7e64deda2e954b067a093a5"
    private val baseUrl = "http://api.openweathermap.org/data/2.5/weather" //https not supported with CIO engine

    private val httpClient = HttpClient(CIO) {
        expectSuccess = false
        ResponseObserver { response ->
            println("HTTP status: ${response.status.value}")
            println(response.receive<String>())
        }
        install(JsonFeature) {
            serializer = KotlinxSerializer(kotlinx.serialization.json.Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
    }

    suspend fun execute(city: String): Response {
        return withContext(Dispatchers.Main) {
            try {
                val response =
                    httpClient.get<HttpResponse>(baseUrl) {
                        parameter(CITY_QUERY_KEY, city)
                        parameter(APP_ID_QUERY_KEY, apiKey)
                        parameter(UNITS_QUERY_KEY, UNITS_PARAMETER)
                        parameter(LANGUAGE_QUERY_KEY, LANGUAGE_PARAMETER)
                    }

                if (response.status == HttpStatusCode.OK) {
                    Response.Success<RawWeather>(response.receive())
                } else {
                    Response.Error("[${response.status.value}] ${response.status.description}")
                }
            } finally {
                httpClient.close()
            }
        }
    }
}
