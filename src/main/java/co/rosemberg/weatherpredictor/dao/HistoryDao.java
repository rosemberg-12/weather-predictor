package co.rosemberg.weatherpredictor.dao;

import co.rosemberg.weatherpredictor.domain.MeteorologicalHistory;
import co.rosemberg.weatherpredictor.domain.Planet;
import co.rosemberg.weatherpredictor.mapper.MeteorologicalHistoryMapper;
import co.rosemberg.weatherpredictor.mapper.PlanetMapper;
import co.rosemberg.weatherpredictor.repository.MeteorologicalHistoryEntryRepository;
import co.rosemberg.weatherpredictor.repository.MeteorologicalHistoryRepository;
import co.rosemberg.weatherpredictor.repository.PlanetRepository;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class HistoryDao {

    private final MeteorologicalHistoryRepository historyRepository;
    private final MeteorologicalHistoryEntryRepository historyEntryRepository;
    private final PlanetRepository planetRepository;
    private final MeteorologicalHistoryMapper historyMapper;


    public HistoryDao(MeteorologicalHistoryRepository historyRepository, MeteorologicalHistoryEntryRepository historyEntryRepository,
                      MeteorologicalHistoryMapper historyMapper, PlanetRepository planetRepository) {
        this.historyRepository = historyRepository;
        this.historyEntryRepository = historyEntryRepository;
        this.historyMapper = historyMapper;

        this.planetRepository= planetRepository;
    }

    public MeteorologicalHistory getHistoryByPlanetNameAndDay(String planetName, Integer day){
        return historyRepository.findMeteorologicalHistoryByReferencePlanet_NameAndAndDay(planetName,day).
                map(historyMapper::entityToDomain).orElse(null);
    }

    @Transactional
    public void deleteHistory(){
        historyEntryRepository.deleteAll();
        historyRepository.deleteAll();
    }

    @Transactional
    public void saveHistoryList(List<MeteorologicalHistory> history){

        Map<String, co.rosemberg.weatherpredictor.entity.Planet> cache= StreamSupport.stream(planetRepository.findAll().spliterator(), false).
                collect(Collectors.toMap(co.rosemberg.weatherpredictor.entity.Planet::getName,Function.identity()));

        List<co.rosemberg.weatherpredictor.entity.MeteorologicalHistory> entityList= history.stream().
                map(domain->historyMapper.domainToEntityFromCache(domain, cache)).collect(Collectors.toList());

        historyRepository.saveAll(entityList);
    }
}
