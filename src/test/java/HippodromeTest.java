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
    void nullHorsesThrowsException() {
        assertThrows(IllegalArgumentException.class,
                () -> new Hippodrome(null));
    }

    @Test
    void nullHorsesExceptionMessage() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                () -> new Hippodrome(null));

        assertEquals("Horses cannot be null.", e.getMessage());
    }

    @Test
    void getHorsesReturnsUnmodifiableList() {
        Hippodrome hippodrome = new Hippodrome(List.of(new Horse("A",1)));
        assertThrows(UnsupportedOperationException.class,
                () -> hippodrome.getHorses().add(new Horse("B",2)));
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
