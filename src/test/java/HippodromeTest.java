import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class HippodromeTest {

    List<Horse> emptyList = Collections.emptyList();
    List<Horse> expectedList = new ArrayList<>();

    @Test
    void constructor_HorseListIsNull_ThrowIAException() {
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
    }

    @Test
    void constructor_HorseListIsNull_GetCorrectMessageException() {
        Throwable actualExpMsg = assertThrows(
                IllegalArgumentException.class,
                () -> new Hippodrome(null));
        assertEquals("Horses cannot be null.", actualExpMsg.getMessage());
    }

    @Test
    void constructor_HorseListIsEmpty_ThrowIAException() {
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(emptyList));
    }

    @Test
    void constructor_HorseListIsEmpty_GetCorrectMessageException() {
        Throwable actualExpMsg = assertThrows(
                IllegalArgumentException.class,
                () -> new Hippodrome(emptyList));
        assertEquals("Horses cannot be empty.", actualExpMsg.getMessage());
    }
    @Test
    void getHorses_addHorses_getThisHorses() {
        for (int i = 0; i < 30; i++) {
            Horse horse = new Horse("Test", i);
            expectedList.add(horse);
        }
        List<Horse> actualList = new Hippodrome(expectedList).getHorses();
        assertEquals(expectedList, actualList);
        expectedList.clear();
    }
    @Test
    void move_addHorses_allHorsesMove() {
        for (int i = 0; i < 50; i++) {
            Horse mockHorse = Mockito.mock(Horse.class);
            expectedList.add(mockHorse);
        }
        Hippodrome hippodrome = new Hippodrome(expectedList);
        hippodrome.move();
        for (Horse horse : expectedList){
            Mockito.verify(horse).move();
        }
        expectedList.clear();
    }

    @Test
    void getWinner_addHorses_getHorseWithTheBiggestDistance() {
        for (int i = 1; i < 6; i++) {
            Horse horse = new Horse("test", i, i);
            expectedList.add(horse);
        }
        double expectedWinner = 5.0;
        Hippodrome hippodrome = new Hippodrome(expectedList);
        double actualWinner = hippodrome.getWinner().getDistance();
        assertEquals(expectedWinner, actualWinner, 0.001);
    }
}