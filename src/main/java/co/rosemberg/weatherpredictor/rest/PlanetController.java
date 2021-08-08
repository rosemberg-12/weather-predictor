package co.rosemberg.weatherpredictor.rest;

import co.rosemberg.weatherpredictor.domain.Planet;
import co.rosemberg.weatherpredictor.dto.PlanetListResponse;
import co.rosemberg.weatherpredictor.dto.WeatherResponse;
import co.rosemberg.weatherpredictor.service.PlanetService;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("planeta")
public class PlanetController {

    private final PlanetService planetService;

    public PlanetController(PlanetService service){
        this.planetService=service;
    }

    @GetMapping("/{planetName}")
    public ResponseEntity<Planet> getPlanet(@PathVariable String planetName){
        return Optional.ofNullable(planetService.getPlanet(planetName)).
                map(ResponseEntity::ok).
                orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/")
    public ResponseEntity<PlanetListResponse> getPlanets(){
        return Optional.ofNullable(planetService.getPlanets()).
                map(PlanetListResponse::new).
                map(ResponseEntity::ok).
                orElse(ResponseEntity.notFound().build());
    }


    @GetMapping("/{planetName}/clima/{day}")
    public ResponseEntity<WeatherResponse> getWeather(@PathVariable String planetName, @PathVariable Integer day){
        return Optional.ofNullable(planetService.getWeatherCondition(day, planetName)).
                map(meteorologicalHistory -> new WeatherResponse(day, meteorologicalHistory.getCurrentWeather().name())).
                map(ResponseEntity::ok).
                orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/")
    @Async
    public ResponseEntity populatePrediction(){
        if(planetService.startWeatherSimulation()){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.internalServerError().build();
    }
}
