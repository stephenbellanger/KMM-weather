package com.sbellanger.weather.android.presentation

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.sbellanger.weather.ViewState
import com.sbellanger.weather.android.R

@ExperimentalAnimationApi
@Composable
fun WeatherScreen(viewState: ViewState) {
    Box {
        Image(
            painter = painterResource(id = R.drawable.background_night),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
        )
        when (viewState) {
            is ViewState.Error -> ErrorScreen(viewState)
            is ViewState.HasResult -> LoadedScreen(viewState)
            ViewState.Loading -> LoadingScreen()
        }
    }
}

@Composable
fun LoadedScreen(viewState: ViewState.HasResult) {
    WeatherDescriptionView(
        temperature = viewState.weather.temperature,
        city = viewState.weather.city,
        date = viewState.weather.date,
        description = viewState.weather.description,
        humidity = viewState.weather.humidity,
        pressure = viewState.weather.pressure,
        wind = viewState.weather.wind
    )
}

@Composable
fun ErrorScreen(viewState: ViewState.Error) {
    Text(
        text = viewState.errorMessage,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(Alignment.Center),
        color = Color.White,
        fontWeight = FontWeight.Bold,
        fontSize = 50.sp
    )
}

@ExperimentalAnimationApi
@Composable
fun LoadingScreen() {
    LoaderView(true)
}

@ExperimentalAnimationApi
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    WeatherScreen(ViewState.Loading)
}

@ExperimentalAnimationApi
@Preview(
    showBackground = true,
    uiMode = UI_MODE_NIGHT_YES
)
@Composable
fun DarkModePreview() {
    WeatherScreen(ViewState.Loading)
}
