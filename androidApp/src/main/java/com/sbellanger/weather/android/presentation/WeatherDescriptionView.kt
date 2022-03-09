package com.sbellanger.weather.android.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.util.*

@Composable
fun WeatherDescriptionView(
    temperature: String,
    city: String,
    date: String,
    description: String,
    humidity: String,
    pressure: String,
    wind: String
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = temperature,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize(Alignment.Center),
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 50.sp
        )
        Text(
            text = city,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize(Alignment.Center)
                .padding(top = 20.dp),
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 40.sp
        )
        Text(
            text = date,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize(Alignment.Center)
                .padding(top = 16.dp),
            color = Color.Gray,
            fontSize = 20.sp
        )
        Text(
            text = description,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize(Alignment.Center)
                .padding(top = 16.dp),
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
        )
        WeatherStatisticsView(
            humidity = humidity,
            pressure = pressure,
            wind = wind
        )
    }
}
