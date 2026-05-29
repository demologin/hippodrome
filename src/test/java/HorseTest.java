import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;


class HorseTest {

    @Test
    @DisplayName("When the first param in constructor is null => throw IAE")
    void whenTheFirstParamInConstructorIsNullThrowEa() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Horse(null, 3);
        });
    }

    @Test
    @DisplayName("When the first param in constructor is Null => get Message")
    void whenTheFirstParamInConstructorIsNullGetMessage() {
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> {
            new Horse(null, 3);
        });
        String correctResult = "Name cannot be null.";
        String actualMessage = illegalArgumentException.getMessage();
        assertEquals(correctResult, actualMessage);
    }

    @ParameterizedTest
    @EmptySource
    @ValueSource(
            strings = {" ", "  ", "\t", "\n", "\r", "\r\n", "\n\r",  " \t ", " \n "}
    )
    @DisplayName("When the first param is Blank or Empty => throw IAE")
    void whenTheFirstParamIsBlankOrEmptyThrowEa(String name) {
        assertThrows(IllegalArgumentException.class, () -> {
            new Horse(name, 3);
        });
    }

    @ParameterizedTest
    @EmptySource
    @ValueSource(
            strings = {" ", "  ", "\t", "\n", "\r", "\r\n", "\n\r",  " \t ", " \n "}
    )
    @DisplayName("When the first param is Blank or Empty => get Message")
    void whenTheFirstParamIsBlankOrEmptyGetMessage(String name) {
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> {
            new Horse(name, 3);
        });
        String correctResult = "Name cannot be blank.";
        String actualMessage = illegalArgumentException.getMessage();
        assertEquals(correctResult, actualMessage);
    }

    @Test
    @DisplayName("When the second param in constructor is negative => throw IAE")
    void whenTheSecondParamInConstructorIsNegativeThrowEa() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Horse("Plotva", -3);
        });
    }

    @Test
    @DisplayName("When the second param in constructor is negative => get Message")
    void whenTheSecondParamInConstructorIsNegativeGetMessage() {
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> {
            new Horse("Plotva", -3);
        });
        String correctMessage = "Speed cannot be negative.";
        String actualMessage = illegalArgumentException.getMessage();
        assertEquals(correctMessage, actualMessage);
    }

    @Test
    @DisplayName("When the third param in constructor is negative => throw IAE")
    void whenTheThirdParamInConstructorIsNegativeThrowEa() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Horse("Plotva", 3, -3);
        });
    }

    @Test
    @DisplayName("When the third param in constructor is negative => get Message")
    void whenTheThirdParamInConstructorIsNegativeGetMessage() {
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> {
            new Horse("Plotva", 3, -3);
        });
        String correctMessage = "Distance cannot be negative.";
        String actualMessage = illegalArgumentException.getMessage();
        assertEquals(correctMessage, actualMessage);
    }

    @Test
    @DisplayName("When the first param is String => Return Name")
    void whenTheFirstParamIsStringReturnName() {
        Horse horse = new Horse("Plotva", 3);

        String correctResult = "Plotva";
        String actualResult = horse.getName();

        assertEquals(correctResult, actualResult);
    }

    @ParameterizedTest
    @MethodSource("differentDoubles")
    @DisplayName("When the second param is double => Return Speed")
    void whenTheSecondParamIsDoubleReturnSpeed(Number inputSpeed, double expectedSpeed) {
        Horse horse = new Horse("Plotva", inputSpeed.doubleValue());

        double actualSpeed = horse.getSpeed();

        assertEquals(expectedSpeed, actualSpeed);
    }

    @ParameterizedTest
    @MethodSource("differentDoubles")
    @DisplayName("When the Third param is double => Return distance")
    void whenTheThirdParamIsDoubleReturnDistance(Number inputDistance, double expectedDistance) {
        Horse horse = new Horse("Plotva", 2.9, inputDistance.doubleValue());

        double actualDistance = horse.getDistance();

        assertEquals(expectedDistance, actualDistance);

    }

    @Test
    @DisplayName("When the third param is empty => return 0")
    void whenTheThirdParamIsEmptyReturn0() {
        Horse horse = new Horse("Plotva", 2.5);

        double actualDistance = horse.getDistance();

        assertEquals(0.0, actualDistance);
    }

    static Stream<Arguments> differentDoubles() {
        return Stream.of(
                Arguments.of(1, 1.0),
                Arguments.of(3.0, 3.0),
                Arguments.of(3.0f, 3.0),
                Arguments.of(3f, 3.0),
                Arguments.of(3L, 3.0)
        );
    }

    @Test
    @DisplayName("When use move => call getRandomDouble")
    void whenUseMoveCallGetRandomDouble() {
        Horse horse = new Horse("Plotva", 3);

        try (MockedStatic<Horse> horseMockedStatic = Mockito.mockStatic(Horse.class, Mockito.CALLS_REAL_METHODS)) {
            horse.move();
            horseMockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }

    }

    @ParameterizedTest
    @CsvSource({
            "0.2, 10.6",
            "0.5, 11.5",
            "0.9, 12.7"
    })
    @DisplayName("move should calculate new distance correctly")
    void moveShouldCalculateDistanceCorrectly(double randomValue,
                                              double expectedDistance) {
        double initialDistance = 10;
        double initialSpeed = 3;

        Horse horse = new Horse("Plotva", initialSpeed, initialDistance);

        try (MockedStatic<Horse> mockedStatic = Mockito.mockStatic(Horse.class, Mockito.CALLS_REAL_METHODS)) {

            mockedStatic
                    .when(() -> Horse.getRandomDouble(0.2, 0.9))
                    .thenReturn(randomValue);

            horse.move();

            assertEquals(expectedDistance, horse.getDistance());
        }
    }

}