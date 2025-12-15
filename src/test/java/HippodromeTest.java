import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;


public class HippodromeTest {
    @Test
    public void cannotBeEmptyHorses() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(new ArrayList<>()));

        assertEquals("Horses cannot be empty.", e.getMessage());
    }

    @Test
    public void getHorses() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            horses.add(new Horse("" + i, i, i));
        }
        Hippodrome hippodrome = new Hippodrome(horses);

        assertEquals(horses, hippodrome.getHorses());
    }

    @Test
    public void move() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            horses.add(mock(Horse.class));
        }
        new Hippodrome(horses).move();


        for (Horse horse : horses) {
            verify(horse).move();
        }
    }
    @Test
    public void getWinner(){
        Horse horse1 = new Horse("Pegasus",1,4);
        Horse horse2 = new Horse("Pegasus",1,2);
        Horse horse3 = new Horse("Pegasus",1,1);
        Horse horse4 = new Horse("Pegasus",1,3);
        Hippodrome hippodrome = new Hippodrome(List.of(horse1,horse2,horse3,horse4));

        assertEquals(horse1, hippodrome.getWinner());
    }

}
