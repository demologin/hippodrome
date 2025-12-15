import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;


import java.lang.reflect.Field;
import java.util.zip.CheckedOutputStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockStatic;

public class HorseTest {
    @Test
    public void illegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Horse(null, 3, 1));


    }

    @Test
    public void nullNameMessage() {
        try {
            new Horse(null, 3, 1);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Name cannot be null.", e.getMessage());
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "   ", "\n\n\n\n\n", "\t\t"})
    public void spaceCharactersException(String name) {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new Horse(name, 1, 1));

        assertEquals("Name cannot be blank.", e.getMessage());
    }

    @Test
    public void negativeSpeedException() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new Horse("Zephyr", -1, 1));

        assertEquals("Speed cannot be negative.", e.getMessage());
    }

    @Test
    public void negativeDistanceException() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new Horse("Zephyr", 1, -1));

        assertEquals("Distance cannot be negative.", e.getMessage());
    }

    @Test
    public void getName() {
        Horse horse = new Horse("Zephyr", 1, 1);

        assertEquals("Zephyr", horse.getName());
    }

    @Test
    public void getSpeed() {
        double speed = 790;
        Horse horse = new Horse("Zephyr", speed, 1);

        assertEquals(speed, horse.getSpeed());
    }

    @Test
    public void getDistance() {
        Horse horse = new Horse("Zephyr", 1, 576);

        assertEquals(576, horse.getDistance());
    }

    @Test
    public void moveGetRandomDouble() {
        try (MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)) {
            new Horse("Zephyr", 1, 1).move();
            mockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }

}