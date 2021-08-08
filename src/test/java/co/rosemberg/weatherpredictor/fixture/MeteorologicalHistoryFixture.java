package co.rosemberg.weatherpredictor.fixture;

import co.rosemberg.weatherpredictor.domain.MeteorologicalHistory;
import co.rosemberg.weatherpredictor.domain.Planet;
import co.rosemberg.weatherpredictor.domain.Rotation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MeteorologicalHistoryFixture {

    public static List<MeteorologicalHistory> getHistoryListWithAlignedGrades(){
        return List.of(getHistoryWithCustomGrades(0,0,0),getHistoryWithCustomGrades(0,180,0), getHistoryWithCustomGrades(270,90,270));
    }

    public static MeteorologicalHistory getHistoryWithCustomGrades(Integer grade1, Integer grade2, Integer grade3){
        MeteorologicalHistory historyToReturn= new MeteorologicalHistory();
        historyToReturn.setDay(1);
        Map<Planet, Integer> grades=new HashMap<>();
        grades.put(PlanetFixture.getPlanet("test1", Rotation.LEFT, 1, 1), grade1);
        grades.put(PlanetFixture.getPlanet("test2", Rotation.LEFT, 1, 1), grade2);
        grades.put(PlanetFixture.getPlanet("test2", Rotation.LEFT, 1, 1), grade3);

        historyToReturn.setPlanetWithGrades(grades);

        return historyToReturn;
    }
}
