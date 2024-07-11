package stuff.patterns.observer.with_native_instruments.displays;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Random;

public class ForecastDisplay implements DisplayElement, PropertyChangeListener {
    private WeatherData weatherData;
    private float temperature;

    public ForecastDisplay(WeatherData weatherData) {
        this.weatherData = weatherData;
        weatherData.addPropertyChangeListener(this);
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
    public void propertyChange(PropertyChangeEvent evt) {
        PacketWeatherData p = (PacketWeatherData) evt.getNewValue();
        this.temperature = p.temperature();

        display();
    }
}
