//
//  ViewController.swift
//  iosApp
//
//  Created by Stephen BELLANGER on 08/03/2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation
import UIKit

class ViewController : UIViewController {
    private var viewModel: WeatherViewModel!
    
    override func viewDidLoad() {
        super.viewDidLoad()
    }

    func callToViewModelForUIUpdate(){
        self.viewModel = WeatherViewModel()
    }
}
