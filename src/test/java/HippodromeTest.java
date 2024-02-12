import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class HippodromeTest {
    private List<Horse> horses;
    @Mock
    private Horse horse;

    @BeforeEach
    void setUp() {

    }

    @Test
    void constructorWithIsNull() {
        horses = null;
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Hippodrome(horses));
        assertEquals(exception.getMessage(), "Horses cannot be null.");
    }

    @Test
    void constructorWithIsEmpty() {
        horses = List.of();
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Hippodrome(horses));
        assertEquals(exception.getMessage(), "Horses cannot be empty.");
    }

    @Test
    void getHorses() {
        horses = new ArrayList<>();
        for (int i = 1; i <= 30; i++) {
            horses.add(new Horse("Horse" + i, 1.0 * i));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        assertEquals(horses, hippodrome.getHorses());
    }

    @Test
    void move() {
        horses = new ArrayList<>();
        for (int i = 1; i <= 50; i++) {
            horses.add(horse);
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        hippodrome.move();
        Mockito.verify(horse, Mockito.times(50)).move();
    }

    @Test
    void getWinner() {
        horses = new ArrayList<>();
        for (int i = 1; i <= 30; i++) {
            horses.add(new Horse("Horse" + i, 1.0 * i));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        hippodrome.move();
        horses = hippodrome.getHorses();
        Horse winnerHorse = hippodrome.getWinner();
        Horse maxDistance = horses.get(0);
        for (Horse horse : horses) {
            if(horse.getDistance() > maxDistance.getDistance()){
                maxDistance = horse;
            }
        }
        assertEquals(winnerHorse.getDistance(), maxDistance.getDistance());
    }
}
