package co.rosemberg.weatherpredictor.component;

import co.rosemberg.weatherpredictor.domain.Planet;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class PolygonOperator {

    public Double getSlope(Pair<Double, Double> referencePoint, Pair<Double, Double> coord) {
        double jX = referencePoint.getFirst() - coord.getFirst();
        double jY = referencePoint.getSecond() - coord.getSecond();

        if (jY == 0.0)
            return +0.0d;

        if (jX == 0.0)
            return Double.POSITIVE_INFINITY;

        return Math.round((jY / jX)*100)/100d;
    }

    public Pair<Planet, Pair<Double, Double>> polarCoordinatesToCartesian(Double value, Planet planet) {
        Double xPoint= planet.getDistanceFromSun()* Math.cos(Math.toRadians(value));
        Double yPoint= planet.getDistanceFromSun()* Math.sin(Math.toRadians(value));

        return Pair.of(planet, Pair.of(xPoint,yPoint));
    }

    public Double getPerimeterOfTriangle(Map<Planet, Double> planetWithGrades) {
        List<Pair<Double, Double>> coords= planetWithGrades.entrySet().stream().
                map(planetIntegerEntry -> polarCoordinatesToCartesian(planetIntegerEntry.getValue(),planetIntegerEntry.getKey()))
                .map(Pair::getSecond).collect(Collectors.toList());

        Pair<Double, Double> p1=coords.get(0);
        Pair<Double, Double> p2=coords.get(1);
        Pair<Double, Double> p3=coords.get(2);

        return calculateSideDistance(p1,p2)+calculateSideDistance(p2,p3)+calculateSideDistance(p1,p3);
    }

    private double calculateSideDistance(Pair<Double, Double> p1, Pair<Double, Double> p2){
        double x = Math.pow(((p2.getFirst())-(p1.getFirst())),2);
        double y = Math.pow(((p2.getSecond())-(p1.getSecond())),2);
        return Math.sqrt((x+y));
    }
}
