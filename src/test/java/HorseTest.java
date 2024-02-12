import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

class HorseTest {

    private Horse horse;
    private String testHorseName = "Gorbunok";

    @BeforeEach
    @Disabled
    void setUp() {
    }

    @AfterEach
    @Disabled
    void tearDown() {
    }

    /**
     * When: horse name is null
     * Result: IllegalArgumentException("Name cannot be null.")
     */
    @Test
    @DisplayName("Test: horse name is null")
    void create_horseWithNullName() {
        Throwable exception = assertThrows(
            IllegalArgumentException.class,
            () -> new Horse(null, 10.0, 10.0));

        String expectedMessage = "Name cannot be null.";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    /**
     * When: horse name is blank
     * Result: IllegalArgumentException("Name cannot be blank.")
     */
    @ParameterizedTest
    @DisplayName("Test: horse name is blank")
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
    @DisplayName("Test: horse speed is negative")
    void create_HorseWithNegativeSpeed() {
        Throwable exception = assertThrows(
            IllegalArgumentException.class,
            () -> new Horse(testHorseName, -10.0, 10.0));

        String expectedMessage = "Speed cannot be negative.";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    @DisplayName("Test: horse distance is negative")
    void create_HorseWithNegativeDistance() {
        Throwable exception = assertThrows(
            IllegalArgumentException.class,
            () -> new Horse(testHorseName, 10.0, -10.0));

        String expectedMessage = "Distance cannot be negative.";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    @DisplayName("Test: horse getName")
    void test_getName() {
        horse = new Horse(testHorseName, 10.0, 10.0);
        assertEquals(testHorseName, horse.getName());
    }

    @Test
    @DisplayName("Test: horse getSpeed")
    void test_getSpeed() {
        double expectedSpeed = 10.0;
        horse = new Horse(testHorseName, expectedSpeed, 10.0);
        assertEquals(expectedSpeed, horse.getSpeed());
    }

    @Test
    @DisplayName("Test: horse getDistance")
    void test_getDistance() {
        double expectedDistance = 10.0;
        horse = new Horse(testHorseName, 10.0, expectedDistance);
        assertEquals(expectedDistance, horse.getDistance());

    }

    @Test
    @DisplayName("Test: horse getDistance with two parameters")
    void test_getDistanceWithTwoParameters() {
        horse = new Horse("Gorbunok", 10.0);
        assertEquals(0, horse.getDistance());
    }

    @Test
    @DisplayName("Test: horseMove")
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

    @Test
    void getRandomDouble() {
    }
}