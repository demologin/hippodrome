import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.internal.stubbing.BaseStubbing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mockStatic;

public class HorseTest {

    @Test
    public void nameNullException() {
        assertThrows(IllegalArgumentException.class, () -> new Horse(null, 1.0, 1.0));

    }

    @Test
    public  void nameNullMessage() {
        try {
            new Horse(null, 1.0, 1.0);
        } catch (IllegalArgumentException e) {
            assertEquals("Name cannot be null.", e.getMessage());
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "\n", "\t", "   ", "\f"})
    public  void nameParamException(String name) {
        assertThrows(IllegalArgumentException.class, () -> new Horse(name, 1.0, 1.0));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "\n", "\t", "   ", "\f"})
    public void nameParamENullMessage(String name) {
        try {
            new Horse(name, 1.0, 1.0);
        } catch (IllegalArgumentException e) {
            assertEquals("Name cannot be blank.", e.getMessage());
        }
    }

    @Test
    public  void speedIsNegativeException() {
        assertThrows(IllegalArgumentException.class, () -> new Horse("test", -1.0, 1.0));
    }

    @Test
    public void speedIsNegativeMessage() {
        try {
            new Horse("test", -1.0, 1.0);
        } catch (IllegalArgumentException e) {
            assertEquals("Speed cannot be negative.", e.getMessage());
        }
    }

    @Test
    public  void distanceIsNegativeException() {
        assertThrows(IllegalArgumentException.class, () -> new Horse("test", 1.0, -1.0));
    }

    @Test
    public  void distanceIsNegativeMessage() {
        try {
            new Horse("test", 1.0, -1.0);
        } catch (IllegalArgumentException e) {
            assertEquals("Distance cannot be negative.", e.getMessage());
        }
    }

    @Test
    public void getName() {
        Horse horse = new Horse("test", 1.0, 1.0);
        assertEquals("test", horse.getName());
    }

    @Test
    public void getSpeed() {
        Horse horse = new Horse("test", 2.0, 1.0);
        assertEquals(2.0, horse.getSpeed());
    }

    @Test
    public  void getDistance() {
        Horse horse = new Horse("test", 2.0, 1.0);
        assertEquals(1.0, horse.getDistance());
    }

    @Test
    public  void getDistanceZero() {
        Horse horse = new Horse("test", 2.0);
        assertEquals(0, horse.getDistance());
    }

    @Test
    public void moveGetRandom() {
        try (MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)) {
            new Horse("test", 2.0, 1.0).move();
            mockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.2, 0.5, 0.9})
    public  void moveDistance(double random) {
        double speed = 2.0;
        double initialDistance = 10.0;
        try (MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)) {
            Horse horse = new Horse("Test", speed, initialDistance);
            mockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9))
                    .thenReturn(random);
            horse.move();
            double expected = initialDistance + speed * random;
            assertEquals(expected, horse.getDistance());
        }
        }
}
