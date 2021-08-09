package co.rosemberg.weatherpredictor.component.WeatherConditions;

import co.rosemberg.weatherpredictor.component.PolygonOperator;
import co.rosemberg.weatherpredictor.domain.MeteorologicalHistory;
import co.rosemberg.weatherpredictor.fixture.MeteorologicalHistoryFixture;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(Parameterized.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class RainConditionTest {

    private RainCondition condition;

    @BeforeAll
    void setupTest(){
        this.condition= new RainCondition(new PolygonOperator());
    }

    @ParameterizedTest
    @MethodSource("getAlignedHistories")
    void testRainCase_success(MeteorologicalHistory history){
        assertTrue(condition.applyValidation(history));
    }

    @ParameterizedTest
    @MethodSource("getNotAlignedHistories")
    void testRainCase_fail(MeteorologicalHistory history){
        assertFalse(condition.applyValidation(history));
    }

    @Parameterized.Parameters
    public static Stream<MeteorologicalHistory> getAlignedHistories(){
        return MeteorologicalHistoryFixture.getHistoryListTriangleWayAndSunInCenter().stream();
    }

    @Parameterized.Parameters
    public static Stream<MeteorologicalHistory> getNotAlignedHistories(){
        return Stream.concat(Stream.concat(MeteorologicalHistoryFixture.getHistoryListWithAlignedGradesRespectToSun().stream(),
                MeteorologicalHistoryFixture.getHistoryListWithAlignedGradesNotRespectToSun().stream()),
                MeteorologicalHistoryFixture.getHistoryListTriangleWayAndSunNotInCenter().stream());
    }
}
