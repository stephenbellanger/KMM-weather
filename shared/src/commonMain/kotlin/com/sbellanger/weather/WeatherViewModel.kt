package com.sbellanger.weather

import com.sbellanger.weather.domain.GetWeatherByLocationUseCase
import dev.icerock.moko.mvvm.livedata.MutableLiveData
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.launch

class WeatherViewModel : ViewModel(), IWeatherViewModel {

    ///////////////////////////////////////////////////////////////////////////
    // DATA
    ///////////////////////////////////////////////////////////////////////////

    override val viewState = MutableLiveData<ViewState>(ViewState.Loading)

    ///////////////////////////////////////////////////////////////////////////
    // SPECIALIZATION
    ///////////////////////////////////////////////////////////////////////////

    override fun getWeather(city: String) {
        viewModelScope.launch {
            val viewState = try {
                GetWeatherByLocationUseCase().execute(city)?.let { result ->
                    ViewState.HasResult(result)
                } ?: ViewState.Error("No result for $city")
            } catch (e: Exception) {
                ViewState.Error("Error : ${e.message}")
            }
            this@WeatherViewModel.viewState.postValue(viewState)
        }
    }
}
