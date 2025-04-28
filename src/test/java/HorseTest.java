import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;


import static org.junit.Assert.*;
import static org.mockito.Mockito.mockStatic;


public class HorseTest {

    @Test
    public void nullNameExceptionTest() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new Horse(null, 1, 1));
        assertEquals("Name cannot be null.", e.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "   ", "\t", "\n"})

    public void blankNameExceptionTest(String name) {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new Horse(name, 1, 1));

        assertEquals("Name cannot be blank.", e.getMessage());
    }

    @Test
    public void getNameTest() throws NoSuchFieldException, IllegalAccessException {
        Horse horse = new Horse("stick", 1, 1);

        assertEquals("stick", horse.getName());

    }

    @Test
    public void getSpeedTest(){
        double expectedSpeed = 555;
        Horse horse = new Horse("stick", expectedSpeed, 1);
        assertEquals(expectedSpeed, horse.getSpeed(), 0.001);
    }

    @Test
    public void getDistanceTest() {
        Horse horse = new Horse("stick", 1, 388);
        assertEquals(388, horse.getDistance(), 0.001);
    }
    @Test
    public void zeroDistanceByDefaultTest() {
        Horse horse = new Horse("stick", 1);
        assertEquals(0, horse.getDistance(), 0.001);
    }
    @Test
    public void moveUsesGetRandomTest(){
        try(MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)) {
            new Horse("stick", 25, 388).move();
        mockedStatic.verify(()-> Horse.getRandomDouble(0.2, 0.9));

        }
    }

}


