package com.sbellanger.weather.android

import com.sbellanger.weather.IWeatherViewModel
import com.sbellanger.weather.domain.WeatherEntity

interface IMainContract {
    interface ViewModel : IWeatherViewModel

    interface ViewCapability {
        fun showLoader(shouldShow: Boolean)
        fun showError(errorMessage: String)
        fun showWeather(weather: WeatherEntity)
    }
}
