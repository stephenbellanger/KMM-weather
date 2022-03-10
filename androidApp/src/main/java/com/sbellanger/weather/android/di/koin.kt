package com.sbellanger.weather.android.di

import com.sbellanger.weather.android.IWeatherContract
import com.sbellanger.weather.android.WeatherViewModel
import org.koin.dsl.module

val viewModelModule = module {
    single<IWeatherContract.ViewModel> { WeatherViewModel() }
}
