import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;


import java.lang.reflect.Field;
import java.util.zip.CheckedOutputStream;

import static org.junit.jupiter.api.Assertions.*;

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
    public void getName() throws NoSuchFieldException, IllegalAccessException {
        Horse horse = new Horse("Zephyr", 1, 1);

        Field name = Horse.class.getDeclaredField("name");
        name.setAccessible(true);
        String nameValue = (String) name.get(horse);
        assertEquals("Zephyr", nameValue);
    }

}