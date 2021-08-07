package co.rosemberg.weatherpredictor.component;

import co.rosemberg.weatherpredictor.dao.HistoryDao;
import co.rosemberg.weatherpredictor.dao.PlanetDao;
import co.rosemberg.weatherpredictor.domain.MeteorologicalHistory;
import co.rosemberg.weatherpredictor.domain.Planet;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class WeatherSimulator {

    private final PlanetDao planetDao;
    private final HistoryDao historyDao;
    private final WeatherCalculator weatherCalculator;
    private final Integer years;
    private final Integer initialGrade;

    public WeatherSimulator(PlanetDao planetDao, HistoryDao historyDao, WeatherCalculator weatherCaculator,
                            @Value("${years.initial:10}") String years, @Value("${grade.initial:0}") String initialGrade) {
        this.planetDao = planetDao;
        this.historyDao = historyDao;
        this.weatherCalculator = weatherCaculator;
        this.years=Integer.parseInt(years);
        this.initialGrade=Integer.parseInt(initialGrade);

    }

    public void startSimulation(){
        historyDao.deleteHistory();
        final List<Planet> planets= planetDao.getAllPlanets();

        CompletableFuture[] taskToExecute=planets.stream()
                .map(planet -> createSimulationPerPlanet(planet, planets)).toArray(CompletableFuture[]::new);

        CompletableFuture.allOf(taskToExecute).whenComplete((unused, throwable) -> System.out.println("Operation end"));
    }

    private CompletableFuture<Boolean> createSimulationPerPlanet(final Planet referencePlanet, final List<Planet> planets){
        return CompletableFuture.supplyAsync(()->{

            List<MeteorologicalHistory> historyList= new ArrayList<>();
            Map<Planet,Integer> planetInformation= planets.stream().collect(Collectors.toMap(Function.identity(),planet->this.initialGrade));

            double daysToProcess= Math.floor(360d/referencePlanet.getAngularVelocity())*years;

            for(int i=0; i<daysToProcess; i++){

                MeteorologicalHistory currentDay=new MeteorologicalHistory();
                currentDay.setDay(i);
                currentDay.setReferencePlanet(referencePlanet);
                currentDay.setPlanetWithGrades(planetInformation);
                currentDay.setCurrentWeather(weatherCalculator.getWeatherByInstant(currentDay));

                historyList.add(currentDay);

                planetInformation=planetInformation.entrySet().stream()
                        .collect(Collectors.toMap(Map.Entry::getKey,entry->{
                            int newGrade=entry.getValue()+entry.getKey().getAngularVelocity();
                            if(360-newGrade<=0){
                                newGrade=newGrade-360;
                            }
                            return newGrade;
                        }));

            }
            historyList.forEach(System.out::println);
            return historyDao.saveHistoryList(historyList);
        });
    }
}
