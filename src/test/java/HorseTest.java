import org.junit.jupiter.api.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class HorseTest {

    private MockedStatic<Horse> horseMockedStatic;

    @BeforeEach
    void setUp() {
        horseMockedStatic = Mockito.mockStatic(Horse.class);
    }

    @AfterEach
    void tearDown() {
        horseMockedStatic.close();
    }

    @Test
    public void nullHorseNameTest() {
        assertThrows(IllegalArgumentException.class, () -> new Horse(null, 5.5));
    }

    @Test
    public void nullHorseNameMessage() {
        Exception checkException = assertThrows(IllegalArgumentException.class, () -> new Horse(null, 5.5));
        Assertions.assertEquals("Name cannot be null.", checkException.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {" ", "", "\n", "\r", "\t"})
    public void nullOrEmptyHorseName(String argument) {
        assertThrows(IllegalArgumentException.class, () -> new Horse(argument, 5.5));
    }

    @Test
    public void emptyHorseNameMessage() {
        Exception checkException = assertThrows(IllegalArgumentException.class, () -> new Horse("", 5.5));
        Assertions.assertEquals("Name cannot be blank.", checkException.getMessage());
    }

    @Test
    public void negativeHorseSpeed() {
        assertThrows(IllegalArgumentException.class, () -> new Horse("Бублик", -5.5));
    }

    @Test
    public void negativeHorseSpeedMessage() {
        Exception checkException = assertThrows(IllegalArgumentException.class, () -> new Horse("Бублик", -5.5));
        Assertions.assertEquals("Speed cannot be negative.", checkException.getMessage());
    }

    @Test
    public void negativeHorseDistance() {
        assertThrows(IllegalArgumentException.class, () -> new Horse("Бублик", 5.5, -5.5));
    }

    @Test
    public void negativeHorseDistanceMessage() {
        Exception checkException = assertThrows(IllegalArgumentException.class, () -> new Horse("Бублик", 5.5, -5.5));
        Assertions.assertEquals("Distance cannot be negative.", checkException.getMessage());
    }

    @Test
    public void getHorseName() {
        String name = "Бублик";
        Horse horse = new Horse(name, 5.5);
        Assertions.assertEquals(name, horse.getName());
    }

    @Test
    public void getHorseSpeed() {
        double speed = 5.5;
        Horse horse = new Horse("Бублик", speed);
        Assertions.assertEquals(speed, horse.getSpeed());
    }

    @Test
    public void getHorseDistance() {
        double distance = 5.5;
        Horse horse = new Horse("Бублик", 5.5, distance);
        Assertions.assertEquals(distance, horse.getDistance());
    }

    @Test
    public void moveTestUsingRandom() {
        horseMockedStatic.when(() -> Horse.getRandomDouble(Mockito.anyDouble(), Mockito.anyDouble())).thenReturn(0.5);
        Assertions.assertEquals(Horse.getRandomDouble(Mockito.anyDouble(), Mockito.anyDouble()), 0.5);
    }

}