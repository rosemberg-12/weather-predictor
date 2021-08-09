package co.rosemberg.weatherpredictor.dao;

import co.rosemberg.weatherpredictor.domain.Planet;
import co.rosemberg.weatherpredictor.mapper.PlanetMapper;
import co.rosemberg.weatherpredictor.repository.PlanetRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class PlanetDao {

    private final PlanetRepository planetRepository;
    private final PlanetMapper planetMapper;

    public PlanetDao(PlanetRepository repository, PlanetMapper mapper){
        this.planetRepository=repository;
        this.planetMapper=mapper;
    }

    public Planet getPlanetByName(String name){
        return planetRepository.findById(name).map(planetMapper::entityToDomain).orElse(null);
    }

    public List<Planet> getAllPlanets(){
        return Optional.of(planetRepository.findAll()).map(Iterable::spliterator).
                map(split-> StreamSupport.stream(split,false).map(planetMapper::entityToDomain).
                        collect(Collectors.toList())).
                orElse(null);
    }

}
