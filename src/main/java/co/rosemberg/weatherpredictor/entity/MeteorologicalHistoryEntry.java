package co.rosemberg.weatherpredictor.entity;

import javax.persistence.*;

@Entity
@Table(name="meteorological_history_entry")
public class MeteorologicalHistoryEntry {

    @Id
    @Column(name="id_history_entry")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name= "id_history", insertable = false, updatable = false )
    private MeteorologicalHistory history;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name= "name_planet", insertable = false, updatable = false)
    private Planet planet;

    private Integer grade;

    public MeteorologicalHistoryEntry() {

    }

    public MeteorologicalHistoryEntry(MeteorologicalHistory history, Planet planet, Integer grade) {
        this.history = history;
        this.planet = planet;
        this.grade = grade;
    }


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
