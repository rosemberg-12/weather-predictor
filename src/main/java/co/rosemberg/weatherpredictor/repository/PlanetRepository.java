package co.rosemberg.weatherpredictor.repository;

import co.rosemberg.weatherpredictor.entity.Planet;
import org.springframework.data.repository.CrudRepository;

public interface PlanetRepository extends CrudRepository<Planet, String> {
}
