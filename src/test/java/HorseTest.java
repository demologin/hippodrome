
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class HorseTest {


    @Test
    void givenNullInNameWhenNewThenThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Horse(null, 1.0, 1.0));
    }

    @Test
    void givenNullInNameWhenThrowIllegalArgumentExceptionThenExceptionContainsText() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Horse(null, 1.0, 1.0));
        assertEquals("Name cannot be null.", exception.getMessage());
    }


    @ParameterizedTest
    @CsvSource({
            "'',1.0,1.0",
            "' ',1.0,1.0",
            "'\t',1.0,1.0",
            "'\n',1.0,1.0",
    })
    void givenWhitespacesCharactersInNameWhenNewThenThrowIllegalArgumentException(String name, double speed, double distance) {
        assertThrows(IllegalArgumentException.class, () -> new Horse(name, speed, distance));
    }

    @ParameterizedTest
    @CsvSource({
            "'',1.0,1.0",
            "' ',1.0,1.0",
            "'\t',1.0,1.0",
            "'\n',1.0,1.0",
    })
    void givenWhitespacesCharactersInNameWhenThrowIllegalArgumentExceptionThenExceptionContainsText(String name, double speed, double distance) {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Horse(name, speed, distance));
        assertEquals("Name cannot be blank.", exception.getMessage());
    }

    @Test
    void givenNegativeNumberInSpeedWhenNewThenThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Horse("test", -1.0, 1.0));
    }

    @Test
    void givenNegativeNumberInSpeedWhenThrowIllegalArgumentExceptionThenExceptionContainsText() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Horse("test", -1.0, 1.0));
        assertEquals("Speed cannot be negative.", exception.getMessage());
    }

    @Test
    void givenNegativeNumberInDistanceWhenNewThenThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Horse("test", 1.0, -1.0));
    }

    @Test
    void givenNegativeNumberInDistanceWhenThrowIllegalArgumentExceptionThenExceptionContainsText() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Horse("test", 1.0, -1.0));
        assertEquals("Distance cannot be negative.", exception.getMessage());
    }

    @Test
    void getName() {
        Horse horse = new Horse("test", 1.0, 1.0);
        assertEquals("test", horse.getName());
    }

    @Test
    void getSpeed() {
        Horse horse = new Horse("test", 2.0, 1.0);
        assertEquals(2.0, horse.getSpeed());
    }

    @Test
    void getDistance() {
        Horse horse = new Horse("test", 1.0, 2.0);
        assertEquals(2.0, horse.getDistance());
        Horse horseWithTwoParameters = new Horse("test", 1.0);
        assertEquals(0, horseWithTwoParameters.getDistance());
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.1, 0.2, 0.3, 0.4, 0.5, 0.6})
    void move(double distance) {
        try (MockedStatic<Horse> staticMock = Mockito.mockStatic(Horse.class)) {
            Mockito.when(Horse.getRandomDouble(0.2, 0.9)).thenReturn(distance);
            Horse horse = new Horse("test", 1.0);
            horse.move();
            staticMock.verify(() -> Horse.getRandomDouble(0.2, 0.9));
            assertEquals(horse.getSpeed() * distance, horse.getDistance());
        }
    }
}