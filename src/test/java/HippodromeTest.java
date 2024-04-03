import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HippodromeTest {
    @Test
    void WhenArgumentConstructorIsNullThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
    }

    @Test
    void WhenArgumentConstructorIsNullDoesErrorTextMatch() {
        try {
            new Hippodrome(null);
        } catch (Exception e) {
            assertEquals("Horses cannot be null.", e.getMessage());
        }
    }

    @Test
    void WhenArgumentConstructorIsEmptyListThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(new ArrayList<>()));
    }

    @Test
    void WhenArgumentConstructorIsEmptyListDoesErrorTextMatch() {
        try {
            new Hippodrome(new ArrayList<>());
        } catch (Exception e) {
            assertEquals("Horses cannot be empty.", e.getMessage());
        }
    }

    @Test
    void getHorses() {
        List<Horse> listHorse = new ArrayList<>();
        List<Horse> listHorseClone = new ArrayList<>();
        for (int i = 1; i < 31; i++) {
            Horse horse = new Horse("Horsik", i);
            listHorse.add(horse);
            listHorseClone.add(horse);
        }
        Hippodrome hippodrome = new Hippodrome(listHorse);
        List<Horse> listFromMethod = hippodrome.getHorses();
        assertEquals(listHorseClone,listFromMethod);
    }

    @Test
    void move() {
        List<Horse> listHorse = new ArrayList<>();
        for (int i = 1; i < 51; i++) {
            Horse horse = Mockito.spy(new Horse("Horsik", i));
            listHorse.add(horse);
        }
        Hippodrome hippodrome = new Hippodrome(listHorse);
        hippodrome.move();
        for (int i = 1; i < 51; i++) {
            Mockito.verify(listHorse.get(i-1)).move();
        }
    }

    @Test
    void getWinner() {
        List<Horse> listHorse = new ArrayList<>();
        listHorse.add(new Horse("pop", 1, 2));
        listHorse.add(new Horse("top", 1, 3));
        Horse horse = new Horse("lop", 1, 4);
        listHorse.add(horse);
        Hippodrome hippodrome = new Hippodrome(listHorse);
        assertEquals(horse, hippodrome.getWinner());
    }
}