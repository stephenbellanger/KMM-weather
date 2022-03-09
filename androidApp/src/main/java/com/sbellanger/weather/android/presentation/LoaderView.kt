package com.sbellanger.weather.android.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@ExperimentalAnimationApi
@Composable
fun LoaderView(isVisible: Boolean) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
    ) {
        AnimatedVisibility(
            visible = isVisible
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
