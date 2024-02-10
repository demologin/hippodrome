import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class HorseTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    /**
     * When: horse name is null
     * Result: IllegalArgumentException("Name cannot be null.")
     */
    @Test
    @DisplayName("Test: horse name is null")
    void create_horseWithNullName_failed() {
        Throwable exception = assertThrows(
            IllegalArgumentException.class,
            () -> {
                Horse horse = new Horse(null, 10.0, 10.0);
            });
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
    void create_horseWithBlankName_failed(String name) {
        Throwable exception = assertThrows(
            IllegalArgumentException.class,
            () -> {
                Horse horse = new Horse(name, 10.0, 10.0);
            });
        String expectedMessage = "Name cannot be blank.";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    @DisplayName("Test: horse speed is negative")
    void create_HorseWithNegativeSpeed() {
        Throwable exception = assertThrows(
            IllegalArgumentException.class,
            () -> {
                Horse horse = new Horse("name", -10.0, 10.0);
            });
        String expectedMessage = "Speed cannot be negative.";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    @DisplayName("Test: horse speed is negative")
    void create_HorseWithNegativeDistance() {
        Throwable exception = assertThrows(
            IllegalArgumentException.class,
            () -> {
                Horse horse = new Horse("name", 10.0, -10.0);
            });
        String expectedMessage = "Distance cannot be negative.";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }



    @Test
    void getName() {
    }

    @Test
    void getSpeed() {
    }

    @Test
    void getDistance() {
    }

    @Test
    void move() {
    }

    @Test
    void getRandomDouble() {
    }
}