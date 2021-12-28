package com.sbellanger.weather

import com.sbellanger.weather.domain.WeatherEntity

sealed class ViewState {
    object Loading : ViewState()
    data class HasResult(val weather: WeatherEntity) : ViewState()
    data class Error(val errorMessage: String) : ViewState()
}
