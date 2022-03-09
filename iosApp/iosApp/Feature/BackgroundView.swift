//
//  BackgroundView.swift
//  iosApp
//
//  Created by Stephen BELLANGER on 09/03/2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI

struct BackgroundView : View {
    
    var body: some View {
        Image("background_night")
            .resizable()
            .scaledToFill()
            .edgesIgnoringSafeArea(.all)
    }
}
