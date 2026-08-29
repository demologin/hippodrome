import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class HippodromeTest {
    
    @Nested
    class Constructor {

        @Test
        @DisplayName("When horses are null throw IAE")
        void whenHorsesAreNullThrowIAE() {
            var e = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
            assertEquals("Horses cannot be null.", e.getMessage());
        }

        @Test
        @DisplayName("When horses are empty throw IAE")
        void whenHorsesAreEmptyThrowIAE() {
            var e = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(List.of()));
            assertEquals("Horses cannot be empty.", e.getMessage());
        }
    }

    @Nested
    class Method {

        @Test
        @DisplayName("When call getHorses return horses")
        void whenGetHorsesReturnHorses() {
            List<Horse> horses = Stream.generate(() -> new Horse("horse", 0))
                    .limit(30).toList();
            assertEquals(horses, new Hippodrome(horses).getHorses());
        }

        @Test
        @DisplayName("When move call move for each horse")
        void whenMoveCallMoveForEachHorse() {
            List<Horse> horses = Stream.generate(() -> mock(Horse.class)).limit(50).toList();
            new Hippodrome(horses).move();
            horses.forEach(horse -> verify(horse, times(1)).move());
        }

        @Test
        @DisplayName("When call getWinner return max distance")
        void whenGetWinnerReturnMaxDistance() {
            List<Horse> horses = IntStream.rangeClosed(1, 5)
                    .mapToObj(i -> new Horse("horse", 0, i)).toList();
            assertEquals(horses.getLast(), new Hippodrome(horses).getWinner());
        }
    }
}
