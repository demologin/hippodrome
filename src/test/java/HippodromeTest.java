import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class HippodromeTest {

    @Test
    public void testConstructorWithNull () {
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
    }

    @Test
    public void testConstructorWithNullAndText () {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new Hippodrome(null));
        assertEquals("Horses cannot be null.", exception.getMessage());
    }

    @Test
    public void testConstructorWithEmptyList () {
        List<Horse> emptyList = Collections.emptyList();
        //создаем пустой список
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(emptyList));
        //проверяем, что при передаче пустого списка в конструктор Ипподрома выбросится IllegalArgumentException
    }

    @Test
    public void testConstructorWithEmptyListAndText () {
        List<Horse> emptyList = Collections.emptyList();
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new Hippodrome(emptyList));
        assertEquals("Horses cannot be empty.", exception.getMessage());
    }

    @Test
    public void testGetHorses() {
        List<Horse> expectedHorses = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            expectedHorses.add(new Horse("Horse " + i, 2));
        }
        Hippodrome hippodrome = new Hippodrome(expectedHorses);

        List<Horse> actualHorses = hippodrome.getHorses();

        assertEquals(expectedHorses.size(), actualHorses.size());
        assertIterableEquals(expectedHorses, actualHorses);
    }

    @Test
    void testMoveForAll50Horses() {
        //создаем список моков лошадей
        List<Horse> horseMocks = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            Horse horseMock = mock(Horse.class);
            horseMocks.add(horseMock);
        }
        //создаем экземпляр класса Hippodrome с моками лошадей
        Hippodrome hippodrome = new Hippodrome(horseMocks);
        //вызываем у этого экземпляра метод move
        hippodrome.move();

        //проверяем, что метод вызван у всех лошадей в списке
        for (Horse horseMock : horseMocks) {
            verify(horseMock).move();
        }
    }

    @Test
    void testGetWinner() {
        //создаем 3 лошадей с разной дистанцией
        Horse horse1 = new Horse("Horse 1", 2, 100);
        Horse horse2 = new Horse("Horse 2", 2.2, 150);
        Horse horse3 = new Horse("Horse 3", 2.4, 200);

        //создаем список лошадей, добавляем туда лошадей, создаем экземпляр импподрома с этим списком
        List<Horse> horses = Arrays.asList(horse1, horse2, horse3);
        Hippodrome hippodrome = new Hippodrome(horses);

        //проверяем, что метод getWinner возвращает лошадь с самым большим значением дистанции
        assertEquals(horse3, hippodrome.getWinner());
    }
}