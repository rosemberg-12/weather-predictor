package co.rosemberg.weatherpredictor.component.WeatherConditions;

import co.rosemberg.weatherpredictor.domain.MeteorologicalHistory;
import co.rosemberg.weatherpredictor.domain.Weather;

public class DroughtCondition implements WeatherCondition {
    @Override
    public boolean applyValidation(MeteorologicalHistory instant) {
        return false;
    }

    @Override
    public Weather getWeather() {
        return Weather.DROUGHT;
    }
}
