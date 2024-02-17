import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class HorseTest {

    @Test
    void ifNameIsNull_ThrowsIllegalArgException() {
        IllegalArgumentException e = Assertions.assertThrows(IllegalArgumentException.class, () -> new Horse(null, 0));
        assertEquals("Name cannot be null.", e.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\t", "\r", "\n",})
    void ifNameIsBlank_ThrowsIllegalArgException(String name) {
        IllegalArgumentException e = Assertions.assertThrows(IllegalArgumentException.class, () -> new Horse(name, 0, 0));
        assertEquals("Name cannot be blank.", e.getMessage());
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, Integer.MIN_VALUE})
    void ifSpeedIsNegative_ThrowIllegalArgException(int speed) {
        IllegalArgumentException e = Assertions.assertThrows(IllegalArgumentException.class, () -> new Horse("name", speed, 0));
        assertEquals("Speed cannot be negative.", e.getMessage());
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, Integer.MIN_VALUE})
    void ifDistanceIsNegative_ThrowIllegalArgException(int distance) {
        IllegalArgumentException e = Assertions.assertThrows(IllegalArgumentException.class, () -> new Horse("name", 0, distance));
        assertEquals("Distance cannot be negative.", e.getMessage());
    }

    @Test
    void getName() {
        Horse horse = new Horse("name", 1, 1);
        assertEquals("name", horse.getName());
    }

    @Test
    void getSpeed() {
        Horse horse = new Horse("name", 1, 1);
        assertEquals(1.0, horse.getSpeed());
    }

    @Test
    void getDistanceWithtoutParameter() {
        Horse horse = new Horse("name", 1);
        assertEquals(0.0, horse.getDistance());
    }

    @Test
    void getDistance() {
        Horse horse = new Horse("name", 1, 1);
        assertEquals(1.0, horse.getDistance());
    }

    @Test
    void moveExecutesGetRandomDoubleMethod() {
        try (MockedStatic<Horse> mockedStatic = Mockito.mockStatic(Horse.class)) {
            new Horse("name", Mockito.anyDouble(), Mockito.anyDouble()).move();
            mockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }

    @ParameterizedTest
    @ValueSource (doubles = {0.2, 0.9, 0.7})
    void moveAssignNewValueForDistance(double value) {
        try (MockedStatic<Horse> mockedStatic = Mockito.mockStatic(Horse.class)) {
            mockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(value);
            Horse horse = new Horse("name", 1, 1);
            double expectedDist = horse.getDistance() + horse.getSpeed() * value;
            horse.move();
            assertEquals(expectedDist, horse.getDistance());
        }
    }


}