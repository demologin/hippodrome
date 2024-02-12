
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class HorseConstructorTest {
    @Test
    void whenNameIsNull() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Horse(null, 1, 1));
    }
    @Test
    void whenNameIsNullMessage() {
        String s = "";
        try {
            new Horse(null,1,1);
        } catch (IllegalArgumentException e) {
            s = e.getMessage();
        }
        Assertions.assertEquals("Name cannot be null.", s);
    }
    @ParameterizedTest
    @ValueSource(strings = {""," ","\t","\n","\r","\f","\u2028","\u2029"})
    void whenNameIsBlank(String stringTest) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Horse(stringTest, 1, 1));
    }
    @ParameterizedTest
    @ValueSource(strings = {""," ","\t","\n","\r","\f","\u2028","\u2029"})
    void whenNameIsBlankMessage(String stringTest) {
        String s = "1";
        try {
            new Horse(stringTest,1,1);
        } catch (IllegalArgumentException e) {
            s = e.getMessage();
        }
        Assertions.assertEquals("Name cannot be blank.", s);
    }
    @Test
    void whenSpeedIsNegative() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Horse("Apple", -1, 1));
    }
    @Test
    void whenSpeedIsNegativeMessage() {
        String s = "";
        try {
            new Horse("Apple",-1,1);
        } catch (IllegalArgumentException e) {
            s = e.getMessage();
        }
        Assertions.assertEquals("Speed cannot be negative.", s);
    }
    @Test
    void whenDistanceIsNegative() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Horse("Apple", 1, -1));
    }
    @Test
    void whenDistanceIsNegativeMessage() {
        String s = "";
        try {
            new Horse("Apple",1,-1);
        } catch (IllegalArgumentException e) {
            s = e.getMessage();
        }
        Assertions.assertEquals("Distance cannot be negative.", s);
    }
}