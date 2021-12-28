package com.sbellanger.weather

import dev.icerock.moko.mvvm.livedata.LiveData

interface IWeatherViewModel {
    val viewState: LiveData<ViewState>

    fun getWeather(city: String)
}
