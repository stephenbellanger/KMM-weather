package com.sbellanger.weather.domain

import kotlinx.datetime.*

class GetFormattedTimeUseCase {

    ///////////////////////////////////////////////////////////////////////////
    // PUBLIC API
    ///////////////////////////////////////////////////////////////////////////

    fun execute(): String {
        val currentMoment: Instant = Clock.System.now()
        val timezone = TimeZone.of("Europe/Paris")
        val datetimeInUtc: LocalDateTime = currentMoment.toLocalDateTime(timezone)
        return "${datetimeInUtc.dayOfWeek.name}, ${datetimeInUtc.hour}h${datetimeInUtc.minute}"
    }
}
