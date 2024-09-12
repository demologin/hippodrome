import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class HorseTest {
    private Horse testHorse;

    @BeforeEach
    void setUp() {
        testHorse = new Horse("Pegaus", 2.2, 22);
    }
    //--------------CONSTRUCTOR-------------------------------------------------------------------------------------------

    @ParameterizedTest
    @NullSource
    @DisplayName("testWhenFirstArgEqualsNullThenThrowIAException")
    void testHorseThreeParam(String name) {
        //Check that when passing null as the first parameter to the constructor, an IllegalArgumentException will be thrown.
        assertThrows(IllegalArgumentException.class, () -> new Horse(name, 2.2, 30.5));
    }
    @ParameterizedTest
    @NullSource
    @DisplayName("testWhenFirstArgEqualsNullThenThrowIAException")
    void testHorseTwoParam(String name) {
        // //Check that when passing null as the first parameter to the constructor, an IllegalArgumentException will be thrown.
        assertThrows(IllegalArgumentException.class, () -> new Horse(name, 2.2));
    }

    @ParameterizedTest
    @NullSource
    void testHorseWhenNameEqNullCorrectOfMessageIAE(String name) {
        //Check that when passing null as the first parameter to the constructor, the thrown exception will contain the message "Name cannot be null."
        // must be IAE with that message
        IllegalArgumentException expected = new IllegalArgumentException("Name cannot be null.");
        // taken actual IAE with actual message
        IllegalArgumentException actual = assertThrows(IllegalArgumentException.class,
                () -> new Horse(name, 2.3));
        //compare message of exceptions
        assertEquals(expected.getMessage(), actual.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"\t", " ","\u1680","\u2000",
            "\u2001", "\u2002", "\u2003", "\u2004", "\u2005",
            "\u2006", "\u2008", "\u2009", "\u3000", "\u000B",
            "\u000C", "\u001C", "\u001D", "\u001E", "\u001F"})
    void testHorseWhenNameParamIsSpaceThenThrowIAE(String name) {
        //Check that when passing an empty string or a string containing only whitespace characters (space, tab, etc.) as the first parameter to the constructor, the thrown exception will contain the message "Name cannot be blank.";
        assertThrows(IllegalArgumentException.class, () -> new Horse(name, 2.4));
    }

    @ParameterizedTest
    @ValueSource(strings = {"\t", " ","\u1680","\u2000",
            "\u2001", "\u2002", "\u2003", "\u2004", "\u2005",
            "\u2006", "\u2008", "\u2009", "\u3000", "\u000B",
            "\u000C", "\u001C", "\u001D", "\u001E", "\u001F"})
    void testHorseIAEMessageWhenNameParamIsBlank(String name) {
        //Check that when passing an empty string or a string containing only whitespace characters (space, tab, etc.) as the first parameter to the constructor, the thrown exception will contain the message "Name cannot be blank.";
        //expected message from IAE
        IllegalArgumentException expected = new IllegalArgumentException("Name cannot be blank.");
        //actual message from IAE
        IllegalArgumentException actual = assertThrows(IllegalArgumentException.class, () -> new Horse(name, 2.45));
        //check
        assertEquals(expected.getMessage(), actual.getMessage());
    }

    @ParameterizedTest
    @CsvSource(value = {"-1.32,23.2", "-52.32,10.0", "23.4,-43.2","3.2,-12.2",
    "-2.4,-5.0"})
    void testHorseWhenSpeedAndDistanceIsNegativeThenThrowIAE(double speed, double distance) {
        //Check, when give to constructor second param negative number, will throw IllegalArgumentException;
        assertThrows(IllegalArgumentException.class, () -> new Horse("Pegaus", speed, distance));
    }

    @ParameterizedTest
    @CsvSource(value = {"-1.23", "-32.2", "-0.1", "-5.5"})
    void testHorseWhenSpeedNegativeThenIAEWithCorrectMessage(double speed) {
        //Check that when passing a negative number as the second parameter to the constructor, the thrown exception will contain the message "Speed cannot be negative.";
        //expected IAE message
        IllegalArgumentException expected = new IllegalArgumentException("Speed cannot be negative.");
        //actual IAE message
        IllegalArgumentException actual = assertThrows(IllegalArgumentException.class, () -> new Horse("Pegaus", speed, 22));
        //check
        assertEquals(expected.getMessage(), actual.getMessage());
    }

    @ParameterizedTest
    @CsvSource(value = {"-1.23", "-32.2", "-0.1", "-5.5"})
    void testHorseWhenDistanceNegativeThenIAEWithCorrectMessage(double distance) {
        //Check that when passing a negative number as the third parameter to the constructor, the thrown exception will contain the message "Distance cannot be negative.";
        //expected IAE message
        IllegalArgumentException expected = new IllegalArgumentException("Distance cannot be negative.");
        //actual IAE message
        IllegalArgumentException actual = assertThrows(IllegalArgumentException.class, () -> new Horse("Pegaus", 2.2, distance));
        //check
        assertEquals(expected.getMessage(), actual.getMessage());
    }
    //--------------------------------------------------------------------------------------------------------------------------


    @Test
    void testMethodGetName_AssertReturnsName() {
        //Check that the method returns the string that was passed as the first parameter to the constructor;
        String expected = "Pegaus";
        String actual = testHorse.getName();
        //compare assert and actual
        assertEquals(expected, actual);
    }

    @Test
    void testMethodGetSpeed_AssertReturnsSpeed() {
        //Check that the method return the double that was passed as the second parameter to the constructor;
        double expected = 2.2;
        double actual = testHorse.getSpeed();
        //compare assert and actual
        assertEquals(expected, actual);
    }

    @Test
    void testMethodGetDistance_AssertReturnsDistance() {
        //Check that the method return the double that was passed as the third parameter to the constructor;
        double expected = 22;
        double actual = testHorse.getDistance();
        //compare assert and actual
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @CsvSource(value = {"0.4", "0.5",
                        "0.6", "0.7"})
    void testVerifyCallsOfGetRandomDouble(double random) {
        //Check that the method internally calls the getRandomDouble method with parameters 0.2 and 0.9
        try(MockedStatic<Horse> staticMockedHorse = Mockito.mockStatic(Horse.class)) {
            staticMockedHorse.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(random);
            testHorse.move();
            //check was the static method called
            staticMockedHorse.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }

    @ParameterizedTest
    @CsvSource(value = {"0.4", "0.5", "0.6", "0.7"})
    void testMoveMethod_AssertForRightDistanceVal(double random) {
        //Check that the method assigns the distance the value calculated by the formula: distance + speed * getRandomDouble(0.2, 0.9)
        try(MockedStatic<Horse> staticMockedHorse = Mockito.mockStatic(Horse.class)) {
            staticMockedHorse.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(random);
            //assert distance after method move
            double assertDist = testHorse.getDistance() + testHorse.getSpeed() * Horse.getRandomDouble(0.2, 0.9);
            testHorse.move();
            //actual distance after method move
            double actualDist = testHorse.getDistance();
            assertEquals(assertDist, actualDist);
        }
    }


}