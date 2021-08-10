package co.rosemberg.weatherpredictor.repository;

import co.rosemberg.weatherpredictor.entity.MeteorologicalHistoryEntry;
import org.springframework.data.repository.CrudRepository;

public interface MeteorologicalHistoryEntryRepository extends CrudRepository<MeteorologicalHistoryEntry, Long> {
}
