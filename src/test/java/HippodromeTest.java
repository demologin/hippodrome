import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HippodromeTest {

    private static List<Horse> generateHorseList(int count) {
        List<Horse> listHorses = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            listHorses.add(new Horse("Horse" + i, i * 1.0));
        }
        return listHorses;
    }

    @Test
    void constructorHorsesCannotBeNull() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
        assertEquals("Horses cannot be null.", exception.getMessage());
    }

    @Test
    void constructorHorsesCannotBeEmpty() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(new ArrayList<>()));
        assertEquals("Horses cannot be empty.", exception.getMessage());
    }

    @Test
    void getHorses() {
        List<Horse> expected = generateHorseList(30);
        Hippodrome hippodrome = new Hippodrome(expected);
        List<Horse> actual = hippodrome.getHorses();

        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < expected.size(); i++) {
            assertSame(expected.get(i), actual.get(i));
        }

    }

    @Test
    void move() {
        List<Horse> horses = new ArrayList<>();

        for (int i = 1; i <= 50; i++) {
            Horse mockedHorse = Mockito.mock(Horse.class);
            horses.add(mockedHorse);
        }

        Hippodrome hippodrome = new Hippodrome(horses);
        hippodrome.move();
        for (Horse hors : horses) {
            Mockito.verify(hors).move();
        }
    }

    @Test
    void getWinner() {
        List<Horse> horses = List.of(
                new Horse("Horse1", 1.0, 10.0),
                new Horse("Horse2", 2.0, 25.0),
                new Horse("Horse3", 3.0, 30.0),
                new Horse("Horse4", 4.0, 15.0)
        );

        Hippodrome hippodrome = new Hippodrome(horses);
        Horse winner = hippodrome.getWinner();

        assertEquals("Horse3", winner.getName());
        assertEquals(30.0, winner.getDistance());
    }
}