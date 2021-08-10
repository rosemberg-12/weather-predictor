package co.rosemberg.weatherpredictor.component;

import co.rosemberg.weatherpredictor.dao.HistoryDao;
import co.rosemberg.weatherpredictor.dao.PlanetDao;
import co.rosemberg.weatherpredictor.dao.StatisticDao;
import co.rosemberg.weatherpredictor.domain.MeteorologicalHistory;
import co.rosemberg.weatherpredictor.domain.Planet;
import co.rosemberg.weatherpredictor.domain.Rotation;
import co.rosemberg.weatherpredictor.domain.Weather;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class WeatherSimulator {

    private final PlanetDao planetDao;
    private final HistoryDao historyDao;
    private final StatisticDao statisticDao;
    private final WeatherCalculator weatherCalculator;
    private final Integer years;
    private final Integer initialGrade;
    private final PolygonOperator polygonOperator;

    public WeatherSimulator(PlanetDao planetDao, HistoryDao historyDao, WeatherCalculator weatherCalculator, @Value("${years.initial:10}") String years,
                            @Value("${grade.initial:0}") String initialGrade, PolygonOperator polygonOperator, StatisticDao statisticDao) {
        this.planetDao = planetDao;
        this.historyDao = historyDao;
        this.weatherCalculator = weatherCalculator;
        this.years=Integer.parseInt(years);
        this.initialGrade=Integer.parseInt(initialGrade);
        this.polygonOperator= polygonOperator;
        this.statisticDao=statisticDao;

    }

    public void startSimulation(){
        historyDao.deleteHistory();
        statisticDao.deleteAll();
        final List<Planet> planets= planetDao.getAllPlanets();

        CompletableFuture[] taskToExecute=planets.stream()
                .map(planet -> createSimulationPerPlanet(planet, planets)).toArray(CompletableFuture[]::new);

        CompletableFuture.allOf(taskToExecute).whenComplete((unused, throwable) -> System.out.println("Operation end"));
    }

    private CompletableFuture createSimulationPerPlanet(final Planet referencePlanet, final List<Planet> planets){
        return CompletableFuture.runAsync(()->{

            List<MeteorologicalHistory> historyList= new ArrayList<>();
            Map<Planet,Double> planetInformation= planets.stream().collect(Collectors.toMap(Function.identity(),planet->Double.valueOf(this.initialGrade)));
            Map<Weather, Integer> weatherPeriods= Stream.of(Weather.values()).collect(Collectors.toMap(Function.identity(),item->0));

            double daysToProcess= Math.floor(360d/referencePlanet.getAngularVelocity())*years;
            Pair<Integer, Double> dayAndPerimeter= Pair.of(-1,-1d);

            for(int i=0; i<daysToProcess; i++){

                MeteorologicalHistory currentDay=new MeteorologicalHistory();
                currentDay.setDay(i);
                currentDay.setReferencePlanet(referencePlanet);
                currentDay.setPlanetWithGrades(planetInformation);
                currentDay.setCurrentWeather(weatherCalculator.getWeatherByInstant(currentDay));

                weatherPeriods.put(currentDay.getCurrentWeather(),weatherPeriods.get(currentDay.getCurrentWeather())+1);

                if(currentDay.getCurrentWeather()== Weather.RAIN && currentDay.getPlanetWithGrades().size() == 3){
                    Double currentPerimeter= polygonOperator.getPerimeterOfTriangle(currentDay.getPlanetWithGrades());
                    if(dayAndPerimeter.getSecond()<currentPerimeter){
                        dayAndPerimeter=Pair.of(currentDay.getDay(), currentPerimeter);
                    }
                }
                historyList.add(currentDay);
                planetInformation=planetInformation.entrySet().stream()
                        .collect(Collectors.toMap(Map.Entry::getKey,entry-> makeGradeMovement(entry.getKey(), entry.getValue())));
            }
            statisticDao.insertPlanetStatics(referencePlanet, weatherPeriods.get(Weather.DROUGHT), weatherPeriods.get(Weather.RAIN),
                    dayAndPerimeter.getFirst(), weatherPeriods.get(Weather.OPTIMAL));
            historyDao.saveHistoryList(historyList);
        });
    }

    private Double makeGradeMovement(Planet planet, Double currentGrade){
        double newGrade;
        if(planet.getRotation()== Rotation.LEFT){
            newGrade=currentGrade+planet.getAngularVelocity();
            if(360-newGrade<=0){
                newGrade=newGrade-360;
            }
        }else{
            newGrade=currentGrade-planet.getAngularVelocity();
            if(newGrade<0){
                newGrade=newGrade+360;
            }
        }

        return newGrade;
    }
}
