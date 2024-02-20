import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class HorseTest {

    private Horse horse;
    private final String testHorseName = "Gorbunok";

    /**
     * When: horse name is null
     * Result: IllegalArgumentException("Name cannot be null.")
     */
    @Test
    @Order(1)
    @DisplayName("HorseTest: name is null")
    void create_horseWithNullName() {
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new Horse(null, 10.0, 10.0));

        Assertions.assertEquals("Name cannot be null.", exception.getMessage());
    }

    /**
     * When: horse name is blank
     * Result: IllegalArgumentException("Name cannot be blank.")
     */
    @ParameterizedTest
    @Order(2)
    @DisplayName("HorseTest: name is blank")
    @ValueSource
        (strings = {"", " ", "\t", "\n", "\u000B", "\f", "\r",
                    "\u001C", "\u001D", "\u001E", "\u001F"})
    void create_horseWithBlankName(String name) {
        Throwable exception = assertThrows(
            IllegalArgumentException.class,
            () -> new Horse(name, 10.0, 10.0));

        String expectedMessage = "Name cannot be blank.";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    @Order(3)
    @DisplayName("HorseTest: speed is negative")
    void create_HorseWithNegativeSpeed() {
        Throwable exception = assertThrows(
            IllegalArgumentException.class,
            () -> new Horse(testHorseName, -10.0, 10.0));

        String expectedMessage = "Speed cannot be negative.";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    @Order(4)
    @DisplayName("HorseTest: distance is negative")
    void create_HorseWithNegativeDistance() {
        Throwable exception = assertThrows(
            IllegalArgumentException.class,
            () -> new Horse(testHorseName, 10.0, -10.0));

        String expectedMessage = "Distance cannot be negative.";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    @Order(5)
    @DisplayName("HorseTest: getName")
    void test_getName() {
        horse = new Horse(testHorseName, 10.0, 10.0);
        assertEquals(testHorseName, horse.getName());
    }

    @Test
    @Order(6)
    @DisplayName("HorseTest: getSpeed")
    void test_getSpeed() {
        double expectedSpeed = 10.0;
        horse = new Horse(testHorseName, expectedSpeed, 10.0);
        assertEquals(expectedSpeed, horse.getSpeed());
    }

    @Test
    @Order(7)
    @DisplayName("HorseTest: getDistance")
    void test_getDistance() {
        double expectedDistance = 10.0;
        horse = new Horse(testHorseName, 10.0, expectedDistance);
        assertEquals(expectedDistance, horse.getDistance());
    }

    @Test
    @Order(8)
    @DisplayName("HorseTest: getDistance with two parameters")
    void test_getDistanceWithTwoParameters() {
        horse = new Horse("Gorbunok", 10.0);
        assertEquals(0, horse.getDistance());
    }

    @Test
    @Order(9)
    @DisplayName("HorseTest: move")
    @ExtendWith(MockitoExtension.class)
    void test_move() {
        double speed = 10;
        double distance = 100;

        horse = new Horse(testHorseName, speed, distance);
        try (MockedStatic<Horse> mockedHorse = Mockito.mockStatic(Horse.class)) {
            // значение, которое должен вернуть getRandomDouble
            double mockRandomDouble = 0.5;
            // мокаем статический метод getRandomDouble
            // указываем, что при вызове метода с параметрами 0.2 и 0.9 он должен вернуть mockRandomDouble
            mockedHorse.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(mockRandomDouble);

            horse.move();

            double expectedDistance = distance + speed * mockRandomDouble;
            double actualDistance = horse.getDistance();

            assertEquals(expectedDistance, actualDistance);
            // проверяем, что метод getRandomDouble был вызван с параметрами 0.2 и 0.9
            mockedHorse.verify(() -> Horse.getRandomDouble(0.2, 0.9));//
        }
    }
}