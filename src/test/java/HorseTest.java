import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class HorseTest {


    private Horse horse;

    @BeforeEach
    void setUp() {
        String name = "Vegas";
        double speed = 10.0;
        double distance = 100.0;
        horse = new Horse(name, speed, distance);
    }

    @Test
    @DisplayName("Ensure NullPointerException is thrown when name is null")
    void nullHorse() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Horse(null, 10.0, 100.0));
        assertEquals("Name cannot be null.", exception.getMessage());
    }

    @ParameterizedTest
    @DisplayName("Ensure IllegalArgumentException is thrown when name is blank")
    @ValueSource(strings = {"", " ", "\t", "\n", "\r", "\t\n\r", "\t  \n"})
    void emptyHorse(String name) {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Horse(name, 10.0, 100.0));
        assertEquals("Name cannot be blank.", exception.getMessage());
    }

    @Test
    @DisplayName("Ensure IllegalArgumentException is thrown when speed is negative")
    void negativeSpeedHorse() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Horse("Vegas", -10.0, 100.0));
        assertEquals("Speed cannot be negative.", exception.getMessage());
    }

    @Test
    @DisplayName("Ensure IllegalArgumentException is thrown when distance is negative")
    void negativeDistanceHorse() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Horse("Vegas", 10.0, -100.0));
        assertEquals("Distance cannot be negative.", exception.getMessage());
    }

    @Test
    @DisplayName("Ensure getName returns correct name")
    void getName() {
        assertEquals("Vegas", horse.getName());
    }

    @Test
    @DisplayName("Ensure getSpeed returns correct speed")
    void getSpeed() {
        assertEquals(10.0, horse.getSpeed());
    }

    @Test
    @DisplayName("Ensure getDistance returns correct distance")
    void getDistance() {
        assertEquals(100.0, horse.getDistance());
    }
    @Test
    @DisplayName("Ensure that a method calls a method inside a method")
    void move() {
        try (MockedStatic<Horse> mocked = Mockito.mockStatic(Horse.class)) {

            mocked.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(0.5);

            horse.move();

            mocked.verify(() -> Horse.getRandomDouble(0.2, 0.9));

            assertEquals(105, horse.getDistance());
        }

    }

}