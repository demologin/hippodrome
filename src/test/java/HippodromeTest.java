import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HippodromeTest {

    @Test
    void whenConstructorIsNull_throwIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
    }

    @Test
    void whenConstructorHasNull_illegalArgumentExceptionContainsExpectedMessage() {
        //when
        Exception actualException = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
        String actualExceptionMessage = actualException.getMessage();
        //then
        String expectedMessage = "Horses cannot be null.";
        assertEquals(expectedMessage, actualExceptionMessage);
    }

    @Test
    void whenConstructorHasEmptyList_throwIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(new ArrayList<>()));
    }

    @Test
    void whenConstructorHasEmptyList_illegalArgumentExceptionContainsExpectedMessage() {
        //when
        Exception actualException = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(new ArrayList<>()));
        String actualExceptionMessage = actualException.getMessage();
        //then
        String expectedMessage = "Horses cannot be empty.";
        assertEquals(expectedMessage, actualExceptionMessage);
    }

    @Test
    void whenGetHorses_returnCorrectListOfHorses() {
        //given
        List<Horse> testHorses = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            testHorses.add(new Horse("Horse" + i, i));
        }
        List<Horse> expectedHorses = testHorses;
        //when
        List<Horse> actualHorses = new Hippodrome(testHorses).getHorses();
        //then
        assertEquals(expectedHorses, actualHorses);
    }

    @Test
    void whenMove_thenEachHorseMustMove() {
        //given
        List<Horse> mockedHorses = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            mockedHorses.add(Mockito.mock(Horse.class));
        }
        //when
        new Hippodrome(mockedHorses).move();
        //then
        for (Horse mockedHorse : mockedHorses) {
            Mockito.verify(mockedHorse).move();
        }
    }

    @Test
    void whenGetWinner_returnCorrectHorse() {
        //given
        Horse horse1 = new Horse("h1", 11.1, 111);
        Horse horse2 = new Horse("h2", 9.9, 222);
        Horse horse3 = new Horse("h3", 3.3, 33.3);
        List<Horse> testHorses = List.of(horse1, horse2, horse3);
        //when
        Horse actualWinner = new Hippodrome(testHorses).getWinner();
        //then
        Horse expectedWinner = horse2;
        assertSame(expectedWinner, actualWinner);
    }
}