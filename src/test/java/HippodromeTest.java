import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class HippodromeTest {

    @Test
    void constructorWhenHorsesNull(){
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> new Hippodrome(null));
        assertEquals("Horses cannot be null.", exception.getMessage());
    }

    @Test
    void constructorWhenHorsesEmpty(){
        List<Horse> horses = new ArrayList<>();
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> new Hippodrome(horses));
        assertEquals("Horses cannot be empty.", exception.getMessage());
    }


    @Test
    void getHorses() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            Horse horse = new Horse("Horse" + i, Horse.getRandomDouble(0.2, 0.9));
            horses.add(horse);
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        List<Horse> hippodromeHorses = hippodrome.getHorses();
        assertEquals(horses, hippodromeHorses);
    }

    @Test
    void move() {
        List<Horse> mockList = new ArrayList<>();
        int countOfHorses = 50;
        for (int i = 0; i < countOfHorses; i++) {
            Horse mock = Mockito.mock(Horse.class);
            mockList.add(mock);
        }

        Hippodrome hippodrome = new Hippodrome(mockList);
        hippodrome.move();

        for (Horse horse : mockList) {
            Mockito.verify(horse).move();
        }
    }

    @Test
    void getWinner() {
        Horse horse1 = Mockito.mock(Horse.class);
        when(horse1.getDistance()).thenReturn(10.0);

        Horse horse2 = Mockito.mock(Horse.class);
        when(horse2.getDistance()).thenReturn(9.0);

        Horse horse3 = Mockito.mock(Horse.class);
        when(horse3.getDistance()).thenReturn(8.0);

        List<Horse> horses = List.of(horse1, horse2, horse3);
        Hippodrome hippodrome = new Hippodrome(horses);

        assertEquals(horse1, hippodrome.getWinner());
    }
}