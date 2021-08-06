package co.rosemberg.weatherpredictor.entity;

import javax.persistence.*;

@Entity
@Table(name="meteorological_history_entry")
public class MeteorologicalHistoryEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name= "id_history", insertable = false, updatable = false)
    private MeteorologicalHistory history;

    @ManyToOne
    @JoinColumn(name= "name", insertable = false, updatable = false)
    private Planet planet;

    public MeteorologicalHistoryEntry(MeteorologicalHistory history, Planet planet, Integer grade) {
        this.history = history;
        this.planet = planet;
        this.grade = grade;
    }

    private Integer grade;

    public Planet getPlanet() {
        return planet;
    }

    public void setPlanet(Planet planet) {
        this.planet = planet;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }
}
