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

    @ManyToOne
    @JoinColumn(name ="name", nullable = false)
    private Planet referencePlanet;

    private Integer day;

    @OneToMany(mappedBy = "history")
    private List<MeteorologicalHistoryEntry> planetEntries;

    private String weather;

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
