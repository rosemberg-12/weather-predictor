package co.rosemberg.weatherpredictor.component.WeatherConditions;

import co.rosemberg.weatherpredictor.domain.MeteorologicalHistory;
import co.rosemberg.weatherpredictor.domain.Weather;

public class OptimalCondition implements WeatherCondition {
    @Override
    public boolean applyValidation(MeteorologicalHistory instant) {
        return true;
    }

    @Override
    public Weather getWeather() {
        return Weather.OPTIMAL;
    }
}
