import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class HorseTest {
    static final String FULL_CONSTRUCTOR = "Full constructor";
    static final String SHORT_CONSTRUCTOR = "Short constructor";

    @Nested
    class Constructor {
        static final String PARAMS_CLASS = "HorseTest$Constructor$Params#";
        static final String WITH_NULL_NAMES = PARAMS_CLASS + "provideWithNullNames";
        static final String WITH_EMPTY_NAMES = PARAMS_CLASS + "provideWithEmptyNames";
        static final String WITH_NEGATIVE_SPEEDS = PARAMS_CLASS + "provideWithNegativeSpeeds";

        @ParameterizedTest
        @MethodSource(WITH_NULL_NAMES)
        @DisplayName("When name is null throw IAE")
        void whenNameIsNullThrowIAE(Executable constructor) {
            var e = assertThrows(IllegalArgumentException.class, constructor);
            assertEquals("Name cannot be null.", e.getMessage());
        }

        @ParameterizedTest
        @MethodSource(WITH_EMPTY_NAMES)
        @DisplayName("When name is empty throw IAE")
        void whenNameIsEmptyThrowIAE(Executable constructor) {
            var e = assertThrows(IllegalArgumentException.class, constructor);
            assertEquals("Name cannot be blank.", e.getMessage());
        }

        @ParameterizedTest
        @MethodSource(WITH_NEGATIVE_SPEEDS)
        @DisplayName("When speed is negative throw IAE")
        void whenSpeedIsNegativeThrowIAE(Executable constructor) {
            var e = assertThrows(IllegalArgumentException.class, constructor);
            assertEquals("Speed cannot be negative.", e.getMessage());
        }

        @Test
        @DisplayName("When distance is negative throw IAE")
        void whenDistanceIsNegativeThrowIAE() {
            Executable constructor = () -> new Horse("horse", 0, -1);
            var e = assertThrows(IllegalArgumentException.class, constructor);
            assertEquals("Distance cannot be negative.", e.getMessage());
        }

        static class Params {
            private static final String CHECK = ": check ";

            private static Stream<Named<Executable>> provideConstructors(String name, double speed) {
                return Stream.of(
                        Named.of(FULL_CONSTRUCTOR, () -> new Horse(name, speed, 0)),
                        Named.of(SHORT_CONSTRUCTOR, () -> new Horse(name, speed))
                );
            }

            static Stream<Named<Executable>> provideWithNullNames() {
                return provideConstructors(null, 0);
            }

            static Stream<Named<Executable>> provideWithNegativeSpeeds() {
                return provideConstructors("horse", -1);
            }

            static Stream<Named<Executable>> provideWithEmptyNames() {
                Map<String, String> strings = Map.of(
                        "", CHECK + "\"\"", " ", CHECK + "\" \"",
                        "\t", CHECK + "\\t", "\n", CHECK + "\\n"
                );
                Stream<Named<Executable>> fullConstructors = strings.entrySet().stream()
                        .map(entry -> Named.of(
                                FULL_CONSTRUCTOR + entry.getValue(),
                                () -> new Horse(entry.getKey(), 0, 0)
                        ));
                Stream<Named<Executable>> shortConstructors = strings.entrySet().stream()
                        .map(entry -> Named.of(
                                SHORT_CONSTRUCTOR + entry.getValue(),
                                () -> new Horse(entry.getKey(), 0)
                        ));
                return Stream.concat(fullConstructors, shortConstructors);
            }
        }
    }

    @Nested
    class Method {
        Horse horse;

        @BeforeEach
        void setUp() {
            horse = new Horse("horse", 1 , 1);
        }

        @Test
        @DisplayName("When get name return name")
        void whenGetNameReturnName() {
            assertEquals("horse", horse.getName());
        }

        @Test
        @DisplayName("When get speed return speed")
        void whenGetSpeedReturnSpeed() {
            assertEquals(1, horse.getSpeed());
        }

        @Test
        @DisplayName("When get distance return distance")
        void whenGetDistanceReturnDistance() {
            Horse anotherHorse = new Horse("horse", 1);
            assertAll(
                    () -> assertEquals(1, horse.getDistance(), FULL_CONSTRUCTOR),
                    () -> assertEquals(0, anotherHorse.getDistance(), SHORT_CONSTRUCTOR)
            );
        }

        @Test
        @DisplayName("When move then call get random double")
        void whenMoveThenCallGetRandomDouble() {
            try (MockedStatic<Horse> mockRandom = mockStatic(Horse.class)) {
                horse.move();
                mockRandom.verify(() -> Horse.getRandomDouble(0.2, 0.9), times(1));
            }
        }

        @ParameterizedTest
        @CsvSource({
                "0, 10, 0.2, 2",
                "5, 10, 0.5, 10",
                "0, 20, 0.9, 18"
        })
        @DisplayName("When move then set distance")
        void whenMoveThenSetDistance(double distance, double speed,
                                     double random, double expected) {
            Horse horse = new Horse("horse", speed, distance);

            try (MockedStatic<Horse> mockRandom = mockStatic(Horse.class)) {
                mockRandom.when(() -> Horse.getRandomDouble(anyDouble(), anyDouble()))
                        .thenReturn(random);

                horse.move();
                assertEquals(expected, horse.getDistance(), 0.0001,
                        "for random " + random);
            }
        }
    }
}
