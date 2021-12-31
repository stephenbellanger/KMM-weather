import SwiftUI
import shared

class ObservableState: ObservableObject {
    private var viewModel: IWeatherViewModel = WeatherViewModel()
    
    @Published var description: String?
    @Published var temperature: String?
    @Published var city: String?
    @Published var date: String?
    @Published var pressure: String?
    @Published var humidity: String?
    @Published var wind: String?
    @Published var loaderVisibility: Bool = true
    
    init() {
        self.viewModel.getWeather(city: "rennes")
        self.viewModel.viewState.addObserver { (viewState) in
            if(viewState is ViewState.HasResult){
                let successState = viewState as! ViewState.HasResult
                self.description = successState.weather.description_
                self.temperature = successState.weather.temperature
                self.city = successState.weather.city
                self.date = successState.weather.date
                self.pressure = successState.weather.pressure
                self.humidity = successState.weather.humidity
                self.wind = successState.weather.wind
                self.loaderVisibility = false
            }
            else if(viewState is ViewState.Error){
                let errorState = viewState as! ViewState.Error
                self.description = errorState.errorMessage
                self.loaderVisibility = false
            }
            else if(viewState is ViewState.Loading){
                self.loaderVisibility = true
            }
        }
    }
}

struct ContentView: View {
    @StateObject private var observableState = ObservableState()
    
    var body: some View {
        GeometryReader { geometry in
            ZStack {
                Image("background_night")
                    .resizable()
                    .scaledToFill()
                    .edgesIgnoringSafeArea(.all)
                weatherDescription
                loader
            }
        }
    }
    
    var weatherDescription: some View {
        VStack {
            if let temperature = observableState.temperature{
                Text(temperature)
                    .font(.system(size: 50))
                    .bold()
                    .foregroundColor(.white)
            }
            if let city = observableState.city{
                Text(city)
                    .font(.system(size: 40))
                    .bold()
                    .foregroundColor(.white)
            }
            if let date = observableState.date{
                Text(date)
                    .font(.system(size: 20))
                    .bold()
                    .foregroundColor(.gray)
            }
            if let description = observableState.description {
                Text(description.capitalized)
                    .font(.system(size: 20))
                    .bold()
                    .padding(.top, 12)
                    .foregroundColor(.white)
            }
            weatherStatictics
        }
        .frame(maxWidth: .infinity)
    }
    
    var weatherStatictics: some View {
        HStack(alignment: .center, spacing: 20) {
            if let humidity = observableState.humidity {
                WeatherStatistic(text: humidity, iconRes: "ic_humidity")
            }
            if let pressure = observableState.pressure {
                WeatherStatistic(text: pressure, iconRes: "ic_pressure")
            }
            if let wind = observableState.wind {
                WeatherStatistic(text: wind, iconRes: "ic_wind")
            }
        }
        .frame(maxWidth: .infinity)
        .padding(.top, 20)
    }
    
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
    
    var loader: some View {
        ZStack {
            if observableState.loaderVisibility {
                ProgressView()
                    .progressViewStyle(CircularProgressViewStyle())
            }
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
