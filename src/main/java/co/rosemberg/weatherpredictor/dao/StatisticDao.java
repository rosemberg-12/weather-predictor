package co.rosemberg.weatherpredictor.dao;

import co.rosemberg.weatherpredictor.domain.Planet;
import co.rosemberg.weatherpredictor.entity.PlanetStatistic;
import co.rosemberg.weatherpredictor.repository.PlanetRepository;
import co.rosemberg.weatherpredictor.repository.PlanetStatisticRepository;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class StatisticDao {

    private final PlanetStatisticRepository repository;
    private final PlanetRepository planetRepository;

    public StatisticDao(PlanetStatisticRepository repository, PlanetRepository planetRepository) {
        this.repository = repository;
        this.planetRepository= planetRepository;
    }

    @Transactional
    public void insertPlanetStatics(Planet planet, Integer droughtPeriods, Integer rainPeriods, Integer maxRainDay, Integer optimalPeriods){
        PlanetStatistic statistic= new PlanetStatistic();
        statistic.setPlanet(planetRepository.findById(planet.getName()).get());
        statistic.setDroughtPeriods((long)droughtPeriods);
        statistic.setRainPeriods((long)rainPeriods);
        statistic.setMaxRainPeriods((long)maxRainDay);
        statistic.setOptimalPeriods((long)optimalPeriods);
        repository.save(statistic);
    }

    public void deleteAll() {
        repository.deleteAll();
    }
}
