import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockStatic;

class HorseTest {
    @Test
    void nullHorseNameException() {
        String nullName = null;
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Horse(nullName, 1, 1));
    }

    @Test
    void nullHorseNameExceptionMessage() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () ->
                new Horse(null, 1, 1)
        );
        String expectedMessage = "Name cannot be null.";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @ParameterizedTest
    @ValueSource(strings = {" ", "", "\n", "\r", "\t"})
    void blankHorseNameException(String name) {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                new Horse(name, 1, 1));
    }

    @ParameterizedTest
    @ValueSource(strings = {" ", "", "\n", "\r", "\t"})
    void blankHorseNameExceptionMessage(String name) {
        Throwable exception = assertThrows(IllegalArgumentException.class, () ->
            new Horse(name, 1, 1)
        );
        String expectedMessage = "Name cannot be blank.";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void negativeSpeedException() {
        double negativeValue = -1;
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Horse("Name", negativeValue, 1));
    }

    @Test
    void negativeSpeedExceptionMessage() {
        double negativeValue = -1;
        Throwable exception = assertThrows(IllegalArgumentException.class, () ->
                new Horse("Name", negativeValue, 1)
        );
        String expectedMessage = "Speed cannot be negative.";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void negativeDistanceException() {
        double negativeValue = -1;
        Assertions.assertThrows(IllegalArgumentException.class, () ->
            new Horse("Name", 1, negativeValue)
        );
    }

    @Test
    void negativeDistanceExceptionMessage() {
        double negativeValue = -1;
        Throwable exception = assertThrows(IllegalArgumentException.class, () ->
            new Horse("Name", 1, negativeValue)
        );
        String expectedMessage = "Distance cannot be negative.";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void getName() {
        String testName = "Name";
        Horse horse = new Horse(testName, 1, 1);
        assertEquals(testName, horse.getName());
    }

    @Test
    void getSpeed() {
        Double testSpeed = 1.0;
        Horse horse = new Horse("Name", testSpeed, 1);
        assertEquals(testSpeed, horse.getSpeed());
    }

    @Test
    void getDistance() {
        Double testDistance = 1.0;
        Horse horse = new Horse("Name", 1, testDistance);
        assertEquals(testDistance, horse.getDistance());
    }

    @Test
    void getDistanceIfEmptyArgument() {
        Horse horse = new Horse("Name", 1);
        assertEquals(0, horse.getDistance());
    }

    @Test
    void invocationMethodRandomDouble() {
        try (MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)) {
            Horse horse = new Horse("Name", 1, 1);
            horse.move();
            mockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.4, 0.5, 0.6})
    void getRandomDoubleMethod(double arg) {
        try (MockedStatic<Horse> mockObject =  mockStatic(Horse.class)) {
            mockObject.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(arg);
            Horse horse = new Horse("Name", 1, 1);
            Double probMove = horse.getDistance() + horse.getSpeed() * Horse.getRandomDouble(0.2, 0.9);
            horse.move();
            assertEquals(probMove, horse.getDistance());
        }
    }
}