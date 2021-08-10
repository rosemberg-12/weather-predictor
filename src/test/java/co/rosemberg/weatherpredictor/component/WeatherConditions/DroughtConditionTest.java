package co.rosemberg.weatherpredictor.component.WeatherConditions;

import co.rosemberg.weatherpredictor.domain.MeteorologicalHistory;
import co.rosemberg.weatherpredictor.fixture.MeteorologicalHistoryFixture;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;

@RunWith(Parameterized.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DroughtConditionTest {

    private DroughtCondition droughtCondition;

    @BeforeAll
    void setupTest(){
        this.droughtCondition= new DroughtCondition();
    }

    @ParameterizedTest
    @MethodSource("getAlignedHistories")
    void testDroughtCase_success(MeteorologicalHistory history){
        assertTrue(droughtCondition.applyValidation(history));
    }

    @ParameterizedTest
    @MethodSource("getNotAlignedHistories")
    void testDroughtCase_fail(MeteorologicalHistory history){
        assertFalse(droughtCondition.applyValidation(history));
    }

    @Parameterized.Parameters
    public static Stream<MeteorologicalHistory> getAlignedHistories(){
        return MeteorologicalHistoryFixture.getHistoryListWithAlignedGradesRespectToSun().stream();
    }

    @Parameterized.Parameters
    public static Stream<MeteorologicalHistory> getNotAlignedHistories(){
        return Stream.concat(Stream.concat(MeteorologicalHistoryFixture.getHistoryListWithAlignedGradesNotRespectToSun().stream(),
                MeteorologicalHistoryFixture.getHistoryListTriangleWayAndSunInCenter().stream()),
                MeteorologicalHistoryFixture.getHistoryListTriangleWayAndSunNotInCenter().stream());
    }
}
