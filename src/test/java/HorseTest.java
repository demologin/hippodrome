import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class HorseTest {

    @Test
    @DisplayName("When first parameter null throw EA")
    void whenFirstParameterNullThrowEa() {
        assertThrows(IllegalArgumentException.class,
                () -> {
                    new Horse(null, 0.2, 0.9);
                });
    }

    @Test
    @DisplayName("When first parameter null Then send message")
    void whenFirstParameterNullThenSendMessage() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> {
                    new Horse(null, 0.2, 0.9);
                });
        String expected = "Name cannot be null.";
        String actual = exception.getMessage();
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @MethodSource("stringDoubleAndDoubleProvider")
    @DisplayName("when fist parameter is blank then throw EA")
    void whenFistParameterIsBlankThenThrowEa(String name, Double speed, Double distance) {
        assertThrows(IllegalArgumentException.class,
                () -> {
                    new Horse(name, speed, distance);
                });
    }

    @ParameterizedTest
    @MethodSource("stringDoubleAndDoubleProvider")
    @DisplayName("when fist parameter is blank then send message")
    void whenFistParameterIsBlankThenSendMessage(String name, Double speed, Double distance) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> {
                    new Horse(name, speed, distance);
                });
        String expected = "Name cannot be blank.";
        String actual = exception.getMessage();
        assertEquals(expected, actual);
    }

    static Stream<Arguments> stringDoubleAndDoubleProvider() {
        return Stream.of(
                arguments("", 0.2, 0.3),
                arguments(" ", 0.2, 0.3),
                arguments("   ", 0.2, 0.3)
        );
    }

    @Test
    @DisplayName("when second parameter is negative then throw IAE")
    void whenSecondParameterIsNegativeThenThrowIae() {
        assertThrows(IllegalArgumentException.class,
                () -> {
                    new Horse("Laya", -0.2, 0.9);
                });
    }

    @Test
    @DisplayName("When second parameter is negative Then send message")
    void whenSecondParameterIsNegativeThenSendMessage() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> {
                    new Horse("Laya", -0.2, 0.9);
                });
        String expected = "Speed cannot be negative.";
        String actual = exception.getMessage();
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("When third parameter is negative then throw IAE")
    void whenThirdParameterIsNegativeThenThrowIae() {
        assertThrows(IllegalArgumentException.class,
                () -> {
                    new Horse("Laya", 0.2, -0.9);
                });
    }

    @Test
    @DisplayName("When third parameter is negative Then send message")
    void whenThirdParameterIsNegativeThenSendMessage() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> {
                    new Horse("Laya", 0.2, -0.9);
                });
        String expected = "Distance cannot be negative.";
        String actual = exception.getMessage();
        assertEquals(expected, actual);
    }

    @Test
    void whenFirstParameterIsStringThenReturnName() {
        Horse horse = new Horse("Laya", 0.3, 0.9);
        String expected = "Laya";
        String actual = horse.getName();
        assertEquals(expected, actual);
    }

    @Test
    void whenSecondParameterIsDoubleThenReturnSpeed() {
        Horse horse = new Horse("Laya", 0.3, 0.9);
        double expected = 0.3;
        double actual = horse.getSpeed();
        assertEquals(expected, actual);
    }

    @Test
    void whenThirdParameterIsDoubleThenReturnDistance() {
        Horse horse = new Horse("Laya", 0.3, 0.9);
        double expected = 0.9;
        double actual = horse.getDistance();
        assertEquals(expected, actual);
    }

    @Test
    void moveUseMethodGetRandom() {
        try (MockedStatic<Horse> horseMockedStatic = Mockito.mockStatic(Horse.class)) {
            Horse horse = new Horse("laya", 0.5, 100);
            horse.move();
            horseMockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.1, 0.5, 0.7, 0.57})
    void getRandomDoubleCalculatedForFormula(double randomValues) {
        try (MockedStatic<Horse> horseMockedStatic = Mockito.mockStatic(Horse.class)) {
            Horse horse = new Horse("laya", 0.5, 108);
            horseMockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(randomValues);
            horse.move();
            assertEquals(108 + 0.5 * randomValues, horse.getDistance());
        }
    }

}
