package co.rosemberg.weatherpredictor.component.WeatherConditions;

import co.rosemberg.weatherpredictor.domain.MeteorologicalHistory;
import co.rosemberg.weatherpredictor.domain.Weather;

public interface WeatherCondition {
    boolean applyValidation(MeteorologicalHistory instant);
    Weather getWeather();
}
