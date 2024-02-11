import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

public class HippodromeTest {
    private Hippodrome hippodrome;
    private List<Horse> horses;

    @BeforeEach
    public void setUp() {
        horses = Arrays.asList(mock(Horse.class), mock(Horse.class), mock(Horse.class));
        hippodrome = new Hippodrome(horses);
    }

    @Test
    public void testConstructorNullParameter() {
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
    }

    @Test
    public void testConstructorNullParameterMessage() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
        assertEquals("Horses cannot be null.", exception.getMessage());
    }

    @Test
    public void testConstructorEmptyList() {
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(Arrays.asList()));
    }

    @Test
    public void testConstructorEmptyListMessage() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(Arrays.asList()));
        assertEquals("Horses cannot be empty.", exception.getMessage());
    }

    @Test
    public void testGetHorses() {
        assertEquals(horses, hippodrome.getHorses());
    }

    @Test
    public void testMove() {
        hippodrome.move();
        for (Horse horse : horses) {
            verify(horse).move();
        }
    }

    @Test
    public void testGetWinner() {
        Horse winner = mock(Horse.class);
        when(winner.getDistance()).thenReturn(100.0); // assuming 100 is the max distance
        when(horses.get(0).getDistance()).thenReturn(50.0);
        when(horses.get(1).getDistance()).thenReturn(75.0);
        when(horses.get(2).getDistance()).thenReturn(90.0);

        assertEquals(winner, hippodrome.getWinner());
    }
}