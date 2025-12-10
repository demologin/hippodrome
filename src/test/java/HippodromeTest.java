import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class HippodromeTest {

    @Test
    public void whenHorsesParamIsNull_thenConstructorThrowsException() {
        assertThrows(IllegalArgumentException.class,
                () -> new Hippodrome(null));
    }

    @Test
    public void whenHorsesParamIsNull_thenConstructorThrowsExceptionWithSpecificMessage() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new Hippodrome(null));
        assertEquals("Horses cannot be null.", exception.getMessage());
    }

    @Test
    public void whenHorsesParamIsEmptyList_thenConstructorThrowsException() {
        assertThrows(IllegalArgumentException.class,
                () -> new Hippodrome(new ArrayList<>()));
    }

    @Test
    public void whenHorsesParamIsEmptyList_thenConstructorThrowsExceptionWithSpecificMessage() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new Hippodrome(new ArrayList<>()));
        assertEquals("Horses cannot be empty.", exception.getMessage());
    }

    @Test
    public void whenGetHorses_thenItReturnsListThatEqualsToHorsesParamOfConstructor() {
        int horsesNum = 30;
        double horseSpeed = 1.5;
        Random random = new Random();
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < horsesNum; i++) {
            int randomNum = random.nextInt(100500);
            String horseName = "Horse#" + randomNum;
            Horse horse = new Horse(horseName, horseSpeed);
            horses.add(horse);
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        List<Horse> list = hippodrome.getHorses();

        assertEquals(horses, list);
    }

    @Test
    public void whenMove_thenItCallsMoveForEachHorse() {
        int horsesNum = 50;
        List<Horse> mockHorses = new ArrayList<>();
        for (int i = 0; i < horsesNum; i++) {
            Horse horse = mock(Horse.class);
            mockHorses.add(horse);
        }
        Hippodrome hippodrome = new Hippodrome(mockHorses);
        hippodrome.move();

        for (Horse mockHorse : mockHorses) {
            verify(mockHorse).move();
        }
    }

    @Test
    public void whenGetWinner_thenItReturnsHorseWithTheLargestDistance() {
        int horsesNum = 50;
        double horseSpeed = 1.5;
        List<Horse> horses = new ArrayList<>();
        double theLargestDistance = Double.MIN_VALUE;
        for (int i = 0; i < horsesNum; i++) {
            String horseName = "Horse#" + i;
            double distance = Horse.getRandomDouble(1.0, 1000.0);
            Horse horse = new Horse(horseName, horseSpeed, distance);
            horses.add(horse);
            theLargestDistance = (Double.compare(theLargestDistance, distance) < 0)
                    ? distance
                    : theLargestDistance;
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        Horse winner = hippodrome.getWinner();

        assertEquals(theLargestDistance, winner.getDistance());
    }
}