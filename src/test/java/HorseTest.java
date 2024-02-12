import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class HorseTest {
    @Test
    void testConstructorWithIsNull() {
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Horse(null, 0));
        assertEquals(exception.getMessage(), "Name cannot be null.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "\t", "\u000B", "\u000C", " ", "\u1680", "\u2000",
            "\u2001", "\u2002", "\u2003", "\u2004", "\u2005", "\u2006",
            "\u2008", "\u2009", "\u200A", "\u2028", "\u2029", "\u205F", "\u3000"})
    void testConstructorWithIsBlank(String name) {
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Horse(name, 0));
        assertEquals(exception.getMessage(), "Name cannot be blank.");
    }

    @Test
    void testConstructorWithNegativeSpeed() {
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Horse("nameHorse", -6));
        assertEquals(exception.getMessage(), "Speed cannot be negative.");
    }

    @Test
    void testConstructorWithNegativeDistance() {
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Horse("nameHorse", 1, -8));
        assertEquals(exception.getMessage(), "Distance cannot be negative.");
    }

    @Test
    void getName() {
        Horse horse = new Horse("someName", 1);
        assertEquals(horse.getName(), "someName");
    }

    @Test
    void getSpeed() {
        Horse horse = new Horse("someName", 3);
        assertEquals(horse.getSpeed(), 3);
    }

    @Test
    void getDistance() {
        Horse horseWithoutDistance = new Horse("someName", 6);
        Horse horseWithDistance = new Horse("someName", 4, 5);
        assertEquals(horseWithoutDistance.getDistance(), 0);
        assertEquals(horseWithDistance.getDistance(), 5);
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.2, 0.4, 0.6})
    void move(double parameter) {
        Horse horse = new Horse("someName", 3);
        try (MockedStatic<Horse> horseMockedStatic = Mockito.mockStatic(Horse.class)) {
            horse.move();
            horseMockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
            horseMockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(parameter);
            assertEquals(Horse.getRandomDouble(0.2, 0.9), parameter);
        }
    }

}