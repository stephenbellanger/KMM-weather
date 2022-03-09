package com.sbellanger.weather.android.presentation

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sbellanger.weather.android.R

@Composable
fun WeatherStatisticsView(
    humidity: String,
    pressure: String,
    wind: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 32.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        WeatherStatistic(
            text = humidity,
            iconResId = R.drawable.ic_humidity
        )
        WeatherStatistic(
            text = pressure,
            iconResId = R.drawable.ic_pressure
        )
        WeatherStatistic(
            text = wind,
            iconResId = R.drawable.ic_wind
        )
    }
}

@Composable
private fun WeatherStatistic(
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
