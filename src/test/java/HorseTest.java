import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class HorseTest {

    @Test
    public void testConstructorWithNullName() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new Horse(null, 2));
        assertEquals("Name cannot be null.", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\t", "\n", "\r", "\f"})
    public void testConstructorWithEmptyOrBlankName(String name) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new Horse(name, 2));
        assertEquals("Name cannot be blank.", exception.getMessage());
    }

    @Test
    public void testConstructorWithNegativeSpeed() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new Horse("name", -3));
        assertEquals("Speed cannot be negative.", exception.getMessage());
    }

    @Test
    public void testConstructorWithNegativeDistance() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new Horse("name", 2, -10));
        assertEquals("Distance cannot be negative.", exception.getMessage());
    }
    @Test
    public void testGetName() {
        String expectedName = "Bucephalus"; // ожидаемое имя
        Horse horse = new Horse(expectedName, 2); // создаем экземпляр лошади с ожидаемым именем
        String actualName = horse.getName(); // получаем имя лошади с помощью getName
        assertEquals(expectedName, actualName); // сравниваем ожидаемое и получаемое
    }

    @Test
    public void testGetSpeed() {
        double expectedSpeed = 3;
        Horse horse = new Horse("Horse", expectedSpeed);
        double actualSpeed = horse.getSpeed();
        assertEquals(expectedSpeed, actualSpeed);
    }

    @Test
    public void testGetDistanceWithThreeParameters() {
        double expectedDistance = 10;
        Horse horse = new Horse("Horse", 2, expectedDistance);
        double actualDistance = horse.getDistance();
        assertEquals(expectedDistance, actualDistance);
    }
    @Test
    public void testGetDistanceWithTwoParameters() {
        Horse horse = new Horse("Horse", 2);
        double actualDistance = horse.getDistance();
        assertEquals(0L, actualDistance);
    }

    @Test
    void move() {
    }

    @Test
    void getRandomDouble() {
    }
}