import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
class HippodromeTest {

    @Test
    void whenArgumentIsNull_ThenThrowIllegalArgumentException(){
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new Hippodrome(null));

        String expected = "Horses cannot be null.";
        String actual = exception.getMessage();

        assertEquals(expected, actual);
    }

    @Test
    void whenArgumentIsEmptyList_ThenThrowIllegalArgumentException(){
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new Hippodrome(Collections.emptyList()));

        String expected = "Horses cannot be empty.";
        String actual = exception.getMessage();

        assertEquals(expected, actual);
    }

    @Test
    void getHorses(){
        List<Horse> horses = createHorseList(30);
        Hippodrome hippodrome = new Hippodrome(horses);

        List<Horse> actual = hippodrome.getHorses();

        assertEquals(horses, actual);
    }

    @Test
    void move(){
        List<Horse> horses = createMockedHorseList(50);
        Hippodrome hippodrome = new Hippodrome(horses);

        hippodrome.move();

        hippodrome.getHorses()
                .forEach(horse -> verify(horse).move());
    }

    @Test
    void getWinner(){
        List<Horse> horses = createHorseList(50);
        Hippodrome hippodrome = new Hippodrome(horses);

        Horse actualWinner = hippodrome.getWinner();

        Horse expectedlWinner = hippodrome.getHorses().stream()
                .max(Comparator.comparing(Horse::getDistance))
                .get();

        assertEquals(expectedlWinner, actualWinner);

    }


    private List<Horse> createHorseList(int number){
        List<Horse> horses = new ArrayList<>();
        String prefix = "Horse_";
        for(int i = 0; i < number; i++){
            double distance = ThreadLocalRandom.current().nextDouble(10, 100);
            horses.add(new Horse(prefix+i, 10, distance));
        }
        return horses;
    }

    private List<Horse> createMockedHorseList(int number){
        List<Horse> horses = new ArrayList<>();
        for(int i = 0; i < number; i++){
            horses.add(Mockito.mock(Horse.class));
        }
        return horses;
    }
}