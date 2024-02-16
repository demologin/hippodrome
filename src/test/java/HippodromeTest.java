
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HippodromeTest {

    @Test
    void givenNullInConstructorWhenNewThenIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
    }

    @Test
    void givenNullInConstructorWhenThrowIllegalArgumentExceptionThenContainsText() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
        assertEquals("Horses cannot be null.", exception.getMessage());
    }

    @Test
    void givenEmptyListInConstructorWhenNewThenIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(new ArrayList<>()));
    }

    @Test
    void givenEmptyListInConstructorWhenThrowIllegalArgumentExceptionThenContainsText() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(new ArrayList<>()));
        assertEquals("Horses cannot be empty.", exception.getMessage());
    }

    @Test
    void getHorses() {
        List<Horse> list = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            list.add(new Horse("test" + i, i, i));
        }

        Hippodrome hippodrome = new Hippodrome(list);

        assertEquals(list, hippodrome.getHorses());
    }

    @Test
    void move() {
        List<Horse> list = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            list.add(Mockito.mock(Horse.class));
        }
        Hippodrome hippodrome = new Hippodrome(list);

        hippodrome.move();

        for (Horse mockHorse : list) {
            Mockito.verify(mockHorse).move();
        }
    }

    @Test
    void getWinner() {
        List<Horse> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(new Horse("" + i, i, i));
        }
        Hippodrome hippodrome = new Hippodrome(list);

        assertEquals(list.get(9), hippodrome.getWinner());
    }
}