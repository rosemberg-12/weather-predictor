package co.rosemberg.weatherpredictor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class WeatherPredictorApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeatherPredictorApplication.class, args);
	}

}
