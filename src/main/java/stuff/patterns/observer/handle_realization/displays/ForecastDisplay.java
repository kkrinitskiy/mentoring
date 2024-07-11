package stuff.patterns.observer.handle_realization.displays;

import stuff.patterns.observer.handle_realization.Observer;
import stuff.patterns.observer.handle_realization.Subject;
import stuff.patterns.observer.handle_realization.core.WeatherData;

import java.util.Random;

public class ForecastDisplay implements DisplayElement, Observer {

    private float temperature;


    private WeatherData weatherData;

    public ForecastDisplay(WeatherData weatherData) {
        this.weatherData = weatherData;
        weatherData.register(this);
    }

    @Override
    public void display() {
        int r = new Random().nextInt(0, 100);
        System.out.println("\n\tForecast:");
        System.out.println(
                r < 33 ? "It's cold and rainy!" :
                        r < 66 ? "It's comfortable and sunny" :
                                "It's hot like desert!");
    }

    @Override
    public void update() {
        this.temperature = weatherData.getTemperature();
        display();
    }
}
