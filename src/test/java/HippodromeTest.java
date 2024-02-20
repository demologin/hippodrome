import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class HippodromeTest {
    String nullHorseMsg = "Horses cannot be null.";
    String emptyHorseMsg = "Horses cannot be empty.";
    String validName = "Horse";

    @Test
    void testConstructorWithNullHorsesList_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
    }

    @Test
    void testConstructorWithNullHorsesList_getErrorMessage() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
        assertEquals(nullHorseMsg, exception.getMessage());
    }

    @Test
    void testConstructorWithEmptyHorsesList_throwsIllegalArgumentException() {
        List<Horse> emptyList = Arrays.asList();

        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(emptyList));
    }

    @Test
    void testConstructorWithEmptyHorsesList_getErrorMessage() {
        List<Horse> emptyList = Arrays.asList();

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(emptyList));
        assertEquals(emptyHorseMsg, exception.getMessage());
    }

    @Test
    void testGetHorses_returnListWithSameObjectsInSameSequence() {
        List<Horse> originalHorses = createListOfHorses(30);
        Hippodrome hippodrome = new Hippodrome(originalHorses);

        assertEquals(originalHorses, hippodrome.getHorses());
    }

    @Test
    void testMove_checkAllHorsesMoved() {
        List<Horse> mockHorses = createMockListOfHorses(50);

        Hippodrome hippodrome = new Hippodrome(mockHorses);
        hippodrome.move();

        for (Horse mockHorse : mockHorses) {
            verify(mockHorse, times(1)).move();
        }
    }

    @Test
    void testGetWinner_returnHorseWithMaxDistance() {
        List<Horse> horses = createListOfHorsesWithDifferentDistances();
        Hippodrome hippodrome = new Hippodrome(horses);

        Horse winner = hippodrome.getWinner();
        double maxDistance = horses.stream()
                .mapToDouble(Horse::getDistance)
                .max()
                .orElse(0.0);

        assertEquals(maxDistance, winner.getDistance());
    }

    private List<Horse> createMockListOfHorses(int count) {
        List<Horse> mockHorses = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            Horse mockHorse = mock(Horse.class);
            mockHorses.add(mockHorse);
        }

        return mockHorses;
    }

    private List<Horse> createListOfHorsesWithDifferentDistances() {
        List<Horse> horses = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            double speed = 1.0 + i * 2.0;
            double distance = i * 1.5;
            horses.add(new Horse(validName + i, speed, distance));
        }
        return horses;
    }

    private List<Horse> createListOfHorses(int count) {
        return IntStream.rangeClosed(1, count)
                .mapToObj(i -> new Horse(validName + i, 5.0 + i))
                .collect(Collectors.toList());
    }

}