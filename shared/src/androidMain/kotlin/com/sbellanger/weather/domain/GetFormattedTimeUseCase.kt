package com.sbellanger.weather.domain

import com.sbellanger.weather.helper.Constants
import com.sbellanger.weather.helper.capitalize
import org.joda.time.DateTimeZone
import org.joda.time.LocalDateTime
import org.joda.time.format.DateTimeFormat
import java.util.*

actual class GetFormattedTimeUseCase {

    ///////////////////////////////////////////////////////////////////////////
    // SPECIALIZATION
    ///////////////////////////////////////////////////////////////////////////

    actual fun execute(): String {
        val currentDay = LocalDateTime.now(DateTimeZone.forID(Constants.TIME_ZONE))
        val dateFormat = DateTimeFormat
            .forPattern(Constants.DATE_FORMAT)
            .withLocale(Locale.forLanguageTag(Constants.LOCALE))

        return currentDay.toString(dateFormat).capitalize()
    }
}
