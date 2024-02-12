
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HippodromeTest {

    @Test
    @DisplayName("IllegalArgumentException if the parameter is null")
    void whenParamIsNullThrowIAE() {
        assertThrows(IllegalArgumentException.class, ()
                -> new Hippodrome(null));
    }

    @Test
    @DisplayName("Message if the parameter is null")
    void whenParamIsNullThenExceptionMessage() {
        Throwable exception = assertThrows(IllegalArgumentException.class, ()
                -> new Hippodrome(null)
        );
        String expectedMessage = "Horses cannot be null.";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    @DisplayName("IllegalArgumentException if the horses list is empty")
    void WhenHorsesListIsEmptyThrowIAE() {
        List<Horse> horseList = new ArrayList<>();
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(horseList));
    }

    @Test
    @DisplayName("IllegalArgumentException if the horses list is empty")
    void WhenHorsesListIsEmptyThenMessage() {
        List<Horse> horseList = new ArrayList<>();
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(horseList));
        String expectedMessage = "Horses cannot be empty.";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    @DisplayName("getHorses test")
    void getHorses() {
        List<Horse> horseList = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            horseList.add(new Horse("Name" + i, i, i));
        }
        Hippodrome hippodrome = new Hippodrome(horseList);
        assertEquals(horseList, hippodrome.getHorses());
    }

    @Test
    @DisplayName("move test")
    void move() {
        List<Horse> horseList = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            horseList.add(Mockito.mock(Horse.class));
        }
        Hippodrome hippodrome = new Hippodrome(horseList);
        hippodrome.move();
        for (Horse entity : hippodrome.getHorses()) {
            Mockito.verify(entity).move();
        }
    }

    @Test
    @DisplayName("winner test")
    void getWinner() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            horses.add(new Horse(String.format("Horse №%d", i + 1), 1.0d, i + 1));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        assertEquals(30.0d, hippodrome.getWinner().getDistance());
    }
}