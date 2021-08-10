# Weather Predictor
Weather predictor API that provides the weather information of the civilizations registered in the system.
The information provides by the API is generated based on a simulation made by the system, to taking in account the parameters of each civilization and the parameter specified in the system (like number of year to simulate and the initial position of each planet in the galaxy)


## Requirements

For building and running the application you need:

- [JDK 1.11](https://openjdk.java.net/projects/jdk/11/l)
- Maven (Included as wrapper)

## Running the application locally

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `com.platzi.market.PlatziMarketApplication.java` class from your IDE.

Alternatively you can use gradle wrapper to run the application

```shell
gradlew runJar
```

## License
[GPL](https://openjdk.java.net/legal/gplv2+ce.html)