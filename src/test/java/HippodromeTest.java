import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class HippodromeTest {

    @Test
    @DisplayName("When in constructor was null Then throws IAE")
    void whenInConstructorWasNullThenThrowsIae() {
        assertThrows(IllegalArgumentException.class,
                () ->
                        new Hippodrome(null)
        );
    }

    @Test
    @DisplayName("When in constructor is null Then send message")
    void whenInConstructorIsNullThenSendMessage() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () ->
                        new Hippodrome(null)
        );
        String expected = "Horses cannot be null.";
        String actual = exception.getMessage();
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("When in constructor is blank List Then throws IAE")
    void whenInConstructorIsBlankListThenThrowsIae() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Hippodrome(Collections.emptyList());
        });
    }

    @Test
    @DisplayName("When in constructor is blank List Then send message")
    void whenInConstructorIsBlankListThenSendMessage() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Hippodrome(Collections.emptyList());
        });
        String expected = "Horses cannot be empty.";
        String actual = exception.getMessage();
        assertEquals(expected, actual);
    }

    @Test
    void getHorses() {
        List<Horse> horseList = new ArrayList<>();

        for (int someHorse = 0; someHorse < 30; someHorse++) {
            Horse horse = new Horse("horse" + someHorse, someHorse, someHorse);
            horseList.add(horse);
        }

        Hippodrome hippodrome = new Hippodrome(horseList);
        assertEquals(horseList, hippodrome.getHorses());
    }

    @Test
    void move() {
        List<Horse> mockHorseList = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            mockHorseList.add(mock(Horse.class));
        }

        Hippodrome hippodrome = new Hippodrome(mockHorseList);
        hippodrome.move();

        for (Horse horse : mockHorseList) {
            verify(horse).move();
        }
    }

    @Test
    void getWinner() {
        List<Horse> horseList = new ArrayList<>();
        for (int someHorse = 0; someHorse < 30; someHorse++) {
            Horse horse = new Horse("horse" + someHorse, someHorse, someHorse);
            horseList.add(horse);
        }

        Hippodrome hippodrome = new Hippodrome(horseList);

        assertSame(horseList.get(29),hippodrome.getWinner());
    }
}
