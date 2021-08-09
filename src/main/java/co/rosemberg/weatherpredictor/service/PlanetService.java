package co.rosemberg.weatherpredictor.service;

import co.rosemberg.weatherpredictor.component.WeatherSimulator;
import co.rosemberg.weatherpredictor.dao.HistoryDao;
import co.rosemberg.weatherpredictor.dao.PlanetDao;
import co.rosemberg.weatherpredictor.domain.MeteorologicalHistory;
import co.rosemberg.weatherpredictor.domain.Planet;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class PlanetService {

    private final PlanetDao planetDao;
    private final HistoryDao historyDao;
    private final WeatherSimulator simulator;

    public PlanetService(PlanetDao planetDao, HistoryDao historyDao, WeatherSimulator simulator) {
        this.planetDao = planetDao;
        this.historyDao = historyDao;
        this.simulator = simulator;
    }

    public boolean startWeatherSimulation(){
        simulator.startSimulation();
        return true;
    }

    public MeteorologicalHistory getWeatherCondition(Integer day, String planetName){
        MeteorologicalHistory history=null;
        Planet planet=this.getPlanet(planetName);
        if(planet!=null && day!=null && day>0){
            history=historyDao.getHistoryByPlanetNameAndDay(planetName,day);
        }

        return history;
    }

    public Planet getPlanet(String name){
        Planet planetToReturn=null;
        if(StringUtils.hasText(name)){
            planetToReturn=planetDao.getPlanetByName(name);
        }
        return planetToReturn;
    }

    public List<Planet> getPlanets(){
        return planetDao.getAllPlanets();
    }


}
