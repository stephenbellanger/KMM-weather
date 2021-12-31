package com.sbellanger.weather.android

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import com.sbellanger.weather.IWeatherViewModel
import com.sbellanger.weather.ViewState
import com.sbellanger.weather.WeatherViewModel
import com.sbellanger.weather.domain.WeatherEntity
import java.util.*

@ExperimentalAnimationApi
class MainActivity : AppCompatActivity(), IMainContract.ViewCapability {

    ///////////////////////////////////////////////////////////////////////////
    // DATA
    ///////////////////////////////////////////////////////////////////////////

    private lateinit var viewModel: IWeatherViewModel

    // TODO: In VM ?
    private val description = mutableStateOf("")
    private val temperature = mutableStateOf("")
    private val city = mutableStateOf("")
    private val date = mutableStateOf("")
    private val pressure = mutableStateOf("")
    private val humidity = mutableStateOf("")
    private val wind = mutableStateOf("")
    private val loaderVisibility = mutableStateOf(true)

    ///////////////////////////////////////////////////////////////////////////
    // LIFECYCLE
    ///////////////////////////////////////////////////////////////////////////

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Box {
                Image(
                    painter = painterResource(id = R.drawable.background_night),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                )
                WeatherDescription()
                Loader()
            }
        }

        viewModel = ViewModelProvider(this)[WeatherViewModel::class.java]

        viewModel.apply {
            getWeather("rennes")
            viewState.addObserver { handleViewState(it) }
        }
    }

    ///////////////////////////////////////////////////////////////////////////
    // SPECIALIZATION
    ///////////////////////////////////////////////////////////////////////////

    override fun showLoader(shouldShow: Boolean) {
        loaderVisibility.value = shouldShow
    }

    override fun showError(errorMessage: String) {
        description.value = errorMessage
    }

    override fun showWeather(weather: WeatherEntity) {
        description.value = weather.description
        temperature.value = weather.temperature
        city.value = weather.city
        date.value = weather.date
        pressure.value = weather.pressure
        humidity.value = weather.humidity
        wind.value = weather.wind
    }

    ///////////////////////////////////////////////////////////////////////////
    // UI SETUP
    ///////////////////////////////////////////////////////////////////////////

    @Composable
    fun WeatherDescription() {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = temperature.value,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentSize(Alignment.Center),
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 50.sp
            )
            Text(
                text = city.value,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentSize(Alignment.Center)
                    .padding(top = 20.dp),
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 40.sp
            )
            Text(
                text = date.value,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentSize(Alignment.Center)
                    .padding(top = 16.dp),
                color = Color.Gray,
                fontSize = 20.sp
            )
            Text(
                text = description
                    .value
                    .replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(Locale.FRANCE)
                        else it.toString()
                    },
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentSize(Alignment.Center)
                    .padding(top = 16.dp),
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
            )
            WeatherStatistics()
        }
    }

    @Composable
    fun WeatherStatistics() {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            WeatherStatistic(
                text = humidity.value,
                iconResId = R.drawable.ic_humidity
            )
            WeatherStatistic(
                text = pressure.value,
                iconResId = R.drawable.ic_pressure
            )
            WeatherStatistic(
                text = wind.value,
                iconResId = R.drawable.ic_wind
            )
        }
    }

    @Composable
    fun WeatherStatistic(
        text: String,
        @DrawableRes iconResId: Int
    ) {
        Column(
            modifier = Modifier
                .padding(
                    start = 12.dp,
                    end = 12.dp
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = text,
                modifier = Modifier
                    .padding(bottom = 6.dp),
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp,
            )
            Image(
                painter = painterResource(iconResId),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(32.dp)
                    .clip(CircleShape)
                    .border(2.dp, Color.White, CircleShape)
                    .padding(6.dp)
            )
        }
    }

    @Composable
    fun Loader() {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
        ) {
            AnimatedVisibility(
                visible = loaderVisibility.value
            ) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(200.dp)
                        .fillMaxSize(),
                    color = Color.White,
                    strokeWidth = 5.dp
                )
            }
        }
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
