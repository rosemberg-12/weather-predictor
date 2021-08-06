package co.rosemberg.weatherpredictor.configuration;

import co.rosemberg.weatherpredictor.component.WeatherConditions.DroughtCondition;
import co.rosemberg.weatherpredictor.component.WeatherConditions.OptimalCondition;
import co.rosemberg.weatherpredictor.component.WeatherConditions.RainCondition;
import co.rosemberg.weatherpredictor.component.WeatherConditions.WeatherCondition;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@org.springframework.context.annotation.Configuration
public class Configuration {

    @Bean
    public List<WeatherCondition> getWeatherConditions(){
        return Stream.of(new DroughtCondition(), new RainCondition(), new OptimalCondition()).collect(Collectors.toList());
    }

}
