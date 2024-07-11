package stuff.patterns.observer.handle_realization.displays;

import stuff.patterns.observer.handle_realization.Observer;
import stuff.patterns.observer.handle_realization.Subject;
import stuff.patterns.observer.handle_realization.core.WeatherData;

public class ThirdPartyDisplay  implements DisplayElement, Observer {

    private float temperature;
    private float humidity;
    private float pressure;

    private WeatherData weatherData;

    public ThirdPartyDisplay(WeatherData weatherData) {
        this.weatherData = weatherData;
        weatherData.register(this);
    }

    @Override
    public void display() {

    }

    @Override
    public void update() {

    }
}
