import SwiftUI
import shared

class ObservableState: ObservableObject {
    @Published var viewModel: IWeatherViewModel = WeatherViewModel()
    @Published var description: String?
    @Published var temperature: String?

    init() {
        self.viewModel.getWeather(city: "rennes")
        self.viewModel.viewState.addObserver { (viewState) in
            if(viewState is ViewState.HasResult){
                let successState = viewState as! ViewState.HasResult
                self.description = successState.weather.weather
                self.temperature = successState.weather.temperature
                
            }
            else if(viewState is ViewState.Error){
                let errorState = viewState as! ViewState.Error
                self.description = errorState.errorMessage
            }
            else if(viewState is ViewState.Loading){
                self.description = "Loading"
            }
        }
    }
}

struct ContentView: View {
    @StateObject private var observableState = ObservableState()
    
    var body: some View {
        VStack {
            if let description = observableState.description {
                Text(description)
                    .font(.title)
            }
            if let temperature = observableState.temperature{
                Text(temperature)
                    .font(.subheadline)
                    .foregroundColor(.gray)
            }
        }
        .frame(maxWidth: .infinity)
    }
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}
