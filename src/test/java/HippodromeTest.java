import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HippodromeTest {

    @Test
    void givenHippodrome_WhenHorsesIsNull_ThenThrowIllegalArgumentException(){
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
    }

    @Test
    void givenHippodrome_WhenHorsesIsNull_ThenThrowIllegalArgumentExceptionHaveMessageHorsesCannotBeNull(){
        //given
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
        //when
        String actual = exception.getMessage();
        //then
        String expended = "Horses cannot be null.";
        assertEquals(expended,actual);
    }

    @Test
    void givenHippodrome_WhenListIsEmpty_ThenThrowIllegalArgumentException(){
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(new ArrayList<>()));
    }

    @Test
    void givenHippodrome_WhenListIsEmpty_ThenThrowIllegalArgumentExceptionHaveMessageHorsesCannotBeEmpty(){
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(new ArrayList<>()));
        //when
        String actual = exception.getMessage();
        //then
        String expected = "Horses cannot be empty.";
        assertEquals(expected,actual);
    }

    @Test
    void getHorses() {
        //given
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            horses.add(new Horse("Horse" + i ,Math.random(),Math.random()));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        //when
        List<Horse> actual = hippodrome.getHorses();
        //then
        assertIterableEquals(horses,actual);
    }

    @Test
    void move() {
        //given
        List<Horse> horsesMock = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            horsesMock.add(Mockito.mock(Horse.class));
        }
        Hippodrome hippodrome = new Hippodrome(horsesMock);
        //when
        hippodrome.move();
        //then
        for (int i = 0; i < 50; i++) {
            Mockito.verify(horsesMock.get(i)).move();
        }
    }

    @Test
    void getWinner() {
        //given
        Horse horse1 = new Horse("Horse" + 1, Math.random(), 2.5);
        Horse horse2 = new Horse("Horse" + 2, Math.random(), 3.2);
        Horse horse3 = new Horse("Horse" + 3, Math.random(), 0.9);
        List<Horse> horses = List.of(horse1,horse2,horse3);
        Hippodrome hippodrome = new Hippodrome(horses);
        //when
        Horse actual = hippodrome.getWinner();
        //then
        Assertions.assertEquals(horse2.getName(), actual.getName());
        Assertions.assertEquals(horse2.getDistance(), actual.getDistance());
    }
}