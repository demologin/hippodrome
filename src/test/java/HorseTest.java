import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class HorseTest {

    @Test
    void constructorNameCannotBeNull() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Horse(null, 1.0));
        assertEquals("Name cannot be null.", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " \t\n\r\f"})
    @DisplayName("Should throw exception for empty and whitespace-only strings")
    void constructorNameCannotBeBlank(String name) {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Horse(name, 1.0));
        assertEquals("Name cannot be blank.", exception.getMessage());
    }

    @Test
    void constructorSpeedCannotBeNegative() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Horse("Horse", -1.0));
        assertEquals("Speed cannot be negative.", exception.getMessage());
    }

    @Test
    void constructorDistanceCannotBeNegative() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Horse("Horse", 10.0, -1.0));
        assertEquals("Distance cannot be negative.", exception.getMessage());
    }

    @Test
    void getName() {
        Horse horse = new Horse("Horse", 1.0);
        assertEquals("Horse", horse.getName());
    }

    @Test
    void getSpeed() {
        Horse horse = new Horse("Horse", 5.6);
        assertEquals(5.6, horse.getSpeed());
    }

    @Test
    void getDistanceWithDistanceConstructor() {
        Horse horse = new Horse("Horse", 1.0, 10.0);
        assertEquals(10.0, horse.getDistance());
    }

    @Test
    void getDistanceWithDefaultValue() {
        Horse horse = new Horse("Horse", 1.0);
        assertEquals(0.0, horse.getDistance());
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.2001, 0.59876542, 0.899999})
    void move(double randomDoubleValue) {
        try (MockedStatic<Horse> mockedHorse = Mockito.mockStatic(Horse.class)) {
            mockedHorse.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(randomDoubleValue);
            Horse horse = new Horse("Horse", 2.0, 5.0);
            horse.move();
            double expectedDistance = 5.0 + (2.0 * randomDoubleValue);
            assertEquals(expectedDistance, horse.getDistance());
            mockedHorse.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }

    @Test
    void getRandomDouble() {
    }
}