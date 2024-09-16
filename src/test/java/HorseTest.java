import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class HorseTest {
    @Test
    void whenFirstArgumentIsNull_ThenThrowIllegalArgumentException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new Horse(null, 10, 100));

        String expected = "Name cannot be null.";
        String actual = exception.getMessage();

        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "   ", "\t"})
    void whenFirstArgumentIsBlank_ThenThrowIllegalArgumentException(String arg) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new Horse(arg, 10, 100));

        String expected = "Name cannot be blank.";
        String actual = exception.getMessage();

        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @ValueSource(doubles = {-1, -2, -54, Integer.MIN_VALUE, Double.NEGATIVE_INFINITY})
    void whenSecondArgumentIsNegative_ThenThrowIllegalArgumentException(double arg) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new Horse("Horse", arg, 100));

        String expected = "Speed cannot be negative.";
        String actual = exception.getMessage();

        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @ValueSource(doubles = {-1, -2, -54, Integer.MIN_VALUE, Double.NEGATIVE_INFINITY})
    void whenThirdArgumentIsNegative_ThenThrowIllegalArgumentException(double arg) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new Horse("Horse", 10, arg));

        String expected = "Distance cannot be negative.";
        String actual = exception.getMessage();

        assertEquals(expected, actual);
    }

    @Test
    void getName() {
        Horse horse = new Horse("Name", 10, 100);

        String expected = "Name";
        String actual = horse.getName();

        assertEquals(expected, actual);
    }

    @Test
    void getSpeed() {
        Horse horse = new Horse("Name", 10.82, 100);

        double expected = 10.82;
        double actual = horse.getSpeed();

        assertEquals(expected, actual);
    }

    @Test
    void getDistance() {
        Horse horse = new Horse("Name", 10.82, 100.01);

        double expected = 100.01;
        double actual = horse.getDistance();

        assertEquals(expected, actual);
    }


    @Test
    void whenMove_ThenCallGetRandomDouble() {
        try (MockedStatic<Horse> mockedHorse = mockStatic(Horse.class)) {
            mockedHorse.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(0.5);
            Horse horse = new Horse("Name", 10, 100);
            horse.move();
            mockedHorse.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }

    @ParameterizedTest
    @CsvSource(value = {
            "10, 34, 0.3",
            "34, 100, 0.8",
            "4, 20, 0.25",
            "100, 1000, 0.672"
    })
    void whenMove_ThenSetCorrectDistance(double speed, double distance, double returnValue) {
        try (MockedStatic<Horse> mockedHorse = mockStatic(Horse.class)) {
            mockedHorse.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(returnValue);

            Horse horse = new Horse("Name", speed, distance);
            horse.move();

            double expectedDistance = distance + speed * returnValue;
            double actualDistance = horse.getDistance();

            assertEquals(expectedDistance, actualDistance);
        }
    }
}