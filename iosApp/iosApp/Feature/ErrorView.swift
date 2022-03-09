//
//  ErrorView.swift
//  iosApp
//
//  Created by Stephen BELLANGER on 09/03/2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI

struct ErrorView : View {
    
    let message: String
    
    var body: some View {
        Text(message)
            .font(.system(size: 36))
            .bold()
            .foregroundColor(.white)
    }
}
