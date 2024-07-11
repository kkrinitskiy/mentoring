package stuff.patterns.observer.with_native_instruments.displays;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.MessageFormat;

public class CurrentConditionDisplay implements DisplayElement, PropertyChangeListener {

    private WeatherData weatherData;
    private float temperature;
    private float humidity;
    private float pressure;

    public CurrentConditionDisplay(WeatherData weatherData) {
        this.weatherData = weatherData;
        weatherData.addPropertyChangeListener(this);
    }

    @Override
    public void display() {
        System.out.println("\n\tCurrent condition:");
        System.out.println(MessageFormat.format("Temperature:{0}; Humidity:{1}; Pressure:{2}",
                temperature, humidity, pressure));
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        PacketWeatherData p = (PacketWeatherData) evt.getNewValue();
        this.temperature = p.temperature();
        this.humidity = p.humidity();
        this.pressure = p.pressure();
        display();
    }
}
