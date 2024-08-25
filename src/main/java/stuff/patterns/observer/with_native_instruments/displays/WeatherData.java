package stuff.patterns.observer.with_native_instruments.displays;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class WeatherData {

    private PropertyChangeSupport support;
    private float temperature;
    private float humidity;
    private float pressure;

    public WeatherData() {
        support = new PropertyChangeSupport(this);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        support.removePropertyChangeListener(listener);
    }

    public void setMeasurements(float temperature, float humidity, float pressure){

        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;


        support.firePropertyChange("data", null, new PacketWeatherData(temperature, humidity, pressure));
    }
}
