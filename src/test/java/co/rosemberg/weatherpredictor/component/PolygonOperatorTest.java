package co.rosemberg.weatherpredictor.component;

import co.rosemberg.weatherpredictor.domain.Planet;
import co.rosemberg.weatherpredictor.domain.Rotation;
import co.rosemberg.weatherpredictor.fixture.PlanetFixture;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.data.util.Pair;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PolygonOperatorTest {

    private PolygonOperator operator;

    @BeforeAll
    void setupTest(){
        this.operator= new PolygonOperator();
    }

    @Test
    void getSlope_success(){
        Pair<Double, Double> p1=Pair.of(1d,1d);
        Pair<Double, Double> p2=Pair.of(2.5d,3d);
        Double slope= operator.getSlope(p1,p2);
        assertEquals(1.33d,slope);
    }

    @Test
    void getSlope_ZeroCase(){
        Pair<Double, Double> p1=Pair.of(1d,1d);
        Pair<Double, Double> p2=Pair.of(3d,1d);
        Double slope= operator.getSlope(p1,p2);
        assertEquals(0d,slope);
    }

    @Test
    void getSlope_InfinityCase(){
        Pair<Double, Double> p1=Pair.of(1d,1d);
        Pair<Double, Double> p2=Pair.of(1d,3d);
        Double slope= operator.getSlope(p1,p2);
        assertEquals(Double.POSITIVE_INFINITY,slope);
    }

    @Test
    void polarCoordinatesToCartesian_success(){
        Pair<Double,Double> result= operator.polarCoordinatesToCartesian(90d,4);
        assertEquals(0d, result.getFirst());
        assertEquals(4d, result.getSecond());
    }

    @Test
    void getPerimeterOfTriangle_success(){
        Map<Planet, Double> mapTest= new HashMap<>();
        mapTest.put(PlanetFixture.getPlanet("test1", Rotation.LEFT, 0, 4), 0d);
        mapTest.put(PlanetFixture.getPlanet("test2", Rotation.LEFT, 0, 4), 90d);
        mapTest.put(PlanetFixture.getPlanet("test3", Rotation.LEFT, 0, 4), 180d);

        Double perimeter= operator.getPerimeterOfTriangle(mapTest);

        assertEquals(19.31370d, perimeter);
    }



}
