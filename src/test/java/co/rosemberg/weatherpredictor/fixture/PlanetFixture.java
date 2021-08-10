package co.rosemberg.weatherpredictor.fixture;

import co.rosemberg.weatherpredictor.domain.Planet;
import co.rosemberg.weatherpredictor.domain.Rotation;

public class PlanetFixture {

    public static Planet getPlanet(String name, Rotation rotation, Integer angularVelocity, Integer sunDistance){
        return new Planet(name,sunDistance, angularVelocity, rotation);
    }

}
