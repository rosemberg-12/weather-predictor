package co.rosemberg.weatherpredictor.component.WeatherConditions;

import co.rosemberg.weatherpredictor.domain.MeteorologicalHistory;
import co.rosemberg.weatherpredictor.domain.Weather;


public class DroughtCondition implements WeatherCondition {
    @Override
    public boolean applyValidation(MeteorologicalHistory instant) {
        Integer referenceValue= instant.getPlanetWithGrades().values().stream().findFirst().get();
        return instant.getPlanetWithGrades().values().stream().allMatch(grade->isSameOrOpposite(referenceValue,grade));
    }

    public boolean isSameOrOpposite(Integer referenceValue, Integer toCompare){
        return referenceValue.equals(toCompare) || toCompare.equals(Math.abs(referenceValue-180));
    }

    @Override
    public Weather getWeather() {
        return Weather.DROUGHT;
    }
}
