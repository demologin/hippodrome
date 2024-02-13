import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HippodromeTest {

    @Test
    public void testConstructorWithNull () {
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
    }

    @Test
    public void testConstructorWithNullAndText () {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new Hippodrome(null));
        assertEquals("Horses cannot be null.", exception.getMessage());
    }

    @Test
    void getHorses() {
    }

    @Test
    void move() {
    }

    @Test
    void getWinner() {
    }
}