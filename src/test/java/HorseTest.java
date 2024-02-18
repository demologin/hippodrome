import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class HorseTest {

    @Test
    void constructorWhenNameIsNull(){
        Throwable exception = assertThrows(IllegalArgumentException.class,
                        () -> new Horse(null, 3.4, 4.2));
        assertEquals("Name cannot be null.", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "  ", "\t", "\n\r", "\f"})
    void constructorWhenNameIsWhiteSpaces(String name) {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> new Horse(name, 3.4, 4.2));
        assertEquals("Name cannot be blank.", exception.getMessage());
    }

    @Test
    void constructorWhenSpeedIsNegative(){
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> new Horse("name", -1.3, 4.2));
        assertEquals("Speed cannot be negative.", exception.getMessage());
    }

    @Test
    void constructorWhenDistanceArgumentNegative(){
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> new Horse("name", 3.2, -2.1));
        assertEquals("Distance cannot be negative.", exception.getMessage());
    }

    @Test
    void getName() {
        String name = "Alex";
        Horse horse = new Horse(name, 3.2, 4.2);
        assertEquals(name, horse.getName());
    }

    @Test
    void getSpeed() {
        double speed = 3.2;
        Horse horse = new Horse("Alex", speed, 4.2);
        assertEquals(speed, horse.getSpeed());
    }

    @Test
    void getDistanceWhenAreThreeParams() {
        double dist = 4.2;
        Horse horse = new Horse("Alex", 3.2, dist);
        assertEquals(dist, horse.getDistance());
    }

    @Test
    void getDistanceWhenAreTwoParams() {
        Horse horse = new Horse("Alex", 3.2);
        assertEquals(0, horse.getDistance());
    }

    @Test
    void move() {
        try (MockedStatic<Horse> mockStaticHorse = Mockito.mockStatic(Horse.class)) {
            // given
            double returnForGetRandomDouble = 0.5;
            double speed = 10;
            Horse horse = new Horse("Alex", speed);
            double expected = horse.getDistance() + horse.getSpeed() * returnForGetRandomDouble;

            //when
            // creating mock for getRandomDouble
            mockStaticHorse.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(returnForGetRandomDouble);
            horse.move();
            // does getRandomDouble calls 1 time?
            mockStaticHorse.verify(() -> Horse.getRandomDouble(0.2, 0.9));

            assertEquals(expected, horse.getDistance());
        }
    }
}