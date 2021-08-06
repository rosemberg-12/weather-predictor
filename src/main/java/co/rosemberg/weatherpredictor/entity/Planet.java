package co.rosemberg.weatherpredictor.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="planet")
public class Planet {

    @Id
    private String name;

    @Column(name = "distance_from_sun")
    private Integer distanceFromSun;

    @Column(name = "angular_velocity")
    private Integer angularVelocity;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDistanceFromSun() {
        return distanceFromSun;
    }

    public void setDistanceFromSun(Integer distanceFromSun) {
        this.distanceFromSun = distanceFromSun;
    }

    public Integer getAngularVelocity() {
        return angularVelocity;
    }

    public void setAngularVelocity(Integer angularVelocity) {
        this.angularVelocity = angularVelocity;
    }
}
