package co.rosemberg.weatherpredictor.entity;

import javax.persistence.*;

@Entity
@Table(name="meteorological_history_entry")
public class MeteorologicalHistoryEntry {

    @Id
    @Column(name="id_history_entry")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name= "id_history")
    private MeteorologicalHistory history;

    @ManyToOne(cascade=CascadeType.MERGE)
    @JoinColumn(name= "name_planet")
    private Planet planet;

    private Double grade;

    public MeteorologicalHistoryEntry() {

    }

    public MeteorologicalHistoryEntry(MeteorologicalHistory history, Planet planet, Double grade) {
        this.history = history;
        this.planet = planet;
        this.grade = grade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MeteorologicalHistory getHistory() {
        return history;
    }

    public void setHistory(MeteorologicalHistory history) {
        this.history = history;
    }

    public Planet getPlanet() {
        return planet;
    }

    public void setPlanet(Planet planet) {
        this.planet = planet;
    }

    public Double getGrade() {
        return grade;
    }

    public void setGrade(Double grade) {
        this.grade = grade;
    }
}
