import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class HippodromeTest {
    private final List<Horse> horseList = new ArrayList<>();

    @Test
    void whenHorseListIsNullThenGetIAE() {
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
    }

    @Test
    void whenHorseListIsNullThenGetMessage() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome((null)));
        String expectedMessage = "Horses cannot be null.";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void whenHorseListIsEmptyThenGetIAE() {
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(horseList));
    }

    @Test
    void whenHorseListIsEmptyThenGetMessage() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome((horseList)));
        String expectedMessage = "Horses cannot be empty.";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void whenGetHorsesThenReturnCorrectlyList() {
        for (int i = 0; i < 30; i++) {
            String horseName = "Horse-" + i;
            horseList.add(new Horse(horseName, i, i));
        }
        Hippodrome hippodrome = new Hippodrome(horseList);
        assertEquals(horseList, hippodrome.getHorses());
        horseList.clear();
    }

    @Test
    void whenMoveThenAllHorsesMove() {
        for (int i = 0; i < 50; i++) {
            Horse horse = Mockito.mock(Horse.class);
            horseList.add(horse);
        }
        Hippodrome hippodrome = new Hippodrome(horseList);
        hippodrome.move();
        for (Horse hors : hippodrome.getHorses()) {
            Mockito.verify(hors).move();
        }
        horseList.clear();
    }

    @Test
    void whenGetWinnerThenGetHorseWithBiggestDistance() {
        Horse horse1 = new Horse("Horse 1", 1, 1);
        Horse horse2 = new Horse("Horse 2", 2, 2);
        Horse horse3 = new Horse("Horse 3", 3, 3);
        horseList.add(horse1);
        horseList.add(horse2);
        horseList.add(horse3);
        Hippodrome hippodrome = new Hippodrome(horseList);
        Horse actualHorse = hippodrome.getWinner();
        assertEquals(horse3, actualHorse);
    }
}