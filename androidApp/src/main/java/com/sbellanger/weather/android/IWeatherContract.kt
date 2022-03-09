package com.sbellanger.weather.android

import androidx.compose.runtime.State
import com.sbellanger.weather.ViewState

interface IWeatherContract {
    interface ViewModel {
        val viewState: State<ViewState>

        fun getWeather(city: String)
    }
}
