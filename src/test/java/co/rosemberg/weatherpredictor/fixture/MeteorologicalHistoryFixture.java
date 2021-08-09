package co.rosemberg.weatherpredictor.fixture;

import co.rosemberg.weatherpredictor.domain.MeteorologicalHistory;
import co.rosemberg.weatherpredictor.domain.Planet;
import co.rosemberg.weatherpredictor.domain.Rotation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MeteorologicalHistoryFixture {

    public static List<MeteorologicalHistory> getHistoryListWithAlignedGradesRespectToSun(){
        return List.of(getHistoryWithCustomGrades(0d,0d,0d),getHistoryWithCustomGrades(0d,180d,0d), getHistoryWithCustomGrades(270d,90d,270d));
    }

    public static List<MeteorologicalHistory> getHistoryListWithAlignedGradesNotRespectToSun(){
        return List.of(getHistoryWithCustomGrades(344.6948870126213d,206.5520109219833d,200.7117979518603));
    }

    public static List<MeteorologicalHistory> getHistoryListTriangleWayAndSunInCenter(){
        return List.of(getHistoryWithCustomGrades(20d,140d,270d), getHistoryWithCustomGrades(346d,100d,270d));
    }

    public static List<MeteorologicalHistory> getHistoryListTriangleWayAndSunNotInCenter(){
        return List.of(getHistoryWithCustomGrades(270d,140d,270d), getHistoryWithCustomGrades(64d,140d,1d));
    }


    public static MeteorologicalHistory getHistoryWithCustomGrades(Double grade1, Double grade2, Double grade3){
        MeteorologicalHistory historyToReturn= new MeteorologicalHistory();
        historyToReturn.setDay(1);
        Map<Planet, Double> grades=new HashMap<>();
        grades.put(PlanetFixture.getPlanet("test1", Rotation.LEFT, 1, 200), grade1);
        grades.put(PlanetFixture.getPlanet("test2", Rotation.LEFT, 2, 500), grade2);
        grades.put(PlanetFixture.getPlanet("test3", Rotation.RIGHT, 3, 1000), grade3);

        historyToReturn.setPlanetWithGrades(grades);

        return historyToReturn;
    }
}
