import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mockStatic;

public class HorseTest {

    Horse horse;
    @BeforeEach
    public void init(){
        horse = new Horse("hoh", 10, 2.4);
    }

    @Test
    public void FirstArgNotNull(){
        assertThrows(IllegalArgumentException.class, () -> new Horse(null, 10));
    }

    @Test
    public void NullExeptionMessage(){
        Throwable throwable = assertThrows(IllegalArgumentException.class,
                () -> new Horse(null, 10));
        assertEquals("Name cannot be null.", throwable.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "       ", "    "})
    public void NameIsBlank(String name){
        assertThrows(IllegalArgumentException.class, () -> new Horse(name, 10));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "       ", "    "})
    public void IsBlankExeptionMessage(String name){
        Throwable throwable = assertThrows(IllegalArgumentException.class, () -> new Horse(name, 10));
        assertEquals("Name cannot be blank.", throwable.getMessage());
    }

    @Test
    public void SecondArgNotNegative(){
        assertThrows(IllegalArgumentException.class, () -> new Horse("hoh", -2));
    }

    @Test
    public void SecondArgNotNegativeMessage(){
        Throwable throwable = assertThrows(IllegalArgumentException.class,
                () -> new Horse("hoh", -4));
        assertEquals("Speed cannot be negative.", throwable.getMessage());
    }

    @Test
    public void ThirdArgNotNegative(){
        Throwable throwable = assertThrows(IllegalArgumentException.class,
                () -> new Horse("hoh", 25, -1));
    }

    @Test
    public void ThirdArgNotNegativeMessage(){
        Throwable throwable = assertThrows(IllegalArgumentException.class,
                () -> new Horse("hoh", 25, -1));
        assertEquals("Distance cannot be negative.", throwable.getMessage());
    }

    @Test
    public void getNameTest(){
        assertEquals("hoh", horse.getName());
    }

    @Test
    public void getSpeedTest(){
        assertEquals(10, horse.getSpeed());
    }

    @Test
    public void getDistanceTwoArgsTest(){
        horse = new Horse("hoh", 23);
        assertEquals(0, horse.getDistance());
    }

    @Test
    public void getDistanceThreeArgsTest(){
        assertEquals(2.4, horse.getDistance());
    }

    @Test
    public void getRandomDoubleInMoveTest(){
        try (MockedStatic<Horse> horseMockedStatic = mockStatic(Horse.class);){
            horseMockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(0.2, 0.9);
            horse.move();
            horseMockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.2, 0.5, 0.9})
    void DistanceFormulaTest(double random) {
        try (MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)) {
            Horse horse = new Horse("Lobster", 2.0, 5.0);

            mockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(random);

            horse.move();

            double expected = 5.0 + 2.0 * random;
            assertEquals(expected, horse.getDistance());
        }
    }
}

