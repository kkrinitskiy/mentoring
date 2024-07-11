package stuff.patterns.observer.handle_realization;

import stuff.patterns.observer.handle_realization.core.WeatherData;
import stuff.patterns.observer.handle_realization.displays.CurrentConditionDisplay;
import stuff.patterns.observer.handle_realization.displays.ForecastDisplay;
import stuff.patterns.observer.handle_realization.displays.HeatIndexDisplay;
import stuff.patterns.observer.handle_realization.displays.StatisticsDisplay;

public class Main {
    public static void main(String[] args) {
        WeatherData weatherData = new WeatherData();

        StatisticsDisplay statisticsDisplay = new StatisticsDisplay(weatherData);
        CurrentConditionDisplay currentConditionDisplay = new CurrentConditionDisplay(weatherData);
        ForecastDisplay forecastDisplay = new ForecastDisplay(weatherData);
        HeatIndexDisplay heatIndexDisplay = new HeatIndexDisplay(weatherData);

        System.out.println("\n######### 21.05.24 #########");
        weatherData.setMeasurements(80, 65, 30.4f);
        System.out.println("\n######### 22.05.24 #########");
        weatherData.setMeasurements(82, 70, 29.2f);
        System.out.println("\nn######### 23.05.24 #########");
        weatherData.setMeasurements(78, 90, 29.2f);
    }
}
