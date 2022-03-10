package com.sbellanger.weather.di

import com.sbellanger.weather.data.IWeatherRepository
import com.sbellanger.weather.data.WeatherRepository
import com.sbellanger.weather.domain.GetWeatherByLocationUseCase
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

fun initKoin(appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        appDeclaration()
        modules(
            repositoryModule,
            useCasesModule,
            platformModule()
        )
    }

// For iOS
fun initKoin() = initKoin {}

val repositoryModule = module {
    single<IWeatherRepository> { WeatherRepository() }
}

val useCasesModule: Module = module {
    factory { GetWeatherByLocationUseCase(get()) }
}
