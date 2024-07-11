package stuff.patterns.observer.with_native_instruments.displays;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ThirdPartyDisplay implements DisplayElement, PropertyChangeListener {

    private float temperature;
    private float humidity;
    private float pressure;

    private WeatherData weatherData;

    public ThirdPartyDisplay(WeatherData weatherData) {
        this.weatherData = weatherData;
        weatherData.addPropertyChangeListener(this);
    }

    @Override
    public void display() {
        System.out.println("third party");
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
