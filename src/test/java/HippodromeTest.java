import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

@ExtendWith(MockitoExtension.class)
class HippodromeTest {

    @Test
    void ifListOfHorsesIsNull_ThrowsIllegalArgException() {
        IllegalArgumentException e = Assertions.assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
        assertEquals("Horses cannot be null.", e.getMessage());
    }

    @Test
    void ifListOfHorsesIsEmpty_ThrowsIllegalArgException() {
        IllegalArgumentException e = Assertions.assertThrows(IllegalArgumentException.class, () -> new Hippodrome(new ArrayList<>()));
        assertEquals("Horses cannot be empty.", e.getMessage());
    }

    @Test
    void getHorses() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            horses.add(new Horse("name" + i, Horse.getRandomDouble(1, 2)));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        assertEquals(horses, hippodrome.getHorses());
    }

    @Test
    void move() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            horses.add(Mockito.mock(Horse.class));
        }
        Hippodrome mocksHippodrome = new Hippodrome(horses);
        mocksHippodrome.move();
        for (Horse horse : horses) {
            Mockito.verify(horse).move();
        }
    }

    @Test
    void getWinner_GivesAWinnerHorse() {
        Horse horse1 = new Horse("1", 1, 2);
        Horse horse2 = new Horse("2", 2, 7);
        Horse horse3 = new Horse("3", 3, 6.99);
        Horse horse4 = new Horse("4", 4, 5);
        Horse horse5 = new Horse("5", 5);
        Hippodrome hippodrome = new Hippodrome(List.of(horse1, horse2, horse3, horse4, horse5));
        assertSame(horse2, hippodrome.getWinner());
    }

}