import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HippodromeTest {
    @Nested
    class HippodromeConstructorTest {
        @Test
        public void whenNullList_IllegalArgumentException() {
            assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
        }

        @Test
        public void whenNullList_IllegalArgumentExceptionMessage() {
            String expected = "Horses cannot be null.";
            String actual = "";
            try {
                new Hippodrome(null);
            } catch (Exception e) {
                actual = e.getMessage();
            }
            assertEquals(expected, actual);
        }

        @Test
        public void whenEmptyList_IllegalArgumentException() {
            List<Horse> horses = new ArrayList<>();
            assertThrows(IllegalArgumentException.class, () -> new Hippodrome(horses));
        }

        @Test
        public void whenEmptyList_IllegalArgumentExceptionMessage() {
            List<Horse> horses = new ArrayList<>();
            String expected = "Horses cannot be empty.";
            String actual = "";
            try {
                new Hippodrome(horses);
            } catch (Exception e) {
                actual = e.getMessage();
            }
            assertEquals(expected, actual);
        }
    }

    @Nested
    class HippodromeMethodsTest {
        @Test
        public void whenGetHorses() {
            List<Horse> horsesExpected = new ArrayList<>();
            for (int i = 0; i < 30; i++) {
                horsesExpected.add(new Horse("" + i, i, i));
            }

            Hippodrome hippodrome = new Hippodrome(horsesExpected);
            List<Horse> horsesActual = hippodrome.getHorses();
            assertEquals(horsesExpected, horsesActual);
        }

        @Test
        public void whenMoveHorses() {
            List<Horse> horses = new ArrayList<>();
            for (int i = 0; i < 50; i++) {
                horses.add(Mockito.mock(Horse.class));
            }
            Hippodrome hippodrome = new Hippodrome(horses);

            hippodrome.move();

//            horses.forEach(horse -> Mockito.verify(horse).move());
            for (Horse horse : horses) {
                Mockito.verify(horse).move();
            }
        }

        @Test
        public void whenGetWinner_WinnerReturns() {
            List<Horse> horses = new ArrayList<>();
            for (int i = 0; i < 30; i++) {
                horses.add(new Horse("" + i, i, i));
            }
            Hippodrome hippodrome = new Hippodrome(horses);

            Horse expected = horses.get(29);
            Horse actual = hippodrome.getWinner();
            assertSame(expected, actual);
        }

    }
}