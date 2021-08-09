package co.rosemberg.weatherpredictor.component;

import co.rosemberg.weatherpredictor.component.WeatherConditions.WeatherCondition;
import co.rosemberg.weatherpredictor.domain.MeteorologicalHistory;
import co.rosemberg.weatherpredictor.domain.Weather;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class WeatherCalculator {

    private final List<WeatherCondition> weatherConditions;

    public WeatherCalculator(List<WeatherCondition> conditions){
        this.weatherConditions=conditions;
    }

    public Weather getWeatherByInstant(MeteorologicalHistory instant){
        return weatherConditions.stream().
                filter(weatherCondition -> weatherCondition.applyValidation(instant))
                .findFirst().map(WeatherCondition::getWeather).orElse(Weather.UNDEFINED);
    }

}
