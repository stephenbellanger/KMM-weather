import SwiftUI
import shared

struct ContentView: View {
    @StateObject private var viewModel = WeatherViewModel()
    
    var body: some View {
        GeometryReader { geometry in
            ZStack {
                BackgroundView()
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
