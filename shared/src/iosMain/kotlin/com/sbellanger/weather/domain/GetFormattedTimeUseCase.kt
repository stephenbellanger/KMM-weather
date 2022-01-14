package com.sbellanger.weather.domain

import com.sbellanger.weather.helper.Constants
import com.sbellanger.weather.helper.capitalize
import platform.Foundation.*

actual class GetFormattedTimeUseCase {

    ///////////////////////////////////////////////////////////////////////////
    // SPECIALIZATION
    ///////////////////////////////////////////////////////////////////////////

    actual fun execute(): String {
        val dateFormat = NSDateFormatter().apply {
            dateFormat = Constants.DATE_FORMAT
            locale = NSLocale(Constants.LOCALE)
            timeZone = NSTimeZone.timeZoneWithName(Constants.TIME_ZONE)
                ?: NSTimeZone.timeZoneForSecondsFromGMT(0)
        }
        return dateFormat.stringFromDate(NSDate()).capitalize()
    }
}
