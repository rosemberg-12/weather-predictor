package co.rosemberg.weatherpredictor.domain;

public class Planet {

    private String name;
    private Integer distanceFromSun;
    private Integer angularVelocity;

    public Planet(){}

    public Planet(String name, Integer distanceFromSun, Integer angularVelocity) {
        this.name = name;
        this.distanceFromSun = distanceFromSun;
        this.angularVelocity = angularVelocity;
    }

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
