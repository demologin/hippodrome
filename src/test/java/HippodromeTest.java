import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class HippodromeTest {

    private Hippodrome hippodrome;

    @Test
    void nullHorsesHippodromeException() {
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
    }

    @Test
    void nullHorsesHippodromeExceptionMessage() {
        Exception checkException = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
        assertEquals("Horses cannot be null.", checkException.getMessage());
    }

    @Test
    void emptyHorseListHippodromeException() {
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(new ArrayList<>()));
    }

    @Test
    void emptyHorseListHippodromeExceptionMessage() {
        Exception checkException = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(new ArrayList<>()));
        assertEquals("Horses cannot be empty.", checkException.getMessage());
    }

    @Test
    void getHorses() {
        List<Horse> horsesList = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            String name = "Horse " + i;
            horsesList.add(new Horse(name, i, i));
        }
        hippodrome = new Hippodrome(horsesList);
        assertEquals(horsesList, hippodrome.getHorses());
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
        List<Horse> horsesList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            String name = "Horse " + i;
            horsesList.add(new Horse(name, i, i));
        }
        assertEquals(horsesList.get(horsesList.size() - 1), new Hippodrome(horsesList).getWinner());
    }
}
