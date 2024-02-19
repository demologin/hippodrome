import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class HorseTest {
    private static Horse horse1;
    private static Horse horse2;

    @BeforeEach
    void setUp() {
        horse1 = new Horse("Pegaz", 1.2, 0.9);
        horse2 = new Horse("Krasava", 0.9);
    }

    @Test
    void givenHorse_WhenConstructorParameterEqualNull_ThenThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Horse(null, 1.0, 2.0));
    }

    @Test
    void givenHorse_WhenConstructorParameterEqualNull_ThenThrowExceptionHaveMessageNameCannotBeNull() {
        //given
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new Horse(null, 1.0, 2.0));
        //when
        String actual = e.getMessage();
        //then
        String expected = "Name cannot be null.";
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @CsvSource({"'', ''", "' '", "'\t'", "'\n'", "'\r'", "'\f'",})
    void givenHorse_WhenNameHaveBlank_ThenThrowIllegalArgumentException(String blank) {
        assertThrows(IllegalArgumentException.class, () -> new Horse(blank, 0.1));
    }

    @ParameterizedTest
    @CsvSource({"'', ''", "' '", "'\t'", "'\n'", "'\r'", "'\f'",})
    void givenHorse_WhenNameHaveBlank_ThenThrowIllegalArgumentExceptionHaveMessageNameCannotBeBlank(String blank) {
        //given
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new Horse(blank, 0.1));
        //when
        String actual = e.getMessage();
        //then
        String expected = "Name cannot be blank.";
        assertEquals(expected, actual);
    }

    @Test
    void givenHorse_WhenSpeedIsNegative_ThenThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Horse("Buzefal", -1.2));
    }

    @Test
    void givenHorse_WhenSpeedIsNegative_ThenThrowIllegalArgumentExceptionHaveMessageSpeedCannotBeNegative() {
        //given
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new Horse("Buzefal", -1.2));
        //when
        String actual = e.getMessage();
        //then
        String expected = "Speed cannot be negative.";
        assertEquals(expected, actual);
    }

    @Test
    void givenHorse_WhenDistanceIsNegative_ThenThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Horse("Buzefal", 2.1, -0.8));
    }

    @Test
    void givenHorse_WhenDistanceIsNegative_ThenThrowIllegalArgumentExceptionMessageDistanceCannotBeNegative() {
        //given
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new Horse("Buzefal", 2.1, -0.8));
        //when
        String actual = e.getMessage();
        //then
        String expected = "Distance cannot be negative.";
        assertEquals(expected, actual);
    }

    @Test
    void getName() {
        //when
        String actual = horse1.getName();
        //then
        String expected = "Pegaz";
        assertEquals(expected, actual);
    }

    @Test
    void getSpeed() {
        //when
        Double actual = horse1.getSpeed();
        //then
        Double expected = 1.2;
        assertEquals(expected, actual);
    }

    @Test
    void givenConstructorWithThreeParameters_WhenGetDistance_ThenDistance() {
        //when
        Double actual = horse1.getDistance();
        //then
        Double expected = 0.9;
        assertEquals(expected, actual);
    }

    @Test
    void givenConstructorWithTwoParameters_WhenGetDistance_ThenZero() {
        //when
        Double actual = horse2.getDistance();
        //then
        Double expected = 0.0;
        assertEquals(expected, actual);
    }

    @Test
    void move() {
        try (MockedStatic<Horse> horseMockedStatic = Mockito.mockStatic(Horse.class)) {
            horse1.move();
            horseMockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9), Mockito.times(1));
        }
    }

    @ParameterizedTest
    @CsvSource({"0.2 , 0.9",})
    void getRandomDouble(Double minValue, Double maxValue) {
        //given
        try (MockedStatic<Horse> horseMockedStatic = Mockito.mockStatic(Horse.class)) {
            horseMockedStatic.when(() -> Horse.getRandomDouble(minValue, maxValue)).thenReturn(0.5);
            //when
            horse1.move();
            double actual = horse1.getDistance();
            //then
            Double expected = 0.9 + 1.2 * 0.5; //distance + speed * getRandomDouble(0.2, 0.9)
            Assertions.assertEquals(expected, actual);
        }
    }
}