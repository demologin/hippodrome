import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

class HippodromeConstructorTest {
    @Test
    void whenListIsNull() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
    }
    @Test
    void whenListIsNullMessage() {
        String s = "";
        try {
            new Hippodrome(null);
        } catch (IllegalArgumentException e) {
            s = e.getMessage();
        }
        Assertions.assertEquals("Horses cannot be null.", s);
    }
    @Test
    void whenListIsEmptyList() {
        List<Horse> horses = new ArrayList<>();
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Hippodrome(horses));
    }
    @Test
    void whenListIsEmptyListMessage() {
        String s = "";
        try {
            List<Horse> horses = new ArrayList<>();
            new Hippodrome(horses);
        } catch (IllegalArgumentException e) {
            s = e.getMessage();
        }
        Assertions.assertEquals("Horses cannot be empty.", s);
    }
}