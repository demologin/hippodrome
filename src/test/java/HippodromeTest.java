import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class HippodromeTest {
    @Test
    public void horsesNullException() {
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
    }

    @Test
    public  void horsesNullMessage() {
        try {
            new Hippodrome(null);
        } catch (IllegalArgumentException e) {
            assertEquals("Horses cannot be null.", e.getMessage());
        }
    }

    @Test
    public void emtyListlException() {
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(new ArrayList<>()));
    }

    @Test
    public void emtyListMessage() {
        try {
            new Hippodrome(new ArrayList<>());
        } catch (IllegalArgumentException e) {
            assertEquals("Horses cannot be empty.", e.getMessage());
        }
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
   public void move () {
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
       Horse horse = new Horse("test1", 2.0, 1.0);
       Horse horse2 = new Horse("test2", 2.0, 15.0);
       Horse horse3 = new Horse("test3", 2.0, 5.0);
       Hippodrome hippodrome = new Hippodrome(List.of(horse, horse2, horse3));
       assertSame(horse2, hippodrome.getWinner());
   }


}
