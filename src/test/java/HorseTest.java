import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class HorseTest {
    private static final String name = "Diamond";
    public static final double negativeValue = -1.0;
    private static final double speed = 1.0;
    private static final double distance = 100.0;
    private static final Horse horse = new Horse(name, speed, distance);

    static Stream<String> stringSpaceArgs() {
        return Stream.of("", " ", "\n", "\t","\r", "\f", "\r\n");
    }

    @Test
    void whenFirstParameterIsNullThenGetIAE() {
        assertThrows(IllegalArgumentException.class, () -> new Horse(null, speed, distance));
    }

    @Test
    void whenFirstParameterIsNullThenGetExceptionMessage() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Horse(null, speed, distance));
        String actualMessage = exception.getMessage();
        String expectedMessage = "Name cannot be null.";
        assertEquals(expectedMessage, actualMessage);
    }

    @ParameterizedTest
    @MethodSource("stringSpaceArgs")
    void whenFirstParameterIsIncorrectThenGetIAE(String strings) {
        assertThrows(IllegalArgumentException.class, () -> new Horse(strings, speed, distance));
    }

    @ParameterizedTest
    @MethodSource("stringSpaceArgs")
    void whenFirstParameterIsIncorrectThenGetMessage(String strings) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Horse(strings, speed, distance));
        String actualMessage = exception.getMessage();
        String expectedMessage = "Name cannot be blank.";
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void whenSecondParameterIsNegativeThenGetIAE() {
        assertThrows(IllegalArgumentException.class, () -> new Horse(name, negativeValue, distance));
    }

    @Test
    void whenSecondParameterIsNegativeThenGetMessage() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Horse(name, negativeValue, distance));
        String actualMessage = exception.getMessage();
        String expectedMessage = "Speed cannot be negative.";
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void whenThirdParameterIsNegativeThenGetIAE() {
        assertThrows(IllegalArgumentException.class, () -> new Horse(name, speed, negativeValue));
    }

    @Test
    void whenThirdParameterIsNegativeThenGetMessage() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Horse(name, speed, negativeValue));
        String actualMessage = exception.getMessage();
        String expectedMessage = "Distance cannot be negative.";
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void getName() {
        String expectedName = name;
        String actualName = horse.getName();
        assertEquals(expectedName, actualName);
    }

    @Test
    void getSpeed() {
        double expectedSpeed = speed;
        double actualSpeed = horse.getSpeed();
        assertEquals(expectedSpeed, actualSpeed);
    }

    @Test
    void getDistance() {
        double expectedDistance = distance;
        double actualDistance = horse.getDistance();
        assertEquals(expectedDistance, actualDistance);
    }

    @Test
    void WhenConstructorWithTwoParametersThenGetDefaultDistance() {
        Horse horseWithDefaultDistance = new Horse(name, speed);
        double expectedDistance = 0.0;
        double actualDistance = horseWithDefaultDistance.getDistance();
        assertEquals(expectedDistance, actualDistance);
    }

    @Test
    void WhenGetRandomHasNumbers() {
        try (MockedStatic<Horse> horseMockedStatic = Mockito.mockStatic(Horse.class)) {
            horse.move();
            horseMockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }

    @ParameterizedTest
    @CsvSource({
            "100.0, 1.0"
    })
    void WhenGetRandomThenCheckMethodFormula(double distance, double speed) {
        try (MockedStatic<Horse> horseMockedStatic = Mockito.mockStatic(Horse.class)) {
            horseMockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(0.5);
            horse.move();
            double expectedValue = distance + speed * 0.5;
            double actualValue = distance + speed * Horse.getRandomDouble(0.2, 0.9);
            assertEquals(expectedValue, actualValue);
        }


    }


}