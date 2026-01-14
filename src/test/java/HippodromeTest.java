import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;

public class HippodromeTest {
    @Test
    public void whenNullThrowException() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
        assertEquals("Horses cannot be null.", e.getMessage());
    }

    @Test
    public void whenEmptyThrowException() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(new ArrayList<>()));
        assertEquals("Horses cannot be empty.", e.getMessage());
    }

    @Test
    public void getHorses() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 1; i < 30; i++) {
            horses.add(new Horse("horse" + i, i, i));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        assertEquals(horses, hippodrome.getHorses());
    }

    @Test
    public void move(){
        List<Horse> horses = createMockedHorseList(50);
        Hippodrome hippodrome = new Hippodrome(horses);

        hippodrome.move();

        hippodrome.getHorses().forEach(horse -> verify(horse).move());
    }

    private List<Horse> createMockedHorseList(int number) {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < number; i++){
            horses.add(Mockito.mock(Horse.class));
        }
        return horses;
    }
    @Test
    public void getWinner(){
        List<Horse> horses = createHorseList(10);
        Hippodrome hippodrome = new Hippodrome(horses);

        Horse actual = hippodrome.getWinner();
        Horse expected = hippodrome.getHorses().get(9);

        assertEquals(expected, actual);
    }
    private List<Horse> createHorseList(int number){
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < number; i++){
            String horseName = String.valueOf(i+1);
            horses.add(new Horse(horseName, i, i ));
        }
        return horses;
    }
}