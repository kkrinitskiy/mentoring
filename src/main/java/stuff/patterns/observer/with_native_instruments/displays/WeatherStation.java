package stuff.patterns.observer.with_native_instruments.displays;

public class WeatherStation {
    public static void main(String[] args) {
        WeatherData weatherData = new WeatherData();

        CurrentConditionDisplay currentConditionDisplay = new CurrentConditionDisplay(weatherData);
        StatisticsDisplay statisticsDisplay = new StatisticsDisplay(weatherData);
        ForecastDisplay forecastDisplay = new ForecastDisplay(weatherData);
//        ThirdPartyDisplay thirdPartyDisplay = new ThirdPartyDisplay(weatherData);
        HeatIndexDisplay heatIndexDisplay = new HeatIndexDisplay(weatherData);

        System.out.println("\n######### 21.05.24 #########");
        weatherData.setMeasurements(80, 65, 30.4f);
        System.out.println("\n######### 22.05.24 #########");
        weatherData.setMeasurements(82, 70, 29.2f);
        System.out.println("\nn######### 23.05.24 #########");
        weatherData.setMeasurements(78, 90, 29.2f);

    }
}
