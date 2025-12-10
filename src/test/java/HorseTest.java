import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

public class HorseTest {

    @Test
    void test_name_null_throws_exception() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Horse(null, 10, 5)
        );
        assertEquals("Name cannot be null.", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "   ", "\t", "\n", "\r\n"})
    void test_name_blank_throws_exception(String blank) {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Horse(blank, 10, 5)
        );
        assertEquals("Name cannot be blank.", exception.getMessage());
    }

    @Test
    void test_speed_negative_throws_exception() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Horse("Test", -1, 5)
        );
        assertEquals("Speed cannot be negative.", exception.getMessage());
    }

    @Test
    void test_distance_negative_throws_exception() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Horse("Test", 10, -1)
        );
        assertEquals("Distance cannot be negative.", exception.getMessage());
    }

    // Getters TEST

    @Test
    void test_getName_returns_name_passed_to_constructor() {
        Horse horse = new Horse("Spirit", 10, 5);
        assertEquals("Spirit", horse.getName());
    }

    @Test
    void test_getSpeed_returns_speed_passed_to_constructor() {
        Horse horse = new Horse("Spirit", 10, 5);
        assertEquals(10, horse.getSpeed());
    }

    @Test
    void test_getDistance_returns_distance_passed_to_constructor() {
        Horse horse = new Horse("Spirit", 10, 5);
        assertEquals(5, horse.getDistance());
    }

    @Test
    void test_getDistance_returns_zero_if_constructor_with_two_parameters_used() {
        Horse horse = new Horse("Spirit", 10);
        assertEquals(0, horse.getDistance());
    }


    @Test
    void test_move_calls_getRandomDouble_with_correct_params() {
        try (MockedStatic<Horse> mocked = Mockito.mockStatic(Horse.class)) {
            Horse horse = new Horse("Spirit", 10, 5);

            mocked.when(() -> Horse.getRandomDouble(Mockito.anyDouble(), Mockito.anyDouble()))
                    .thenReturn(0.5);
            horse.move();

            mocked.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }
}
