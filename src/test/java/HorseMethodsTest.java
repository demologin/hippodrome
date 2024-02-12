import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
class HorseMethodsTest {
    @Test
    void getNameReturnName() {
        String name = "Apple";
        Horse horseGetNameApple = new Horse(name, 1);
        Assertions.assertEquals(name, horseGetNameApple.getName());
    }
    @Test
    void getSpeedReturnSpeed() {
        double speed = 2.1;
        Horse horseGetSpeed = new Horse("Apple", speed);
        Assertions.assertEquals(speed, horseGetSpeed.getSpeed());
    }
    @Test
    void getDistanceReturnDistance() {
        double distance = 2.1;
        Horse horseGetDistance = new Horse("Apple", 2, distance);
        Assertions.assertEquals(distance, horseGetDistance.getDistance());
    }
    @Test
    void getDistanceReturnDistanceZeroWhenNotDistanceParameter() {
        double zero = 0;
        Horse horseGetDistance = new Horse("Apple", 2);
        Assertions.assertEquals(zero, horseGetDistance.getDistance());
    }
    @Test
    void getRandomDoubleReturnCorrectValue() {
        try (MockedStatic<Horse> horseMockedStatic = Mockito.mockStatic(Horse.class)) {
            horseMockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(0.9);
            Horse horse = new Horse("Apple", 1);
            horse.move();
            Assertions.assertEquals(0.9, horse.getDistance());
        }
    }
    @Test
    void moveCausesGetRandomDouble() {
        try (MockedStatic<Horse> horseMockedStatic = Mockito.mockStatic(Horse.class)) {
            Horse horse = new Horse("Apple", 1);
            horse.move();
            horseMockedStatic.verify(()-> Horse.getRandomDouble(0.2, 0.9));
        }
    }
}