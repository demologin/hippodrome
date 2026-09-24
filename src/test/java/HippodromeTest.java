import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class HippodromeTest {

    @Test
    void testConstructorWithArgumentFirst(){
        IllegalArgumentException iae = assertThrows(IllegalArgumentException.class, ()->new Hippodrome(null));
        assertEquals("Horses cannot be null.", iae.getMessage());

    }
    @Test
    void testConstructorWithArgumentSecond(){
        List<Horse> horses = new ArrayList<>();
        IllegalArgumentException iae = assertThrows(IllegalArgumentException.class, ()-> {
            new Hippodrome(horses);
        });
        assertEquals("Horses cannot be empty.", iae.getMessage());

    }

    @Test
    void getHorses() {
        List<Horse> horses = new ArrayList<Horse>();
        horses.add(new Horse("Bell", 1.1, 1.2));
        horses.add(new Horse("All", 1.2, 1.2));
        horses.add(new Horse("Cell", 1.3, 1.2));
        horses.add(new Horse("Well", 1.4, 1.2));
        horses.add(new Horse("Kell", 1.6, 1.2));
        horses.add(new Horse("Shell", 1.5, 1.2));
        horses.add(new Horse("Hell", 1.7, 1.2));
        horses.add(new Horse("Vell", 1.8, 1.2));
        horses.add(new Horse("Jell", 1.9, 1.2));
        horses.add(new Horse("Nell", 2.1, 1.2));
        horses.add(new Horse("Mell", 3.1, 1.2));
        horses.add(new Horse("Sell", 4.1, 1.2));
        horses.add(new Horse("Bem", 5.1, 1.2));
        horses.add(new Horse("Ben", 6.1, 1.2));
        horses.add(new Horse("Bel", 7.1, 1.2));
        horses.add(new Horse("Bef", 8.1, 1.2));
        horses.add(new Horse("Fell", 9.1, 1.2));
        horses.add(new Horse("Bev", 1.12, 1.2));
        horses.add(new Horse("Bew", 1.11, 1.2));
        horses.add(new Horse("Beq", 1.13, 1.2));
        horses.add(new Horse("Bep", 1.14, 1.2));
        horses.add(new Horse("Pell", 1.15, 1.2));
        horses.add(new Horse("Lem", 1.16, 1.2));
        horses.add(new Horse("Bill", 1.17, 1.2));
        horses.add(new Horse("Bull", 1.18, 1.2));
        horses.add(new Horse("Boll", 1.19, 1.2));
        horses.add(new Horse("Ball", 11.1, 1.2));
        horses.add(new Horse("Zell", 12.1, 1.2));
        horses.add(new Horse("Beh", 13.1, 1.2));
        horses.add(new Horse("Bet", 14.1, 1.2));
        horses.add(new Horse("Ber", 15.1, 1.2));

        Hippodrome hippodrome = new Hippodrome(horses);

        assertEquals(horses, hippodrome.getHorses());
    }

    @Test
    void move() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            Horse mock = mock(Horse.class);
            horses.add(mock);
        }

        Hippodrome hippodrome = new Hippodrome(horses);
        hippodrome.move();

        for (Horse horse:horses){
            verify(horse, times(1)).move();
        }
    }

    @Test
    void getWinner() {
        List<Horse> horses = new ArrayList<Horse>();
        horses.add(new Horse("Bell", 1.1, 3.2));
        horses.add(new Horse("All", 1.2, 5.2));
        horses.add(new Horse("Cell", 1.3, 1.6));
        Hippodrome hippodrome = new Hippodrome(horses);
        assertEquals(horses.get(1), hippodrome.getWinner());
    }
}