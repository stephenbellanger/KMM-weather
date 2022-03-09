//
//  LoaderView.swift
//  iosApp
//
//  Created by Stephen BELLANGER on 09/03/2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI

struct LoaderView : View {
    @State var spinCircle = false
    
    var body: some View {
        ZStack {
                Circle()
                    .trim(from: 0.5, to: 1)
                    .stroke(Color.white, lineWidth:4)
                    .frame(width:100)
                    .rotationEffect(.degrees(spinCircle ? 0 : -360), anchor: .center)
                    .animation(Animation.linear(duration: 1).repeatForever(autoreverses: false))
            }
            .onAppear {
                self.spinCircle = true
            }
    }
}
