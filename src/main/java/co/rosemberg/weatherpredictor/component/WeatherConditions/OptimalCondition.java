package co.rosemberg.weatherpredictor.component.WeatherConditions;

import co.rosemberg.weatherpredictor.component.PolygonOperator;
import co.rosemberg.weatherpredictor.domain.MeteorologicalHistory;
import co.rosemberg.weatherpredictor.domain.Weather;
import org.springframework.data.util.Pair;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class OptimalCondition implements WeatherCondition {

    private final PolygonOperator polygonOperator;

    public OptimalCondition(PolygonOperator polygonOperator) {
        this.polygonOperator = polygonOperator;
    }

    @Override
    public boolean applyValidation(MeteorologicalHistory instant) {

        List<Pair<Double, Double>> coords= instant.getPlanetWithGrades().entrySet().stream().
                map(planetIntegerEntry -> polygonOperator.polarCoordinatesToCartesian(planetIntegerEntry.getValue(),planetIntegerEntry.getKey().
                        getDistanceFromSun())).collect(Collectors.toList());

        Pair<Double, Double> referencePoint= coords.stream().findAny().get();

        Set<Double> slope= coords.stream().filter(params->!params.equals(referencePoint)).map(params->polygonOperator
                .getSlope(referencePoint, params)).collect(Collectors.toSet());

        return slope.size()==1 && !slope.contains(polygonOperator.getSlope(referencePoint, Pair.of(0d,0d))) ;
    }


    @Override
    public Weather getWeather() {
        return Weather.OPTIMAL;
    }
}
