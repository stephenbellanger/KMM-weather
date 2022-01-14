package com.sbellanger.weather.helper

fun String.capitalize(): String {
    return this.replaceFirstChar {
        if (it.isLowerCase()) it.titlecase()
        else it.toString()
    }
}
