import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


@ExtendWith(MockitoExtension.class)
public class HorseTest {
    @Test
    public void constructor_firstValueNull_throwsException() {
        Throwable exception = Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new Horse(null, 3, 3);
                }
        );
    }

    @Test
    public void constructor_firstValueNull_throwsExceptionWithMessage() {
        Throwable exception = Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new Horse(null, 3, 3);
                    throw new IllegalArgumentException("Name cannot be null.");
                }
        );
        Assertions.assertEquals("Name cannot be null.", exception.getMessage());
    }

    @ParameterizedTest
    @CsvSource({
            "'', 1, 1",
            "' ', 2, 2",
            "'\t', 3, 3",
            "'\n', 4, 4",
            "'\r\n', 5, 5"
    })
    public void constructor_firstValueEmptyOrTab_throwsException(String name, double speed, double distance) {
        Throwable exception = Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new Horse(name, speed, distance);
                }
        );
    }

    @ParameterizedTest
    @CsvSource({
            "'', 1, 1",
            "' ', 2, 2",
            "'\t', 3, 3",
            "'\n', 4, 4",
            "'\r\n', 5, 5"
    })
    public void constructor_firstValueEmptyOrTab_throwsExceptionWithMessage(String name, double speed, double distance) {
        Throwable exception = Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new Horse(name, speed, distance);
                    throw new IllegalArgumentException("Name cannot be blank.");
                }
        );
        Assertions.assertEquals("Name cannot be blank.", exception.getMessage());
    }

    @Test
    public void constructor_secondValueNegative_throwsException() {
        Throwable exception = Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new Horse("Ron", -3, 3);
                }
        );
    }

    @Test
    public void constructor_secondValueNegative_throwsExceptionWithMessage() {
        Throwable exception = Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new Horse("Ron", -3, 3);
                    throw new IllegalArgumentException("Speed cannot be negative.");
                }
        );
        Assertions.assertEquals("Speed cannot be negative.", exception.getMessage());
    }

    @Test
    public void constructor_thirdValueNegative_throwsException() {
        Throwable exception = Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new Horse("Ron", 3, -3);
                }
        );
    }

    @Test
    public void constructor_thirdValueNegative_throwsExceptionWithMessage() {
        Throwable exception = Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new Horse("Ron", 3, -3);
                    throw new IllegalArgumentException("Distance cannot be negative.");
                }
        );
        Assertions.assertEquals("Distance cannot be negative.", exception.getMessage());
    }

    @Test
    public void getName_validName_returnsSameName() {
        Horse horse = new Horse("Ferrari", 350, 400);
        String name = horse.getName();
        Assertions.assertEquals("Ferrari", name);
    }

    @Test
    public void getSpeed_validSpeed_returnsSameSpeed() {
        Horse horse = new Horse("Aston Martin", 329, 400);
        Double speed = horse.getSpeed();
        Assertions.assertEquals(329, speed);
    }

    @Test
    public void getDistance_validDistance_returnsSameDistance() {
        Horse horse = new Horse("Kick", 319, 400);
        Double distance = horse.getDistance();
        Assertions.assertEquals(400, distance);
    }

    @Test
    public void getDistance_constructorWithoutDistance_returnsZero() {
        Horse horse = new Horse("Alpine", 410);
        Double distance = horse.getDistance();
        Assertions.assertEquals(0, distance);
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.2, 0.5, 0.9})
    public void move_callGetRandomDouble_returnGetRandomDouble(double value) {
        try (MockedStatic<Horse> horseMock = Mockito.mockStatic(Horse.class)) {
            horseMock.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(value);
            Horse horse = new Horse("Alfa", 370, 285);
            horse.move();
            Assertions.assertEquals(285 + 370 * value, horse.getDistance());
            horseMock.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }
}
