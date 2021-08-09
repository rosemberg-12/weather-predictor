package co.rosemberg.weatherpredictor.component.WeatherConditions;

import co.rosemberg.weatherpredictor.domain.MeteorologicalHistory;
import co.rosemberg.weatherpredictor.domain.Weather;


public class DroughtCondition implements WeatherCondition {
    @Override
    public boolean applyValidation(MeteorologicalHistory instant) {
        Double referenceValue= instant.getPlanetWithGrades().values().stream().findFirst().get();
        return instant.getPlanetWithGrades().values().stream().allMatch(grade->isSameOrOpposite(referenceValue,grade));
    }

    public boolean isSameOrOpposite(Double referenceValue, Double toCompare){
        return referenceValue.equals(toCompare) || referenceValue.equals(Math.abs(toCompare-180d)) || referenceValue.equals(Math.abs(toCompare+180d));
    }

    @Override
    public Weather getWeather() {
        return Weather.DROUGHT;
    }
}
