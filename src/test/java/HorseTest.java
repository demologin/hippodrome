import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class HorseTest {

    private static final String defaultName = "Pretty Horse";
    private static final double defaultSpeed = 1.5;

    @Test
    public void whenNameParamIsNull_thenConstructorThrowsException() {
        assertThrows(IllegalArgumentException.class,
                () -> new Horse(null, defaultSpeed));
    }

    @Test
    public void whenNameParamIsNull_thenConstructorThrowsExceptionWithSpecificMessage() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new Horse(null, defaultSpeed));
        assertEquals("Name cannot be null.", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\t", "\n", "\r", "\f", " \t\n\r\f"})
    public void whenNameParamIsEmptyString_thenConstructorThrowsException(String firstParam) {
        assertThrows(IllegalArgumentException.class,
                () -> new Horse(firstParam, defaultSpeed));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\t", "\n", "\r", "\f", " \t\n\r\f"})
    public void whenNameParamIsEmptyString_thenConstructorThrowsExceptionWithSpecificMessage(String firstParam) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new Horse(firstParam, defaultSpeed));
        assertEquals("Name cannot be blank.", exception.getMessage());
    }

    @Test
    public void whenSpeedParamIsNegative_thenConstructorThrowsException() {
        assertThrows(IllegalArgumentException.class,
                () -> new Horse(defaultName, -defaultSpeed));
    }

    @Test
    public void whenSpeedParamIsNegative_thenConstructorThrowsExceptionWithSpecificMessage() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new Horse(defaultName, -defaultSpeed));
        assertEquals("Speed cannot be negative.", exception.getMessage());
    }

    @Test
    public void whenDistanceParamIsNegative_thenConstructorThrowsException() {
        assertThrows(IllegalArgumentException.class,
                () -> new Horse(defaultName, defaultSpeed, -1));
    }

    @Test
    public void whenDistanceParamIsNegative_thenConstructorThrowsExceptionWithSpecificMessage() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new Horse(defaultName, defaultSpeed, -1));
        assertEquals("Distance cannot be negative.", exception.getMessage());
    }

    @Test
    public void whenGetName_thenItReturnsNameParamOfConstructor() {
        String name = defaultName;
        Horse horse = new Horse(name, defaultSpeed);

        assertEquals(name, horse.getName());
    }

    @Test
    public void whenGetSpeed_thenItReturnsSpeedParamOfConstructor() {
        Horse horse = new Horse(defaultName, defaultSpeed);

        assertEquals(defaultSpeed, horse.getSpeed());
    }

    @Test
    public void whenGetDistance_thenItReturnsDistanceParamOfConstructor() {
        double distance = 100500;
        Horse horse = new Horse(defaultName, defaultSpeed, distance);

        assertEquals(distance, horse.getDistance());
    }

    @Test
    public void whenConstructedWithTwoParamsAndGetDistance_thenItReturnsZero() {
        Horse horse = new Horse(defaultName, defaultSpeed);

        assertEquals(0, horse.getDistance());
    }

    @Test
    public void whenMove_thenItCallsGetRandomDouble() {
        try (MockedStatic<Horse> staticMock = mockStatic(Horse.class)) {
            Horse horse = new Horse(defaultName, defaultSpeed);
            horse.move();

            staticMock.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.3, 0.4, 0.5})
    public void whenMove_thenItCalculatesDistanceByFormula(double input) {
        try (MockedStatic<Horse> staticMock = mockStatic(Horse.class)) {
            staticMock.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(input);
            double speed = 3.14;
            double initDistance = 42;
            Horse horse = new Horse(defaultName, speed, initDistance);
            double expectedDistance = horse.getDistance() + speed * input;
            horse.move();

            assertEquals(expectedDistance, horse.getDistance());
        }
    }
}