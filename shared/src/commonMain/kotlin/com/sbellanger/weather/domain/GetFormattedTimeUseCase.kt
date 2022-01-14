package com.sbellanger.weather.domain

expect class GetFormattedTimeUseCase() {
    fun execute(): String
}
