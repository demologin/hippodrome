import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HippodromeTest {

    @ParameterizedTest
    @NullSource
    @DisplayName("When the null in constructor => throw IAE")
    void whenTheNullInConstructorThrowIae(List<Horse> input) {
        assertThrows(IllegalArgumentException.class, () -> {
            new Hippodrome(input);
        });
    }

    @ParameterizedTest
    @NullSource
    @DisplayName("When the null in constructor => get Message")
    void whenTheNullInConstructorGetMessage(List<Horse> input) {
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> {
            new Hippodrome(input);
        });

        String expectResult = "Horses cannot be null.";
        String actualResult = illegalArgumentException.getMessage();

        assertEquals(expectResult, actualResult);
    }

    @ParameterizedTest
    @EmptySource
    @DisplayName("When the empty in constructor => throw IAE")
    void whenTheEmptyInConstructorThrowIae(List<Horse> input) {
        assertThrows(IllegalArgumentException.class, () -> {
            new Hippodrome(input);
        });
    }

    @ParameterizedTest
    @EmptySource
    @DisplayName("When the empty in constructor => get Message")
    void whenTheEmptyInConstructorGetMessage(List<Horse> input) {
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> {
            new Hippodrome(input);
        });

        String expectResult = "Horses cannot be empty.";
        String actualResult = illegalArgumentException.getMessage();

        assertEquals(expectResult, actualResult);
    }

    @Test
    @DisplayName("When sent horses => get the same horses in the same order")
    void whenSentHorsesGetTheSameHorsesInTheSameOrder() {
        List<Horse> expectedHorses = new ArrayList<>();

        for (int numberHorse = 0; numberHorse < 30; numberHorse++) {
            Horse newHorse = new Horse("Plotva_" + numberHorse, numberHorse);
            expectedHorses.add(newHorse);
        }

        Hippodrome hippodrome = new Hippodrome(expectedHorses);
        List<Horse> actualListHorses = hippodrome.getHorses();

        assertEquals(expectedHorses, actualListHorses);
    }

    @Test
    @DisplayName("When all horses move => verify")
    void whenAllHorsesMoveVerify() {
        List<Horse> mockedHorses = new ArrayList<>();

        for (int numberHorse = 0; numberHorse < 50; numberHorse++) {
            mockedHorses.add(Mockito.mock(Horse.class));
        }

        Hippodrome hippodrome = new Hippodrome(mockedHorses);
        hippodrome.move();

        for (Horse mockedHorse : mockedHorses) {
            Mockito.verify(mockedHorse).move();
        }
    }

    @Test
    @DisplayName("When get winner => return Horse with big distance")
    void whenGetWinnerReturnHorseWithBigDistance() {
        List<Horse> horseList = new ArrayList<>();

        for (int numberHorse = 0; numberHorse < 30; numberHorse++) {
            Horse newHorse = new Horse("Plotva_" + numberHorse, numberHorse, numberHorse);
            horseList.add(newHorse);
        }

        Hippodrome hippodrome = new Hippodrome(horseList);

        Horse expectedWinner = hippodrome.getWinner();
        Horse actualWinner = horseList.get(29);

        assertEquals(expectedWinner, actualWinner);

    }

}