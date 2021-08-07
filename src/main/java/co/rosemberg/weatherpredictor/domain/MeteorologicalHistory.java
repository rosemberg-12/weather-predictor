package co.rosemberg.weatherpredictor.domain;

import java.util.Map;

public class MeteorologicalHistory {

    private Planet referencePlanet;
    private Map<Planet, Integer> planetWithGrades;
    private Weather currentWeather;
    private Integer day;

    public MeteorologicalHistory(){}

    public MeteorologicalHistory(Planet referencePlanet, Map<Planet, Integer> planetWithGrades, Weather currentWeather, Integer day) {
        this.referencePlanet = referencePlanet;
        this.planetWithGrades = planetWithGrades;
        this.currentWeather = currentWeather;
        this.day = day;
    }

    public Planet getReferencePlanet() {
        return referencePlanet;
    }

    public void setReferencePlanet(Planet referencePlanet) {
        this.referencePlanet = referencePlanet;
    }

    public Map<Planet, Integer> getPlanetWithGrades() {
        return planetWithGrades;
    }

    public void setPlanetWithGrades(Map<Planet, Integer> planetWithGrades) {
        this.planetWithGrades = planetWithGrades;
    }

    public Weather getCurrentWeather() {
        return currentWeather;
    }

    public void setCurrentWeather(Weather currentWeather) {
        this.currentWeather = currentWeather;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    @Override
    public String toString() {
        return "MeteorologicalHistory{" +
                "referencePlanet=" + referencePlanet.toString() +
                ", planetWithGrades=" + planetWithGrades.toString() +
                ", currentWeather=" + currentWeather.toString() +
                ", day=" + day +
                '}';
    }
}
