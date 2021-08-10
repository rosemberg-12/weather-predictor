package co.rosemberg.weatherpredictor.configuration;

import co.rosemberg.weatherpredictor.component.PolygonOperator;
import co.rosemberg.weatherpredictor.component.WeatherConditions.DroughtCondition;
import co.rosemberg.weatherpredictor.component.WeatherConditions.OptimalCondition;
import co.rosemberg.weatherpredictor.component.WeatherConditions.RainCondition;
import co.rosemberg.weatherpredictor.component.WeatherConditions.WeatherCondition;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@org.springframework.context.annotation.Configuration
@EnableSwagger2
public class Configuration {

    @Bean
    public List<WeatherCondition> getWeatherConditions(){
        PolygonOperator operator= new PolygonOperator();
        return Stream.of(new DroughtCondition(), new OptimalCondition(operator), new RainCondition(operator)).collect(Collectors.toList());
    }

    @Bean
    public Docket apiDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("co.rosemberg.weatherpredictor.rest"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(getApiInfo());
    }

    private ApiInfo getApiInfo() {
        return new ApiInfo(
                "Weather Planet API",
                "REST API to query on the weather conditions of the registered planets",
                "1.0",
                "",
                new Contact("Rosemberg Porras", "https://rosembergporras.co", "rosember.12@gmail.com"),
                "LICENSE GPL",
                "https://www.gnu.org/licenses/gpl-3.0.html",
                Collections.emptyList()
        );
    }
}
