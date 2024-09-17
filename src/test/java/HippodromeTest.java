import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class HippodromeTest {

    @Test
    @Order(1)
    @DisplayName("HippodromeTest: horses list is null")
    void create_hippodromeWithNullList() {
        Throwable exception = assertThrows(
            IllegalArgumentException.class,
            () -> new Hippodrome(null));
        String expectedMessage = "Horses cannot be null.";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    @Order(2)
    @DisplayName("HippodromeTest: horses list is blank")
    void create_hippodromeWithBlankList() {
        List<Horse> horses = new ArrayList<>();
        Throwable exception = assertThrows(
            IllegalArgumentException.class,
            () -> new Hippodrome(horses));
        assertEquals("Horses cannot be empty.", exception.getMessage());
    }

    @Test
    @Order(3)
    @DisplayName("HippodromeTest: getHorses")
    void getHorses() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            horses.add(new Horse("horse" + i, 10.0));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        assertEquals(horses, hippodrome.getHorses());
    }

    /** Проверить, что метод вызывает метод move у всех лошадей.
     * При создании объекта Hippodrome передай в конструктор список из 50 моков лошадей и
     * воспользуйся методом verify. */
    @Test
    @Order(4)
    @DisplayName("HippodromeTest: move")
    void move() {
        List<Horse> horses = new ArrayList<>();

        for (int i = 0; i < 50; i++)
        {
            horses.add(mock(Horse.class));
        }

        Hippodrome hippodrome = new Hippodrome(horses);
        hippodrome.move();

        for (Horse horse: horses) {
            verify(horse, atLeastOnce()).move();
        }
    }

    /** Проверить, что метод возвращает лошадь с самым большим значением distance. */
    @Test
    @Order(5)
    @DisplayName("HippodromeTest: getWinner")
    void getWinner() {
        List<Horse> horses = new ArrayList<>();
        Horse horse1 = mock(Horse.class);
        Horse horse2 = mock(Horse.class);
        Horse horse3 = mock(Horse.class);
        horses.add(horse1);
        horses.add(horse2);
        horses.add(horse3);

        when(horse1.getDistance()).thenReturn(100.0);
        when(horse2.getDistance()).thenReturn(125.0);
        when(horse3.getDistance()).thenReturn(75.0);

        Hippodrome hippodrome = new Hippodrome(horses);
        Horse winner = hippodrome.getWinner();

        assertEquals(horse2, winner);
    }
}