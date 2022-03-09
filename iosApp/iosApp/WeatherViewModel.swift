//
//  WeatherViewModel.swift
//  iosApp
//
//  Created by Stephen BELLANGER on 09/03/2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation
import shared
import UIKit

    @MainActor class WeatherViewModel : ObservableObject {
    
        @Published var viewState: ViewState?
        
        init(){
            getWeather(city: "rennes")
        }
        
        func getWeather(city: String){
            self.viewState = ViewState.Loading()
            GetWeatherByLocationUseCase().execute(city: city) { result, error in
                if let weather = result {
                    self.viewState = ViewState.HasResult(weather: weather)
                } else {
                    self.viewState = ViewState.Error(errorMessage: "No result for \(city)")
                }
            }
        }
    }

