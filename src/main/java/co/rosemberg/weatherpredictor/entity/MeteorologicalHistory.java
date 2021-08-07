package co.rosemberg.weatherpredictor.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="meteorological_history")
public class MeteorologicalHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_history")
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name ="name_planet", nullable = false)
    private Planet referencePlanet;

    private Integer day;

    private String weather;

    @OneToMany(mappedBy = "history")
    private List<MeteorologicalHistoryEntry> planetEntries;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Planet getReferencePlanet() {
        return referencePlanet;
    }

    public void setReferencePlanet(Planet referencePlanet) {
        this.referencePlanet = referencePlanet;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public List<MeteorologicalHistoryEntry> getPlanetEntries() {
        return planetEntries;
    }

    public void setPlanetEntries(List<MeteorologicalHistoryEntry> planetEntries) {
        this.planetEntries = planetEntries;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }
}
