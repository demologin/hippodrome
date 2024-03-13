import jdk.jfr.Category;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

class HorseTest {

    @Nested
    class HorseConstructorTest {

        @Nested
        class ParameterName {
            @Test
            public void whenNullNameIllegalArgumentException() {
                assertThrows(IllegalArgumentException.class, () -> new Horse(null, 1, 1));
            }

            @Test
            public void whenNullNameIllegalArgumentExceptionCorrectMessage() {
                String actual = "";
                try {
                    new Horse(null, 1, 1);
                    fail();
                } catch (IllegalArgumentException e) {
                    actual = e.getMessage();
                }
                assertEquals("Name cannot be null.", actual);
            }

            @ParameterizedTest
            @ValueSource(strings = {"", "  ", "\t\t", "\n\n\n"})
            public void whenBlankNameIllegalArgumentException(String name) {
                assertThrows(IllegalArgumentException.class, () -> new Horse(name, 1, 1));
            }

            @ParameterizedTest
            @ValueSource(strings = {"", "  ", "\t\t", "\n\n\n"})
            public void whenBlankNameIllegalArgumentExceptionCorrectMessage(String name) {
                String actual = "";
                try {
                    new Horse(name, 1, 1);
                    fail();
                } catch (IllegalArgumentException e) {
                    actual = e.getMessage();
                }
                assertEquals("Name cannot be blank.", actual);
            }
        }

        @Nested
        class ParameterSpeed {
            @Test
            public void whenNegativeSpeedIllegalArgumentException() {
                assertThrows(IllegalArgumentException.class, () -> new Horse("name", -10.0, 1));
            }

            @Test
            public void whenNegativeSpeedIllegalArgumentExceptionCorrectMessage() {
                String actual = "";
                try {
                    new Horse("name", -10.0, 1);
                    fail();
                } catch (IllegalArgumentException e) {
                    actual = e.getMessage();
                }
                assertEquals("Speed cannot be negative.", actual);
            }
        }

        @Nested
        class ParameterDistance {
            @Test
            public void whenNegativeDistanceIllegalArgumentException() {
                assertThrows(IllegalArgumentException.class, () -> new Horse("name", 10.0, -1));
            }

            @Test
            public void whenNegativeDistanceIllegalArgumentExceptionCorrectMessage() {
                String actual = "";
                try {
                    new Horse("name", 10.0, -1);
                    fail();
                } catch (IllegalArgumentException e) {
                    actual = e.getMessage();
                }
                assertEquals("Distance cannot be negative.", actual);
            }
        }
    }

    @Nested
    class HorseMethodsTest {
        @Nested
        class MethodGetName {
            @Test
            public void whenGetName() throws NoSuchFieldException, IllegalAccessException {
                String expected = "horse";
                Horse horse = new Horse(expected, 1, 2);
                Field name = Horse.class.getDeclaredField("name");
                name.setAccessible(true);
                String actual = (String) name.get(horse);
                assertEquals(expected, actual);
            }
        }

        @Nested
        class MethodGetSpeed {
            @Test
            public void whenGetSpeed() throws NoSuchFieldException, IllegalAccessException {
                double expected = 10.0;
                Horse horse = new Horse("horse", expected, 2);
                Field speed = Horse.class.getDeclaredField("speed");
                speed.setAccessible(true);
                double actual = (double) speed.get(horse);
                assertEquals(expected, actual);
            }
        }

        @Nested
        class MethodGetDistance {
            @Test
            public void whenGetDistance() throws NoSuchFieldException, IllegalAccessException {
                double expected = 10.0;
                Horse horse = new Horse("horse", 10.0, expected);
                Field distance = Horse.class.getDeclaredField("distance");
                distance.setAccessible(true);
                double actual = (double) distance.get(horse);
                assertEquals(expected, actual);
            }

            @Test
            public void whenGetDistanceAfterTwoParamsConstructor() throws NoSuchFieldException, IllegalAccessException {
                double expected = 0.0;
                Horse horse = new Horse("horse", 10.0);
                Field distance = Horse.class.getDeclaredField("distance");
                distance.setAccessible(true);
                double actual = (double) distance.get(horse);
                assertEquals(expected, actual);
            }
        }

        @Nested
        class MethodMove {
            @Test
            public void whenMoveVerifyGetRandomUsage() {
                try (MockedStatic<Horse> mockedStatic = Mockito.mockStatic(Horse.class)) {
                    new Horse("horse", 10.0, 2.0).move();
                    mockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
                }
            }

            @ParameterizedTest
            @ValueSource(doubles = {0.123, 123, 4.567})
            void moveTest(double random) {
                try (MockedStatic<Horse> mockedStatic = Mockito.mockStatic(Horse.class)) {
                    Horse horse = new Horse("horse", 2, 3);
                    mockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(random);
                    horse.move();
                    assertEquals(3 + 2 * random, horse.getDistance());
                }
            }
        }
    }
}