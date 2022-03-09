package com.sbellanger.weather.android

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.lifecycle.ViewModelProvider
import com.sbellanger.weather.android.presentation.WeatherScreen

@ExperimentalAnimationApi
class WeatherActivity : AppCompatActivity() {

    ///////////////////////////////////////////////////////////////////////////
    // DATA
    ///////////////////////////////////////////////////////////////////////////

    private lateinit var viewModel: IWeatherContract.ViewModel

    ///////////////////////////////////////////////////////////////////////////
    // LIFECYCLE
    ///////////////////////////////////////////////////////////////////////////

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent { WeatherScreen(viewState = viewModel.viewState.value) }

        viewModel = ViewModelProvider(this)[WeatherViewModel::class.java]

        viewModel.getWeather("rennes")
    }
}
