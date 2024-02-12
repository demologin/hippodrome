
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class HippodromeTest {

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
    @DisplayName("Ensure that the move method calls the move method inside the method ")
     void move() {

        List<Horse> horses = new ArrayList<>();
        Random random = new Random();
        for (int i = 1; i <= 50; i++) {
            Horse mockHorse = mock(Horse.class);
            double speed = 20 + random.nextDouble() * 30;
            double distance = random.nextDouble() * 100;
            when(mockHorse.getName()).thenReturn("Horse " + i);
            when(mockHorse.getSpeed()).thenReturn(speed);
            when(mockHorse.getDistance()).thenReturn(distance);
            horses.add(mockHorse);
        }

        Hippodrome hippodrome = new Hippodrome(horses);

        hippodrome.move();


        for (Horse horse : horses) {
            verify(horse).move();
        }

    }

    @Test
    @DisplayName("Ensure that the method returns the horse with the largest distance value")
    void getWinner() {

       List<Horse> horses = new ArrayList<>();

        horses.add(new Horse("Horse 1", 20, 950));
        horses.add(new Horse("Horse 2", 23, 1000));
        horses.add(new Horse("Horse 3", 18, 820));

            Hippodrome hippodrome = new Hippodrome(horses);

            for(Horse horse : horses) {
                System.out.println(horse.hashCode());
            }

        assertEquals(hippodrome.getWinner(),horses.get(1));
    }
}