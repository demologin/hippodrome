import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class HorseTest {
    @Test
    public void illegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Horse(null, 3, 1));
    }

    @Test
    public void nullNameMessage() {
        try {
            new Horse(null, 3, 1);
        } catch (IllegalArgumentException e) {
            assertEquals("Name cannot be null.", e.getMessage());
        }

    }
}
