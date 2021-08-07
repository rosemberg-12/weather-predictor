package co.rosemberg.weatherpredictor.service;

import co.rosemberg.weatherpredictor.domain.MeteorologicalHistory;
import co.rosemberg.weatherpredictor.domain.Planet;
import co.rosemberg.weatherpredictor.mapper.MeteorologicalHistoryMapper;
import co.rosemberg.weatherpredictor.mapper.PlanetMapper;
import co.rosemberg.weatherpredictor.repository.MeteorologicalHistoryRepository;
import co.rosemberg.weatherpredictor.repository.PlanetRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class PlanetService {

    private final PlanetRepository planetRepository;
    private final MeteorologicalHistoryRepository historyRepository;
    private final PlanetMapper planetMapper;
    private final MeteorologicalHistoryMapper historyMapper;


    public PlanetService(PlanetRepository planetRepository, PlanetMapper planetMapper, MeteorologicalHistoryRepository historyRepository,
                         MeteorologicalHistoryMapper historyMapper){
        this.planetRepository=planetRepository;
        this.planetMapper=planetMapper;
        this.historyRepository=historyRepository;
        this.historyMapper=historyMapper;
    }

    public boolean startWeatherSimulation(){
        return true;
    }

    public MeteorologicalHistory getWeatherCondition(Integer day, String planetName){
        MeteorologicalHistory history=null;
        Planet planet=this.getPlanet(planetName);
        if(planet!=null && day!=null && day>0){
            history=historyRepository.findMeteorologicalHistoryByReferencePlanet_NameAndAndDay(planetName,day).
                    map(historyMapper::entityToDomain).orElse(null);
        }

        return null;
    }

    public Planet getPlanet(String name){
        Planet planetToReturn=null;
        if(StringUtils.hasText(name)){
            planetToReturn=planetRepository.findById(name).map(planetMapper::entityToDomain).orElse(null);
        }
        return planetToReturn;
    }

    public List<Planet> getPlanets(){
        return Optional.of(planetRepository.findAll()).map(Iterable::spliterator).
                map(split->StreamSupport.stream(split,false).map(planetMapper::entityToDomain).
                        collect(Collectors.toList())).
                orElse(null);
    }


}
