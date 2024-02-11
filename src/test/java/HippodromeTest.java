
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HippodromeTest {

    @Test
    void nullArgumentException() {
        assertThrows(IllegalArgumentException.class, ()
                -> new Hippodrome(null));
    }

    @Test
    void nullHorseNameExceptionMessage() {
        Throwable exception = assertThrows(IllegalArgumentException.class, ()
                -> new Hippodrome(null)
        );
        String expectedMessage = "Horses cannot be null.";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void emptyHorsesList() {
        List<Horse> horseList = new ArrayList<>();
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(horseList));
    }

    @Test
    void emptyHorsesListMessage() {
        List<Horse> horseList = new ArrayList<>();
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(horseList));
        String expectedMessage = "Horses cannot be empty.";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void getHorses() {
        List<Horse> horseList = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            horseList.add(new Horse("Name" + i, i, i));
        }
        Hippodrome hippodrome = new Hippodrome(horseList);
        assertEquals(horseList, hippodrome.getHorses());
    }

    @Test
    void move() {
        List<Horse> horseList = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            horseList.add(Mockito.mock(Horse.class));
        }
        Hippodrome hippodrome = new Hippodrome(horseList);
        hippodrome.move();
        for (Horse entity : hippodrome.getHorses()) {
            Mockito.verify(entity).move();
        }
    }

    @Test
    void getWinner() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            horses.add(new Horse(String.format("Horse №%d", i+1), 1.0d,i + 1));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        assertEquals(30.0d, hippodrome.getWinner().getDistance());
    }
}