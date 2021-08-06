package co.rosemberg.weatherpredictor.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlanetController {

    @GetMapping("/hello")
    public String greeting(){
        return "hello world";
    }
}
