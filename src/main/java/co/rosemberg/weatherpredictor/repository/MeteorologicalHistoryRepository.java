package co.rosemberg.weatherpredictor.repository;

import co.rosemberg.weatherpredictor.entity.MeteorologicalHistory;
import co.rosemberg.weatherpredictor.entity.Planet;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface MeteorologicalHistoryRepository extends CrudRepository<MeteorologicalHistory, Long> {

    Optional<MeteorologicalHistory> findMeteorologicalHistoryByReferencePlanet_NameAndAndDay(String planet, Integer day);
}
