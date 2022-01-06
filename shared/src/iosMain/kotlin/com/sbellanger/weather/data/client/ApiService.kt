package com.sbellanger.weather.data.client

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.ios.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.observer.*

actual class ApiService {
    actual fun build(): HttpClient {
        return HttpClient(Ios) {
            expectSuccess = false
            ResponseObserver { response ->
                println("HTTP status: ${response.status.value}")
                println(response.receive())
            }
            install(JsonFeature) {
                serializer = KotlinxSerializer(kotlinx.serialization.json.Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                })
            }
        }
    }
}
