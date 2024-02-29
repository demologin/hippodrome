import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class HorseTest {

    @Test
    void constructor_HorseNameIsNull_ThrowIAException() {
        assertThrows(IllegalArgumentException.class, () -> new Horse(null, 1, 1));
    }

    @Test
    void constructor_HorseNameIsNull_GetCorrectMessageException() {
        Throwable actualExpMsg = assertThrows(
                IllegalArgumentException.class,
                () -> new Horse(null, 1, 1));
        assertEquals("Name cannot be null.", actualExpMsg.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\t", "\n", "\r", "\f"})
    void constructor_HorseNameIsBlank_ThrowIAException(String name) {
        assertThrows(IllegalArgumentException.class, () -> new Horse(name, 1, 1));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\t", "\n", "\r", "\f"})
    void constructor_HorseNameIsBlank_GetCorrectMessageException(String name) {
        Throwable actualExpMsg = assertThrows(
                IllegalArgumentException.class,
                () -> new Horse(name, 1, 1));
        assertEquals("Name cannot be blank.", actualExpMsg.getMessage());
    }

    @Test
    void constructor_HorseSpeedIsNegative_ThrowIAException() {
        assertThrows(IllegalArgumentException.class, () -> new Horse("1", -1, 1));
    }

    @Test
    void constructor_HorseSpeedIsNegative_GetCorrectMessageException() {
        Throwable actualExpMsg = assertThrows(
                IllegalArgumentException.class,
                () -> new Horse("1", -1, 1));
        assertEquals("Speed cannot be negative.", actualExpMsg.getMessage());
    }

    @Test
    void constructor_HorseDistanceIsNegative_ThrowIAException() {
        assertThrows(IllegalArgumentException.class, () -> new Horse("1", 1, -1));
    }

    @Test
    void constructor_HorseDistanceIsNegative_GetCorrectMessageException() {
        Throwable actualExpMsg = assertThrows(
                IllegalArgumentException.class,
                () -> new Horse("1", 1, -1));
        assertEquals("Distance cannot be negative.", actualExpMsg.getMessage());
    }

    @Test
    void getName_provideName_getThisName() {
        String expectedName = "test";
        Horse horse = new Horse(expectedName, 1);
        String actualName = horse.getName();
        assertEquals(expectedName, actualName);
    }

    @Test
    void getSpeed_provideSpeed_getThisSpeed() {
        double expectedSpeed = 5.0;
        Horse horse = new Horse("test", expectedSpeed);
        double actualSpeed = horse.getSpeed();
        assertEquals(expectedSpeed, actualSpeed, 0.001);
    }

    @Test
    void getDistance_provideDistance_getThisDistance() {
        double expectedDistance = 5.0;
        Horse horse = new Horse("test", 1, expectedDistance);
        double actualDistance = horse.getDistance();
        assertEquals(expectedDistance, actualDistance, 0.001);
    }

    @Test
    void getDistance_useTwoParamConstructor_getZeroDistance() {
        double expectedDistance = 0;
        Horse horse = new Horse("test", 1);
        double actualDistance = horse.getDistance();
        assertEquals(expectedDistance, actualDistance, 0.001);
    }

    @Test
    void move_callInnerStaticMethodGetRandomDouble() {
        try (MockedStatic<Horse> mockHorse = Mockito.mockStatic(Horse.class)) {
            Horse horse = new Horse("test", 1, 5);
            horse.move();
            mockHorse.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }

    @Test
    void move_callGetRandomDouble_getCorrectNewHorseDistance() {
        try (MockedStatic<Horse> mockHorse = Mockito.mockStatic(Horse.class)) {
            mockHorse.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(0.7);
            Horse horse = new Horse("test", 1, 5);
            horse.move();
            double expectedDistance = 5 + 1 * 0.7;
            double actualDistance = horse.getDistance();
            assertEquals(expectedDistance, actualDistance, 0.001);
        }
    }

}