package co.rosemberg.weatherpredictor.dto;

import co.rosemberg.weatherpredictor.domain.Planet;

import java.util.List;

public class PlanetListResponse {

    private List<Planet> planets;

    public PlanetListResponse(List<Planet> planets) {
        this.planets = planets;
    }

    public List<Planet> getPlanets() {
        return planets;
    }

    public void setPlanets(List<Planet> planets) {
        this.planets = planets;
    }
}
