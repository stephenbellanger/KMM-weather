//
//  WeatherDescriptionView.swift
//  iosApp
//
//  Created by Stephen BELLANGER on 09/03/2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI

struct WeatherDescriptionView : View {
    let temperature: String
    let city: String
    let date: String
    let description: String
    let humidity: String
    let pressure: String
    let wind: String
    
    var body: some View {
        VStack {
            if let temperature = temperature{
                Text(temperature)
                    .font(.system(size: 36))
                    .bold()
                    .foregroundColor(.white)
            }
            if let city = city{
                Text(city)
                    .font(.system(size: 26))
                    .bold()
                    .foregroundColor(.white)
                    .padding(.top, 16)
            }
            if let date = date{
                Text(date)
                    .font(.system(size: 16))
                    .bold()
                    .foregroundColor(.gray)
                    .padding(.top, 8)
            }
            if let description = description {
                Text(description)
                    .font(.system(size: 16))
                    .bold()
                    .padding(.top, 12)
                    .foregroundColor(.white)
            }
            HStack(alignment: .center, spacing: 20) {
                if let humidity = humidity {
                    WeatherStatistic(text: humidity, iconRes: "ic_humidity")
                }
                if let pressure = pressure {
                    WeatherStatistic(text: pressure, iconRes: "ic_pressure")
                }
                if let wind = wind {
                    WeatherStatistic(text: wind, iconRes: "ic_wind")
                }
            }
            .frame(maxWidth: .infinity)
            .padding(.top, 20)
        }
        .frame(maxWidth: .infinity)
    }
}
