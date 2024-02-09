import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import org.mockito.MockedStatic;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class HorseTest {

    String validName = "Horse";
    double validSpeed = 1.0;
    double validDistance = 2.0;
    double negativeSpeed = -1.0;
    double negativeDistance = -2.0;
    String tabulation = "\t";
    String nullNameMsg = "Name cannot be null.";
    String negSpeedMsg = "Speed cannot be negative.";
    String negDistanceMsg = "Distance cannot be negative.";
    String blankNameMsg = "Name cannot be blank.";

    @Test
    void testConstructorWithNullName_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Horse(null, validSpeed, validDistance));
    }

    @Test
    void testConstructorWithNullName_getErrorMessage() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new Horse(null, validSpeed, validDistance));
        assertEquals(nullNameMsg, exception.getMessage());
    }

    @ParameterizedTest(name = "For name={0}")
    @DisplayName("Test constructor with different variations of blank names")
    @ValueSource(strings = {"", "   ", " ", "\t", "\n", "\r", "\f", "\u2000"})
    void testConstructorWithBlankNames(String name) {
        assertThrows(IllegalArgumentException.class, () -> new Horse(name, validSpeed, validDistance));
    }

    @Test
    void testConstructorWithBlankName_getErrorMessage() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new Horse(tabulation, validSpeed, validDistance));
        assertEquals(blankNameMsg, exception.getMessage());
    }

    @Test
    void testConstructorWithNegativeSpeed_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Horse(validName, negativeSpeed, validDistance));
    }

    @Test
    void testConstructorWithNegativeSpeed_getErrorMessage() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Horse(validName, negativeSpeed, validDistance));
        assertEquals(negSpeedMsg, exception.getMessage());
    }

    @Test
    void testConstructorWithNegativeDistance_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Horse(validName, validSpeed, negativeDistance));
    }

    @Test
    void testConstructorWithNegativeDistance_getErrorMessage() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Horse(validName, validSpeed, negativeDistance));
        assertEquals(negDistanceMsg, exception.getMessage());
    }

    @Test
    void testGetName_returnName() {
        Horse horse = new Horse(validName, validSpeed, validDistance);
        assertEquals(validName, horse.getName());
    }

    @Test
    void testGetSpeed_returnSpeed() {
        Horse horse = new Horse(validName, validSpeed, validDistance);
        assertEquals(validSpeed, horse.getSpeed());
    }

    @Test
    void testGetDistance_returnDistance() {
        Horse horse = new Horse(validName, validSpeed, validDistance);
        assertEquals(validDistance, horse.getDistance());
    }

    @Test
    void testGetDistanceWithoutDistance_returnZero() {
        Horse horse = new Horse(validName, validSpeed);
        assertEquals(0, horse.getDistance());
    }

    @Test
    void testMoveAssignsDistanceCalculatedByFormula_setCorrectDistance() {
        try (MockedStatic<Horse> horseMockedStatic = mockStatic(Horse.class)) {
            horseMockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(0.5);

            Horse horse = new Horse(validName, validSpeed, validDistance);
            horse.move();

            double expectedDistance = validDistance + validSpeed * 0.5;
            assertEquals(expectedDistance, horse.getDistance());
        }
    }

    @Test
    void testMoveWithMockedRandomDouble_methodCallsGetRandomDouble() {
        try (MockedStatic<Horse> horseMockedStatic = mockStatic(Horse.class)) {

            Horse horse = new Horse(validName, validSpeed, validDistance);
            horse.move();

            horseMockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }

}