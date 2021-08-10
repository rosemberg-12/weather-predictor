package co.rosemberg.weatherpredictor.component;

import co.rosemberg.weatherpredictor.domain.Rotation;
import co.rosemberg.weatherpredictor.entity.Planet;
import co.rosemberg.weatherpredictor.repository.PlanetRepository;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

@Component
public class OnApplicationStartUp {

    private PlanetRepository repository;


    public OnApplicationStartUp(PlanetRepository repository) {
        this.repository = repository;
    }


   @EventListener
   public void onApplicationEvent(ContextRefreshedEvent event) {

       if (StreamSupport.stream(repository.findAll().spliterator(), false).count() <= 0) {
           List<Planet> planetsToInsert=new ArrayList<>();
           planetsToInsert.add(new Planet("ferengi",500, 1,Rotation.RIGHT.name()));
           planetsToInsert.add(new Planet("betasoide",2000, 3,Rotation.RIGHT.name()));
           planetsToInsert.add(new Planet("vulcano",1000, 5,Rotation.LEFT.name()));
           repository.saveAll(planetsToInsert);
       }else{
           System.out.println("No load data");
       }
   }

}