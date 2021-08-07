package co.rosemberg.weatherpredictor.domain;

public class Planet {

    private String name;
    private Integer distanceFromSun;
    private Integer angularVelocity;
    private Rotation rotation;

    public Planet(){}

    public Planet(String name, Integer distanceFromSun, Integer angularVelocity, Rotation rotation) {
        this.name = name;
        this.distanceFromSun = distanceFromSun;
        this.angularVelocity = angularVelocity;
        this.rotation= rotation;
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

    public Rotation getRotation() {
        return rotation;
    }

    public void setRotation(Rotation rotation) {
        this.rotation = rotation;
    }

    @Override
    public String toString() {
        return "Planet{" +
                "name='" + name + '\'' +
                '}';
    }
}
