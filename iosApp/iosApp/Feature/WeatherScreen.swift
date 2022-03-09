//
//  WeatherScreen.swift
//  iosApp
//
//  Created by Stephen BELLANGER on 09/03/2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import sha

struct WeatherScreen : View {
    let viewModel: WeatherViewModel
    
    var body: some View {
        GeometryReader { geometry in
            ZStack {
                Image("background_night")
                    .resizable()
                    .scaledToFill()
                    .edgesIgnoringSafeArea(.all)
                if(viewModel.viewState is ViewState.HasResult){
                    let successState = viewModel.viewState as! ViewState.HasResult
                    WeatherDescriptionView(
                        temperature: successState.weather.temperature,
                        city: successState.weather.city,
                        date: successState.weather.date,
                        description: successState.weather.description_,
                        humidity: successState.weather.humidity,
                        pressure: successState.weather.pressure,
                        wind: successState.weather.wind
                    )
                } else if(viewModel.viewState is ViewState.Error){
                    let errorState = viewModel.viewState as! ViewState.Error
                    ErrorView(message: errorState.errorMessage)

                } else if (viewModel.viewState is ViewState.Loading){
                    LoaderView()
                }
            }
        }
    }
}
