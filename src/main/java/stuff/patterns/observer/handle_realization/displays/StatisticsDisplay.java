package stuff.patterns.observer.handle_realization.displays;

import stuff.patterns.observer.handle_realization.Observer;
import stuff.patterns.observer.handle_realization.Subject;
import stuff.patterns.observer.handle_realization.core.WeatherData;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StatisticsDisplay  implements DisplayElement, Observer {

    private List<Float> temperature;

    private WeatherData weatherData;

    public StatisticsDisplay(WeatherData weatherData) {
        temperature = new ArrayList<>();
        this.weatherData = weatherData;
        weatherData.register(this);
    }

    @Override
    public void display() {
        float maxTemp = Collections.max(temperature);
        float minTemp = Collections.min(temperature);
        float averageTemp = 0;

        for (Float v : temperature) {
            averageTemp += v;
        }

        averageTemp = averageTemp / temperature.size();

        System.out.println("\n\tStatistics:");
        System.out.println(MessageFormat.format("Max t:{0}; Min t:{1}; Average t:{2}; data count:{3}",
                maxTemp, minTemp, averageTemp, temperature.size()));
    }

    @Override
    public void update() {
        this.temperature.add(weatherData.getTemperature());
        display();
    }
}
