package co.rosemberg.weatherpredictor.mapper;

import co.rosemberg.weatherpredictor.domain.Planet;
import co.rosemberg.weatherpredictor.domain.Rotation;
import org.springframework.stereotype.Component;

@Component
public class PlanetMapper extends Mapper<co.rosemberg.weatherpredictor.entity.Planet, Planet>{

    public co.rosemberg.weatherpredictor.entity.Planet domainToEntity(Planet domain){
        co.rosemberg.weatherpredictor.entity.Planet entity= new co.rosemberg.weatherpredictor.entity.Planet();
        entity.setName(domain.getName());
        entity.setAngularVelocity(domain.getAngularVelocity());
        entity.setDistanceFromSun(domain.getDistanceFromSun());
        entity.setRotation(domain.getRotation().name());
        return entity;
    }

    public Planet entityToDomain(co.rosemberg.weatherpredictor.entity.Planet entity){
        Planet domain= new Planet();
        domain.setName(entity.getName());
        domain.setAngularVelocity(entity.getAngularVelocity());
        domain.setDistanceFromSun(entity.getDistanceFromSun());
        domain.setRotation(Rotation.valueOf(entity.getRotation()));
        return domain;
    }
}
