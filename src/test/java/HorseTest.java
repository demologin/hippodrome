import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockStatic;

class HorseTest {
    @Test
    @DisplayName("IllegalArgumentException if the first parameter is null")
    void whenFirstParamIsNullThrowIAE() {
        String nullName = null;
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                new Horse(nullName, 1, 1));
    }

    @Test
    @DisplayName("Message if the first parameter is null")
    void whenFirstParamIsNullThenExceptionMessage() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () ->
                new Horse(null, 1, 1)
        );
        String expectedMessage = "Name cannot be null.";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @ParameterizedTest
    @ValueSource(strings = {" ", "", "\n", "\r", "\t"})
    @DisplayName("IllegalArgumentException if the first parameter is blank")
    void whenFirstParamIsBlankThrowIAE(String name) {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                new Horse(name, 1, 1));
    }

    @ParameterizedTest
    @ValueSource(strings = {" ", "", "\n", "\r", "\t"})
    @DisplayName("Message if the first parameter is blank")
    void whenFirstParamIsBlankThenExceptionMessage(String name) {
        Throwable exception = assertThrows(IllegalArgumentException.class, () ->
                new Horse(name, 1, 1)
        );
        String expectedMessage = "Name cannot be blank.";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    @DisplayName("IllegalArgumentException if the second parameter negative")
    void whenSecondParamNegativeThrowIAE() {
        double negativeValue = -1;
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Horse("Name", negativeValue, 1));
    }

    @Test
    @DisplayName("Message if the second parameter negative")
    void whenSecondParamNegativeThenExceptionMessage() {
        double negativeValue = -1;
        Throwable exception = assertThrows(IllegalArgumentException.class, () ->
                new Horse("Name", negativeValue, 1)
        );
        String expectedMessage = "Speed cannot be negative.";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    @DisplayName("IllegalArgumentException if the third parameter negative")
    void whenThirdParamNegativeThrowIAE() {
        double negativeValue = -1;
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                new Horse("Name", 1, negativeValue)
        );
    }

    @Test
    @DisplayName("Message if the third parameter negative")
    void whenThirdParamNegativeThenExceptionMessage() {
        double negativeValue = -1;
        Throwable exception = assertThrows(IllegalArgumentException.class, () ->
                new Horse("Name", 1, negativeValue)
        );
        String expectedMessage = "Distance cannot be negative.";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    @DisplayName("getName test")
    void getName() {
        String testName = "Name";
        Horse horse = new Horse(testName, 1, 1);
        assertEquals(testName, horse.getName());
    }

    @Test
    @DisplayName("getSpeed test")
    void getSpeed() {
        Double testSpeed = 1.0;
        Horse horse = new Horse("Name", testSpeed, 1);
        assertEquals(testSpeed, horse.getSpeed());
    }

    @Test
    @DisplayName("getDistance test")
    void getDistance() {
        Double testDistance = 1.0;
        Horse horse = new Horse("Name", 1, testDistance);
        assertEquals(testDistance, horse.getDistance());
    }

    @Test
    @DisplayName("If the third parameter is empty, set it to 0")
    void WhenParamDistanceIsEmptyAssignValueZero() {
        Horse horse = new Horse("Name", 1);
        assertEquals(0, horse.getDistance());
    }

    @Test
    @DisplayName("checking the method call RandomDouble")
    void invocationMethodRandomDouble() {
        try (MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)) {
            Horse horse = new Horse("Name", 1, 1);
            horse.move();
            mockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.4, 0.5, 0.6})
    @DisplayName("RandomDouble test")
    void getRandomDouble(double arg) {
        try (MockedStatic<Horse> mockObject = mockStatic(Horse.class)) {
            mockObject.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(arg);
            Horse horse = new Horse("Name", 1, 1);
            Double moveValue = horse.getDistance() + horse.getSpeed() * Horse.getRandomDouble(0.2, 0.9);
            horse.move();
            assertEquals(moveValue, horse.getDistance());
        }
    }
}