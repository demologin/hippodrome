import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class HippodromeTest {
    //--------------CONSTRUCTOR-------------------------------------------------------------------------------------------
    @ParameterizedTest
    @NullSource
    void testWhenPassingNullList_AssertIAException(List<Horse> nullList) {
        //Check that when passing null to the constructor, an IllegalArgumentException will be thrown;
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(nullList));
    }

    @ParameterizedTest
    @NullSource
    void testWhenListNull_AssertCorrectMessageOfIAE(List<Horse> nullList) {
        //Check that when passing null to the constructor, the thrown exception will contain the message "Horses cannot be null."
        IllegalArgumentException expected = new IllegalArgumentException("Horses cannot be null.");
        IllegalArgumentException actual = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(nullList));
        //compare messages of IAExceptions
        assertEquals(expected.getMessage(), actual.getMessage());
    }

    @Test
    void testWhenListForConstructorIsEmpty_AssertThrowIAException() {
        //Check that when passing an empty list to the constructor, an IllegalArgumentException will be thrown;
        List<Horse> mockListHorses = Mockito.mock(List.class);
        //When the isEmpty() method is called the value will be true
        Mockito.when(mockListHorses.isEmpty()).thenReturn(true);
        //check is IAException
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(mockListHorses));
    }

    @Test
    void testWhenListForConstructorIsEmpty_AssertThrowIAExceptionWithCorrectMessage() {
        //Check that when passing an empty list to the constructor, the exception thrown will contain the message "Horses cannot be empty.";
        //Mocked list
        List<Horse> mockListHorses = Mockito.mock(List.class);
        Mockito.when(mockListHorses.isEmpty()).thenReturn(true);
        //asserted message
        IllegalArgumentException expected = new IllegalArgumentException("Horses cannot be empty.");
        //actual message
        IllegalArgumentException actual = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(mockListHorses));
        //compare messages
        assertEquals(expected.getMessage(), actual.getMessage());
    }
    //--------------------------------------------------------------------------------------------------------------------

    @Test
    void testIsListGotFromGetHorsesEqualsListPuttedToTheConstructor() {
        //Check that the method returns a list that contains the same objects and in the same sequence as the list that was passed to the constructor.
        List<Horse> horseList = new ArrayList<>();
        //add 30 horse
        double speed = 1.5;
        for (int i = 0; i < 30; i++) {
            horseList.add(new Horse("Maqueen" + i, speed, 30.5));
            speed+= 0.2;
        }
        Hippodrome hippodrome = new Hippodrome(horseList);
        //init expected and actual
        List<Horse> expected = horseList;
        List<Horse> actual = hippodrome.getHorses();
        //compare list
        assertEquals(expected, actual);
    }

    @Test
    void testIsTheEachHorseInListCallsMoveMethod() {
        //Check that the method calls the move method on all horses
        List<Horse> horses = new ArrayList<>();
        //add 50 horses
        for(int i = 0; i < 50; i++) {
            horses.add(Mockito.mock(Horse.class));
        }
        //create obj
        Hippodrome hippodrome = new Hippodrome(horses);
        hippodrome.move();
        //test verify
        for (Horse horse : horses) {
            Mockito.verify(horse).move();
        }
    }

    @Test
    void testIsWinnerMethod_ReturnHorseWithLargestDistanceValue() {
        //Check that the method returns the horse with the largest distance value.
        // preparing
        List<Horse> horses = new ArrayList<>();
        for (int i = 1; i <= 30; i++) {
            horses.add(new Horse("№" + i, i, i));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        // get values
        double expectedMax = 30.0;
        double actualMax = hippodrome.getWinner().getDistance();
        //compare values
        assertEquals(expectedMax, actualMax);
    }
}