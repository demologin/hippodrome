import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class HorseTest {
    private static String testName;
    private static double testSpeed;

    @BeforeAll
    static void setUpAll() {
        testName = "TestHorse";
        testSpeed = 11.1;
    }

    @Test
    void whenConstructorNameIsNull_throwIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Horse(null, testSpeed));
    }

    @Test
    void whenConstructorNameIsNull_illegalArgumentExceptionContainsExpectedMessage() {
        //when
        Exception actualException = assertThrows(IllegalArgumentException.class, () -> new Horse(null, testSpeed));
        String actualExceptionMessage = actualException.getMessage();
        //then
        String expectedMessage = "Name cannot be null.";
        assertEquals(expectedMessage, actualExceptionMessage);
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\t", "\u000B", "\u000C", "\u1680", "\u2000", "\u2001", "\u2002", "\u2003",
            "\u2004", "\u2005", "\u2006", "\u2008", "\u2009", "\u200A", "\u2028", "\u205F", "\u3000", "\u2028", "\u2029"})
    void whenConstructorNameIsBlank_throwIllegalArgumentException(String blankName) {
        assertThrows(IllegalArgumentException.class, () -> new Horse(blankName, testSpeed));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\t", "\u000B", "\u000C", "\u1680", "\u2000", "\u2001", "\u2002", "\u2003",
            "\u2004", "\u2005", "\u2006", "\u2008", "\u2009", "\u200A", "\u2028", "\u205F", "\u3000", "\u2028", "\u2029"})
    void whenConstructorNameIsBlank_illegalArgumentExceptionContainsExpectedMessage(String blankName) {
        //when
        Exception actualException = assertThrows(IllegalArgumentException.class, () -> new Horse(blankName, testSpeed));
        String actualExceptionMessage = actualException.getMessage();
        //then
        String expectedMessage = "Name cannot be blank.";
        assertEquals(expectedMessage, actualExceptionMessage);
    }

    @Test
    void whenConstructorSpeedIsNegative_throwIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Horse(testName, -7.3));
    }

    @Test
    void whenConstructorSpeedIsNegative_illegalArgumentExceptionContainsExpectedMessage() {
        //when
        Exception actualException = assertThrows(IllegalArgumentException.class, () -> new Horse(testName, -7.3));
        String actualExceptionMessage = actualException.getMessage();
        //then
        String expectedMessage = "Speed cannot be negative.";
        assertEquals(expectedMessage, actualExceptionMessage);
    }

    @Test
    void whenConstructorDistanceIsNegative_throwIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Horse(testName, testSpeed, -1));
    }

    @Test
    void whenConstructorDistanceIsNegative_illegalArgumentExceptionContainsExpectedMessage() {
        //when
        Exception actualException = assertThrows(IllegalArgumentException.class, () -> new Horse(testName, testSpeed, -1));
        String actualExceptionMessage = actualException.getMessage();
        //then
        String expectedMessage = "Distance cannot be negative.";
        assertEquals(expectedMessage, actualExceptionMessage);
    }

    @Test
    void whenGetName_getFirstParam() {
        //given
        String expectedName = testName;
        Horse testHorse = new Horse(expectedName, testSpeed);
        //when
        String actual = testHorse.getName();
        //then
        assertEquals(expectedName, actual);
    }

    @Test
    void whenGetSpeed_getSecondParam() {
        //given
        double expectedSpeed = 10;
        Horse testHorse = new Horse(testName, expectedSpeed);
        //when
        double actual = testHorse.getSpeed();
        //then
        assertEquals(expectedSpeed, actual);
    }

    @Test
    void givenHorse3Param_whenGetDistance_getThirdParam() {
        //given
        double expectedDistance = 5;
        Horse horse3Param = new Horse(testName, testSpeed, expectedDistance);
        //when
        double actual = horse3Param.getDistance();
        //then
        assertEquals(expectedDistance, actual);
    }

    @Test
    void givenHorse2Param_whenGetDistance_getZero() {
        //given
        Horse horse2Param = new Horse(testName, testSpeed);
        //when
        double actual = horse2Param.getDistance();
        //then
        double expected = 0;
        assertEquals(expected, actual);
    }

    @Test
    void whenMove_invokeGetRandomDoubleMethodWithExpectedParams() {
        try (MockedStatic<Horse> mockedStaticHorse = Mockito.mockStatic(Horse.class)) {
            new Horse(testName, Mockito.anyDouble(), Mockito.anyDouble()).move();
            mockedStaticHorse.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }

    @ParameterizedTest
    @ValueSource(doubles = {188.5, 3.2, 200, 1.1111, 5.0})
    void whenMove_distanceIsCalculatedCorrectly(double sourceRandomDouble) {
        try (MockedStatic<Horse> mockedStaticHorse = Mockito.mockStatic(Horse.class)) {
            //given
            double testDistance = 15;
            Horse testHorse = new Horse(testName, testSpeed, testDistance);
            mockedStaticHorse.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(sourceRandomDouble);
            //when
            testHorse.move();
            //then
            double actualDistanceAfterMove = testHorse.getDistance();
            double expectedDistanceAfterMove = testDistance + testSpeed * sourceRandomDouble;
            assertEquals(expectedDistanceAfterMove, actualDistanceAfterMove);
        }
    }
}