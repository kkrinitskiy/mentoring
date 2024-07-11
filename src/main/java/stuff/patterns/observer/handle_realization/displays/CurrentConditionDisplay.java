package stuff.patterns.observer.handle_realization.displays;

import stuff.patterns.observer.handle_realization.Observer;
import stuff.patterns.observer.handle_realization.Subject;
import stuff.patterns.observer.handle_realization.core.WeatherData;

import java.text.MessageFormat;

public class CurrentConditionDisplay  implements DisplayElement, Observer {

    private float temperature;
    private float humidity;
    private float pressure;

    private WeatherData weatherData;

    public CurrentConditionDisplay(WeatherData weatherData) {
        this.weatherData = weatherData;
        weatherData.register(this);
    }

    @Override
    public void display() {
        System.out.println("\n\tCurrent condition:");
        System.out.println(MessageFormat.format("Temperature:{0}; Humidity:{1}; Pressure:{2}",
                temperature, humidity, pressure));
    }

    @Override
    public void update() {
        this.temperature = weatherData.getTemperature();
        this.humidity = weatherData.getHumidity();
        this.pressure = weatherData.getPressure();
        display();
    }
}
