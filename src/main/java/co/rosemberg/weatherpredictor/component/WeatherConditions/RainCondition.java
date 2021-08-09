package co.rosemberg.weatherpredictor.component.WeatherConditions;

import co.rosemberg.weatherpredictor.component.PolygonOperator;
import co.rosemberg.weatherpredictor.domain.MeteorologicalHistory;
import co.rosemberg.weatherpredictor.domain.Weather;
import org.springframework.data.util.Pair;

import java.util.List;
import java.util.stream.Collectors;

public class RainCondition implements WeatherCondition {

    private final PolygonOperator polygonOperator;

    public RainCondition(PolygonOperator polygonOperator) {
        this.polygonOperator = polygonOperator;
    }

    @Override
    public boolean applyValidation(MeteorologicalHistory instant) {

        List<Pair<Double, Double>> coords= instant.getPlanetWithGrades().entrySet().stream().
                map(planetIntegerEntry -> polygonOperator.polarCoordinatesToCartesian(planetIntegerEntry.getValue(),
                        planetIntegerEntry.getKey().getDistanceFromSun())).collect(Collectors.toList());

        if(coords.size()==3){
            Pair<Double, Double> first=coords.get(0);
            Pair<Double, Double> second=coords.get(1);
            Pair<Double, Double> third=coords.get(2);
            Pair<Double, Double> sun= Pair.of(0d,0d);

            double a = triangleArea(first.getFirst(), first.getSecond(), second.getFirst(), second.getSecond(), third.getFirst(), third.getSecond());

            if(a==0)
                return false;

            double a1 = triangleArea (sun.getFirst(), sun.getSecond(), second.getFirst(), second.getSecond(), third.getFirst(), third.getSecond());

            double a2 = triangleArea (first.getFirst(), first.getSecond(), sun.getFirst(), sun.getSecond(), third.getFirst(), third.getSecond());

            double a3 = triangleArea (first.getFirst(), first.getSecond(), second.getFirst(), second.getSecond(),sun.getFirst(), sun.getSecond());

            return ((Math.round(a*10000)/10000d) == Math.round((a1 + a2 + a3)*10000)/10000d);
        }
        return false;
    }

    private double triangleArea(double x1, double y1, double x2, double y2, double x3, double y3){
        return Math.abs((x1*(y2-y3) + x2*(y3-y1)+x3*(y1-y2))/2.0);
    }

    @Override
    public Weather getWeather() {
        return Weather.RAIN;
    }
}
