package co.rosemberg.weatherpredictor.entity;

import javax.persistence.*;

@Entity
@Table(name="planet_statistic")
public class PlanetStatistic {

    @Id
    @Column(name="id_planet_statistic")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name= "name_planet")
    private Planet planet;

    @Column(name = "drought_periods")
    private Long droughtPeriods;

    @Column(name = "rain_periods")
    private Long rainPeriods;

    @Column(name = "max_rain_day")
    private Long maxRainDay;

    @Column(name = "optimal_periods")
    private Long optimalPeriods;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Planet getPlanet() {
        return planet;
    }

    public void setPlanet(Planet planet) {
        this.planet = planet;
    }

    public Long getDroughtPeriods() {
        return droughtPeriods;
    }

    public void setDroughtPeriods(Long droughtPeriods) {
        this.droughtPeriods = droughtPeriods;
    }

    public Long getRainPeriods() {
        return rainPeriods;
    }

    public void setRainPeriods(Long rainPeriods) {
        this.rainPeriods = rainPeriods;
    }

    public Long getMaxRainPeriods() {
        return maxRainDay;
    }

    public void setMaxRainPeriods(Long maxRainDay) {
        this.maxRainDay = maxRainDay;
    }

    public Long getOptimalPeriods() {
        return optimalPeriods;
    }

    public void setOptimalPeriods(Long optimalPeriods) {
        this.optimalPeriods = optimalPeriods;
    }
}
