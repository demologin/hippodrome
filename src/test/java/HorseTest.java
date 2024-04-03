import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class HorseTest {
    private Horse testWithThreeParameters;
    private Horse testWithTwoParameters;
    private final String firstParameterName = "TestParameter";
    private final Double secondParameterSpeed = 2.2;
    private final Double thirdParameterDistance = 3.3;

    @BeforeEach
    void setUp() {
        testWithThreeParameters = new Horse(firstParameterName, secondParameterSpeed, thirdParameterDistance);
        testWithTwoParameters = new Horse(firstParameterName, secondParameterSpeed);

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void WhenFirstArgumentConstructorIsNullThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Horse(null, 2));
    }

    @Test
    void WhenFirstArgumentConstructorIsNullDoesErrorTextMatch() {
        try {
            new Horse(null, 2);
        } catch (Exception e) {
            assertEquals("Name cannot be null.", e.getMessage());
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\n", "\t"})
    void WhenFirstArgumentConstructorIsBlankString(String firstArgument) {
        assertThrows(IllegalArgumentException.class, () -> new Horse(firstArgument, 2));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\n", "\t"})
    void WhenFirstArgumentConstructorIsBlankStringDoesErrorTextMatch(String firstArgument) {
        try {
            new Horse(firstArgument, 2);
        } catch (Exception e) {
            assertEquals("Name cannot be blank.", e.getMessage());
        }
    }

    @Test
    void WhenSecondArgumentConstructorIsNegativeNumberThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Horse("Ru", Integer.MIN_VALUE));
    }

    @Test
    void WhenFirstArgumentConstructorIsNegativeNumberDoesErrorTextMatch() {
        try {
            new Horse("Ru", Integer.MIN_VALUE);
        } catch (Exception e) {
            assertEquals("Speed cannot be negative.", e.getMessage());
        }
    }

    @Test
    void WhenThirdArgumentConstructorIsNegativeNumberThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Horse("Ru", Integer.MAX_VALUE, Integer.MIN_VALUE));
    }

    @Test
    void WhenThirdArgumentConstructorIsNegativeNumberDoesErrorTextMatch() {
        try {
            new Horse("Ru", Integer.MAX_VALUE, Integer.MIN_VALUE);
        } catch (Exception e) {
            assertEquals("Distance cannot be negative.", e.getMessage());
        }
    }


    @Test
    void getName() {
        assertEquals(firstParameterName, testWithThreeParameters.getName());
    }

    @Test
    void getSpeed() {
        assertEquals(secondParameterSpeed, testWithThreeParameters.getSpeed());
    }

    @Test
    void getDistance() {
        assertAll("Проверка дистанции с одним и двумя параметрами в конструкторе",
                () -> assertEquals(thirdParameterDistance, testWithThreeParameters.getDistance()),
                () -> assertEquals(0, testWithTwoParameters.getDistance()));
    }

    @Test
    void MoveCallsMethodGetRandomDouble() {
        try (MockedStatic<Horse> horse = mockStatic(Horse.class)) {
            testWithTwoParameters.move();
            horse.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }

    @Test
    void MoveCallsMethodRandomDouble() {
        try (MockedStatic<Horse> horse = mockStatic(Horse.class)) {
            horse.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(0.5);
            double testDistance = testWithTwoParameters.getDistance() +
                    testWithThreeParameters.getSpeed() * Horse.getRandomDouble(0.2, 0.9);
            assertEquals(1.1, testDistance);
        }
    }


}