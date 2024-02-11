import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class HorseTest {
    @Test
    public void testConstructorNullParameter() {
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
    }

    @Test
    public void testConstructorNullParameterMessage() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
        assertEquals("Horses cannot be null.", exception.getMessage());
    }

    @Test
    public void testConstructorEmptyList() {
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(Arrays.asList()));
    }

    @Test
    public void testConstructorEmptyListMessage() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(Arrays.asList()));
        assertEquals("Horses cannot be empty.", exception.getMessage());
    }

    @Test
    public void testGetHorses() {
        // Создание списка из 30 разных лошадей
        List<Horse> horses = createListOf30DifferentHorses();
        Hippodrome hippodrome = new Hippodrome(horses);
        assertEquals(horses, hippodrome.getHorses());
    }

    @Test
    public void testMove() {
        // Создание списка из 50 моков лошадей
        List<Horse> mockHorses = createListOf50MockHorses();
        Hippodrome hippodrome = new Hippodrome(mockHorses);
        hippodrome.move();
        for (Horse horse : mockHorses) {
            verify(horse).move();
        }
    }

    @Test
    public void testGetWinner() {
        // Создание списка из 3 лошадей с разными значениями distance
        List<Horse> horses = Arrays.asList(
                new Horse("Horse1", 10, 100),
                new Horse("Horse2", 15, 150),
                new Horse("Horse3", 20, 200)
        );
        Hippodrome hippodrome = new Hippodrome(horses);
        assertEquals(horses.get(2), hippodrome.getWinner());
    }

    private List<Horse> createListOf30DifferentHorses() {
        List<Horse> horses = new ArrayList<>();
        Random random = new Random();
        for (int i = 1; i <= 30; i++) {
            String name = "Horse" + i;
            double speed = 5 + random.nextDouble() * 20; // Генерация случайной скорости от 5 до 25
            double distance = random.nextInt(1000); // Генерация случайной дистанции до 1000
            horses.add(new Horse(name, speed, distance));
        }
        return horses;
    }

    private List<Horse> createListOf50MockHorses() {
        List<Horse> mockHorses = new ArrayList<>();
        for (int i = 1; i <= 50; i++) {
            Horse mockHorse = Mockito.mock(Horse.class);
            mockHorses.add(mockHorse);
        }
        return mockHorses;
    }
}