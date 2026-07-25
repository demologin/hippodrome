import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class HippodromeTest {
    private List<Horse> horses = new ArrayList<>();
    private Hippodrome hippodrome;
    private final Random random = new Random();
    private final double maxDist = 0.9;
    private final double minDist = 0.2;

    @BeforeEach
    void setup(){
        int endExclusive = 31;
        IntStream.range(1, endExclusive).forEach(i -> {
            int rndNumber = random.nextInt(2) + 1;
            Horse horse= new Horse(
                    "Horse #" + i,
                    rndNumber,
                    rndNumber * (Math.random() * (maxDist - minDist))
            );
            horses.add(horse);
        });
        hippodrome = new Hippodrome(horses);
    }

    @Test
    @DisplayName("Check Constructor Horse Illegal Throw")
    void checkConstructorHorseIllegalThrow() {
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
    }

    @Test
    @DisplayName("Check Constructor Horse Illegal Throw Get Message")
    void checkConstructorHorseIllegalThrowGetMessage(){
        IllegalArgumentException exc = assertThrows(IllegalArgumentException.class,
                () -> new Hippodrome(null));
        assertEquals("Horses cannot be null.", exc.getMessage());
    }

    @Test
    @DisplayName("Check Constructor Horse Illegal Throw Empty List")
    void checkConstructorHorseIllegalThrowEmptyList(){
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(new ArrayList<Horse>()));
    }

    @Test
    @DisplayName("Check Constructor Horse Illegal Throw Empty List Get Message")
    void checkConstructorHorseIllegalThrowEmptyListGetMessage(){
        IllegalArgumentException exc = assertThrows(IllegalArgumentException.class,
                () -> new Hippodrome(new ArrayList<Horse>()));
        assertEquals("Horses cannot be empty.", exc.getMessage());
    }

    @Test
    @DisplayName("check Get Horses")
    void checkGetHorses(){
        assertIterableEquals(hippodrome.getHorses(), horses);
    }

    @Test
    @DisplayName("check Get Winner")
    void checkGetWinner() {
        Horse horseWinner = hippodrome.getWinner();
        Horse horseWithMaxDist = horses.stream().max(Comparator.comparing(Horse::getDistance)).get();
        assertEquals(horseWinner, horseWithMaxDist);
    }
    
    @Test
    @DisplayName("check move")
    void checkMove() {
        List<Horse> horsesMock = new ArrayList<Horse>();
        IntStream.range(1, 51).forEach(i -> {
            Horse horseMock = Mockito.mock(Horse.class);
            horsesMock.add(horseMock);
        });
        hippodrome = new Hippodrome(horsesMock);
        hippodrome.move();

        horsesMock.forEach(horse -> verify(horse, times(1)).move());
    }
}
