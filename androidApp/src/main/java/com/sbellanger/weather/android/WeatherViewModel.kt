package com.sbellanger.weather.android

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.sbellanger.weather.ViewState
import com.sbellanger.weather.domain.GetWeatherByLocationUseCase
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class WeatherViewModel : ViewModel(), IWeatherContract.ViewModel, KoinComponent {

    ///////////////////////////////////////////////////////////////////////////
    // DEPENDENCY
    ///////////////////////////////////////////////////////////////////////////

    private val getWeatherByLocationUseCase: GetWeatherByLocationUseCase by inject()

    ///////////////////////////////////////////////////////////////////////////
    // DATA
    ///////////////////////////////////////////////////////////////////////////

    override val viewState: MutableState<ViewState> = mutableStateOf(ViewState.Loading)

    ///////////////////////////////////////////////////////////////////////////
    // SPECIALIZATION
    ///////////////////////////////////////////////////////////////////////////

    override fun getWeather(city: String) {
        viewModelScope.launch {
            val viewState = try {
                getWeatherByLocationUseCase.execute(city)?.let { result ->
                    ViewState.HasResult(result)
                } ?: ViewState.Error("No result for $city")
            } catch (e: Exception) {
                ViewState.Error("Error : ${e.message}")
            }
            this@WeatherViewModel.viewState.value = viewState
        }
    }
}
