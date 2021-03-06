package co.rosemberg.weatherpredictor.rest;

import co.rosemberg.weatherpredictor.domain.Planet;
import co.rosemberg.weatherpredictor.dto.PlanetListResponse;
import co.rosemberg.weatherpredictor.dto.WeatherResponse;
import co.rosemberg.weatherpredictor.service.PlanetService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@Api(value="Planet API", description="Operations related with the planets registered in the system and their weather information")
@RequestMapping("planeta")
public class PlanetController {

    private final PlanetService planetService;

    public PlanetController(PlanetService service){
        this.planetService=service;
    }

    @ApiOperation(value = "Get the information of a planet registered in the system, given the name", response = Planet.class)
    @GetMapping("/{planetName}")
    public ResponseEntity<Planet> getPlanet(@PathVariable String planetName){
        return Optional.ofNullable(planetService.getPlanet(planetName)).
                map(ResponseEntity::ok).
                orElse(ResponseEntity.notFound().build());
    }

    @ApiOperation(value = "Get the information of all planets registered in the system", response = Planet.class)
    @GetMapping("/")
    public ResponseEntity<PlanetListResponse> getPlanets(){
        return Optional.ofNullable(planetService.getPlanets()).
                map(PlanetListResponse::new).
                map(ResponseEntity::ok).
                orElse(ResponseEntity.notFound().build());
    }


    @ApiOperation(value = "Get the weather information of a planet, given the name of the planet and the day to be searched", response = Planet.class)
    @GetMapping("/{planetName}/clima/{day}")
    public ResponseEntity<WeatherResponse> getWeather(@PathVariable String planetName, @PathVariable Integer day){
        return Optional.ofNullable(planetService.getWeatherCondition(day, planetName)).
                map(meteorologicalHistory -> new WeatherResponse(day, meteorologicalHistory.getCurrentWeather().name())).
                map(ResponseEntity::ok).
                orElse(ResponseEntity.notFound().build());
    }

    @ApiOperation(value = "Start the weather analysis of the planets registered in the system, base on the configuration of each planet", response = Planet.class)
    @PostMapping("/")
    @Async
    public ResponseEntity<?> populatePrediction(){
        if(planetService.startWeatherSimulation()){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.internalServerError().build();
    }
}
