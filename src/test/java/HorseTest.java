import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
public class HorseTest {

    @Test
    public void whenNameNullThrowException(){
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new Horse(null, 1, 1));
        assertEquals("Name cannot be null.", e.getMessage());
    }
    @ParameterizedTest
    @ValueSource(strings = {"", "    ", "\n\n\n\n\n", "\t\t\t\t"})
    public void whenNameIsBlankThrowException(String name){
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new Horse(name, 1, 1));
        assertEquals("Name cannot be blank.", e.getMessage());
    }
    @Test
    public void whenSpeedIsNegativeThrowException(){
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new Horse("Spirit",-1,1));
        assertEquals("Speed cannot be negative.", e.getMessage());
    }
    @Test
    public void whenDistanceIsNegativeThrowException(){
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new Horse("Spirit",1,-1));
        assertEquals("Distance cannot be negative.", e.getMessage());
    }
    @Test
    public void getName(){
        Horse horse = new Horse("someName", 1, 1);
        assertEquals("someName", horse.getName());
    }
    @Test
    public void getSpeed(){
        Horse horse = new Horse("someName", 1, 1);
        assertEquals(1, horse.getSpeed());
    }
    @Test
    public void getDistance(){
        Horse horse = new Horse("someName", 1, 1);
        assertEquals(1, horse.getDistance());
    }
    @Test
    public void defaultDistanceEqualZero(){
        Horse horse = new Horse("someName", 1);
        assertEquals(0, horse.getDistance());
    }
    @Test
    void moveUsesGetRandomDouble(){
        try (MockedStatic<Horse> mockedHorse = Mockito.mockStatic(Horse.class)) {
            mockedHorse.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(0.5);
            new Horse("someName", 1, 1).move();
        mockedHorse.verify(()->Horse.getRandomDouble(0.2,0.9));
        }
    }
    @ParameterizedTest
    @ValueSource(doubles = {0.2,0.3,0.5})
    void moveSetDistanceValue(double value){
        try (MockedStatic<Horse> mockedHorse = Mockito.mockStatic(Horse.class)){
            mockedHorse.when(()-> Horse.getRandomDouble(0.2,0.9)).thenReturn(value);

            Horse horse = new Horse("HorseName", 20, 30);
            horse.move();
            double expected = 30+20*value;
            assertEquals(expected,horse.getDistance()); 

        }
    }
}
