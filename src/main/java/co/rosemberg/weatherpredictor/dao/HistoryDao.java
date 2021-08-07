package co.rosemberg.weatherpredictor.dao;

import co.rosemberg.weatherpredictor.domain.MeteorologicalHistory;
import co.rosemberg.weatherpredictor.mapper.MeteorologicalHistoryMapper;
import co.rosemberg.weatherpredictor.repository.MeteorologicalHistoryEntryRepository;
import co.rosemberg.weatherpredictor.repository.MeteorologicalHistoryRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HistoryDao {

    private final MeteorologicalHistoryRepository historyRepository;
    private final MeteorologicalHistoryEntryRepository historyEntryRepository;
    private final MeteorologicalHistoryMapper historyMapper;

    public HistoryDao(MeteorologicalHistoryRepository historyRepository, MeteorologicalHistoryEntryRepository historyEntryRepository, MeteorologicalHistoryMapper historyMapper) {
        this.historyRepository = historyRepository;
        this.historyEntryRepository = historyEntryRepository;
        this.historyMapper = historyMapper;
    }

    public MeteorologicalHistory getHistoryByPlanetNameAndDay(String planetName, Integer day){
        return historyRepository.findMeteorologicalHistoryByReferencePlanet_NameAndAndDay(planetName,day).
                map(historyMapper::entityToDomain).orElse(null);
    }

    public void deleteHistory(){
        historyEntryRepository.deleteAll();
        historyRepository.deleteAll();
    }


    public boolean saveHistoryList(List<MeteorologicalHistory> history){
        return true;
    }
}
