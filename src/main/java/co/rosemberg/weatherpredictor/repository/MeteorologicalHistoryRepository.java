package co.rosemberg.weatherpredictor.repository;

import co.rosemberg.weatherpredictor.entity.MeteorologicalHistory;
import co.rosemberg.weatherpredictor.entity.Planet;
import org.springframework.data.repository.CrudRepository;

public interface MeteorologicalHistoryRepository extends CrudRepository<MeteorologicalHistory, Long> {
}
