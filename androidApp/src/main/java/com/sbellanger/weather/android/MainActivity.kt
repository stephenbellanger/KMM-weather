package com.sbellanger.weather.android

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.sbellanger.weather.IWeatherViewModel
import com.sbellanger.weather.WeatherViewModel
import com.sbellanger.weather.ViewState
import com.sbellanger.weather.domain.WeatherEntity

class MainActivity : AppCompatActivity(), IMainContract.ViewCapability {

    ///////////////////////////////////////////////////////////////////////////
    // DATA
    ///////////////////////////////////////////////////////////////////////////

    private lateinit var viewModel: IWeatherViewModel

    ///////////////////////////////////////////////////////////////////////////
    // LIFECYCLE
    ///////////////////////////////////////////////////////////////////////////

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this)[WeatherViewModel::class.java]

        viewModel.getWeather("rennes")

        viewModel.viewState.addObserver { handleViewState(it) }
    }

    ///////////////////////////////////////////////////////////////////////////
    // SPECIALIZATION
    ///////////////////////////////////////////////////////////////////////////

    override fun showLoader(shouldShow: Boolean) {
        findViewById<View>(R.id.loader).visibility =
            if (shouldShow) View.VISIBLE
            else View.GONE
    }

    override fun showError(errorMessage: String) {
        val tv: TextView = findViewById(R.id.text_view)
        tv.text = errorMessage
    }

    override fun showWeather(weather: WeatherEntity) {
        val tv: TextView = findViewById(R.id.text_view)
        tv.text = StringBuilder()
            .appendLine(weather.weather)
            .appendLine(weather.temperature)
    }

    ///////////////////////////////////////////////////////////////////////////
    // HELPER
    ///////////////////////////////////////////////////////////////////////////

    private fun handleViewState(viewState: ViewState) {
        when (viewState) {
            is ViewState.Error -> {
                showError(viewState.errorMessage)
                showLoader(false)
            }
            is ViewState.HasResult -> {
                showWeather(viewState.weather)
                showLoader(false)
            }
            ViewState.Loading -> showLoader(true)
        }
    }
}
