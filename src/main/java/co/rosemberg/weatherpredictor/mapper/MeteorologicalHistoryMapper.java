package co.rosemberg.weatherpredictor.mapper;

import co.rosemberg.weatherpredictor.domain.Planet;
import co.rosemberg.weatherpredictor.domain.Weather;
import co.rosemberg.weatherpredictor.entity.MeteorologicalHistory;
import co.rosemberg.weatherpredictor.entity.MeteorologicalHistoryEntry;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class MeteorologicalHistoryMapper extends Mapper<MeteorologicalHistory, co.rosemberg.weatherpredictor.domain.MeteorologicalHistory> {

    private final PlanetMapper planetMapper;

    public MeteorologicalHistoryMapper(PlanetMapper planetMapper) {
        this.planetMapper = planetMapper;
    }

    @Override
    public MeteorologicalHistory domainToEntity(co.rosemberg.weatherpredictor.domain.MeteorologicalHistory domain) {
        MeteorologicalHistory entity=new MeteorologicalHistory();
        entity.setDay(domain.getDay());
        entity.setReferencePlanet(planetMapper.domainToEntity(domain.getReferencePlanet()));
        entity.setWeather(domain.getCurrentWeather().name());
        if(domain.getPlanetWithGrades()!= null && !domain.getPlanetWithGrades().isEmpty()){
            List<MeteorologicalHistoryEntry> entryList = domain.getPlanetWithGrades().entrySet().stream().
                    map(entry->new MeteorologicalHistoryEntry(entity, planetMapper.domainToEntity(entry.getKey()), entry.getValue())).
                    collect(Collectors.toList());
            entity.setPlanetEntries(entryList);
        }
        return entity;
    }

    @Override
    public co.rosemberg.weatherpredictor.domain.MeteorologicalHistory entityToDomain(MeteorologicalHistory entity) {
        co.rosemberg.weatherpredictor.domain.MeteorologicalHistory domain= new co.rosemberg.weatherpredictor.domain.MeteorologicalHistory();
        domain.setDay(entity.getDay());
        domain.setReferencePlanet(planetMapper.entityToDomain(entity.getReferencePlanet()));
        domain.setCurrentWeather(Weather.valueOf(entity.getWeather()));
        return domain;
    }
}
