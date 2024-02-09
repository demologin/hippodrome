import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class HippodromeTest {


    @BeforeEach
    void setUp() {

    }

    @Test
    @DisplayName("Ensure NullPointerException is thrown when is null")
    void nullHippodrome() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
        assertEquals("Horses cannot be null.", exception.getMessage());
    }

    @Test
    @DisplayName("Ensure NullPointerException is thrown when is blank")
    void emptyHippodrome() {
        List<Horse> horses = new ArrayList<>();
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(horses));
        assertEquals("Horses cannot be empty.", exception.getMessage());
    }

    @Test
    @DisplayName("Ensure getHorses returns the same list and sequence")
    void getHorses() {

        List<Horse> horses = new ArrayList<>();
        Random random = new Random();
        for (int i = 1; i <= 30; i++) {
            double speed = 20 + random.nextDouble() * 30;
            double distance = random.nextDouble() * 100;
            horses.add(new Horse("Horse " + i, speed, distance));
        }

        Hippodrome hippodrome = new Hippodrome(horses);

        assertAll("Checking returned horses",
                () -> assertFalse(hippodrome.getHorses().isEmpty(), "Returned list should not be empty"),
                () -> assertIterableEquals(horses, hippodrome.getHorses(), "Returned list should contain the same horses in the same order")
        );

    }

    @Test
    void move() {
    }

    @Test
    void getWinner() {
    }
}