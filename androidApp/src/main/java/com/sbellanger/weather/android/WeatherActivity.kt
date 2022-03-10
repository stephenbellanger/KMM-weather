package com.sbellanger.weather.android

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.ExperimentalAnimationApi
import com.sbellanger.weather.android.presentation.WeatherScreen
import org.koin.android.ext.android.inject

@ExperimentalAnimationApi
class WeatherActivity : AppCompatActivity() {

    ///////////////////////////////////////////////////////////////////////////
    // DATA
    ///////////////////////////////////////////////////////////////////////////

    private val viewModel: IWeatherContract.ViewModel by inject()

    ///////////////////////////////////////////////////////////////////////////
    // LIFECYCLE
    ///////////////////////////////////////////////////////////////////////////

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent { WeatherScreen(viewState = viewModel.viewState.value) }

        viewModel.getWeather("rennes")
    }
}
