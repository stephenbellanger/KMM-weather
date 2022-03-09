//
//  WeatherStatistic.swift
//  iosApp
//
//  Created by Stephen BELLANGER on 09/03/2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI

struct WeatherStatistic: View {
    var text: String
    var iconRes: String
    
    var body: some View {
        VStack {
            Text(text)
                .font(.system(size: 12))
                .bold()
                .foregroundColor(.white)
            
            Image(iconRes)
                .resizable()
                .frame(width: 20, height: 20)
                .padding(6)
                .clipShape(Circle())
                .overlay(Circle().stroke(Color.white, lineWidth: 2))
        }
    }
}
