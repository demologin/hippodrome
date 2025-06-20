import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.Spy;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class HippodromeTest {
    private static Horse winner;
    private List<Horse> horses;

    @BeforeEach
    public void init() {
        winner = new Horse("One", 20, 30000);
        horses = List.of(
                winner,
                new Horse("Two", 20, 30),
                new Horse("Three", 20, 30),
                new Horse("Four", 20, 30),
                new Horse("Five", 20, 30),
                new Horse("Six", 20, 30),
                new Horse("Seven", 20, 30),
                new Horse("Eight", 20, 30),
                new Horse("Nine", 20, 30),
                new Horse("Ten", 20, 30),
                new Horse("Eleven", 20, 30),
                new Horse("Twelve", 20, 30),
                new Horse("Thirteen", 20, 30),
                new Horse("Fourteen", 20, 30),
                new Horse("Fifteen", 20, 30),
                new Horse("Sixteen", 20, 30),
                new Horse("Seventeen", 20, 30),
                new Horse("Eighteen", 20, 30),
                new Horse("Nineteen", 20, 30),
                new Horse("Twenty", 20, 30),
                new Horse("TwentyOne", 20, 30),
                new Horse("TwentyTwo", 20, 30),
                new Horse("TwentyThree", 20, 30),
                new Horse("TwentyFour", 20, 30),
                new Horse("TwentyFive", 20, 30),
                new Horse("TwentySix", 20, 30),
                new Horse("TwentySeven", 20, 30),
                new Horse("TwentyEight", 20, 30),
                new Horse("TwentyNine", 20, 30),
                new Horse("Thirty", 20, 30)
        );
    }

    @Test
    public void constructor_nullValue_throwsException() {
        Throwable exception = Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new Hippodrome(null);
                }
        );
    }

    @Test
    public void constructor_nullValue_throwsExceptionWithMessage() {
        Throwable exception = Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new Hippodrome(null);
                    throw new IllegalArgumentException("Horses cannot be null.");
                }
        );
        Assertions.assertEquals("Horses cannot be null.", exception.getMessage());
    }

    @Test
    public void constructor_emptyListValue_throwsException() {
        Throwable exception = Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new Hippodrome(new ArrayList<>());
                }
        );
    }

    @Test
    public void constructor_emptyListValue_throwsExceptionWithMessage() {
        Throwable exception = Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new Hippodrome(new ArrayList<>());
                    throw new IllegalArgumentException("Horses cannot be empty.");
                }
        );
        Assertions.assertEquals("Horses cannot be empty.", exception.getMessage());
    }

    @Test
    public void getHorses_validHorsesList_returnsSameHorsesList() {
        Hippodrome hippodrome = new Hippodrome(horses);
        List<Horse> res = hippodrome.getHorses();
        Assertions.assertIterableEquals(horses, res);
    }

    @Spy
    List<Horse> mockedHorses = IntStream.range(0, 50)
            .mapToObj(x -> Mockito.mock(Horse.class))
            .collect(Collectors.toList());

    @Test
    public void move_mockedHorsesList_verifyCallingEveryHorseMove() {
        Hippodrome hippodrome = new Hippodrome(mockedHorses);
        hippodrome.move();
        mockedHorses.forEach(h -> Mockito.verify(h).move());
    }

    @Test
    public void whenGetWinner_returnsHorseWithLongestDistance() {
        Hippodrome hippodrome = new Hippodrome(horses);
        hippodrome.move();
        Assertions.assertEquals(winner.getDistance(), hippodrome.getWinner().getDistance());
    }
}