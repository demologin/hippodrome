import org.junit.jupiter.api.Test;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.mockito.MockedStatic;
import java.util.ArrayList;
import java.util.List;

public class HippodromeTest {

    // --- null list ---
    @Test
    void test_constructor_null_throws_exception() {
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> new Hippodrome(null)
        );
        assertEquals("Horses cannot be null.", ex.getMessage());
    }

    // --- empty list ---
    @Test
    void test_constructor_empty_list_throws_exception() {
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> new Hippodrome(new ArrayList<>())
        );
        assertEquals("Horses cannot be empty.", ex.getMessage());
    }

    // Methods Tests

    @Test
    void test_getHorses_returns_same_list_in_same_order() {
        List<Horse> list = new ArrayList<>();
        // создаём 30 разных лошадей
        for (int i = 0; i < 30; i++) {
            list.add(new Horse("Horse " + i, i, i * 1.5));
        }
        Hippodrome hippodrome = new Hippodrome(list);
        assertEquals(list, hippodrome.getHorses());
    }

    @Test
    void test_move_calls_move_on_all_horses() {

        List<Horse> horses = new ArrayList<>();

        // добавляем 50 моков
        for (int i = 0; i < 50; i++) {
            horses.add(mock(Horse.class));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        hippodrome.move();

        // проверяем, что move() был вызван у всех
        for (Horse horse : horses) {
            verify(horse).move();
        }
    }

    @Test
    void test_getWinner_returns_horse_with_max_distance() {

        Horse h1 = mock(Horse.class);
        Horse h2 = mock(Horse.class);
        Horse h3 = mock(Horse.class);

        when(h1.getDistance()).thenReturn(10.0);
        when(h2.getDistance()).thenReturn(25.0); // победитель
        when(h3.getDistance()).thenReturn(20.0);

        Hippodrome hippodrome = new Hippodrome(List.of(h1, h2, h3));

        Horse winner = hippodrome.getWinner();

        assertEquals(h2, winner);
    }

}
