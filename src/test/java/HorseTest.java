import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.MockedConstruction;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class HorseTest {
    @Mock
    Horse horse;

    @Test
    void testConstructorWithArgumentFirst(){
        IllegalArgumentException iae = assertThrows(IllegalArgumentException.class, ()->new Horse(null, 1.1, 1.1));
        assertEquals("Name cannot be null.", iae.getMessage());

    }
    @ParameterizedTest
    @MethodSource("argsProviderFactory")
    void testConstructorWithParametr(String argument){
        IllegalArgumentException iae = assertThrows(IllegalArgumentException.class, ()->new Horse(argument, 1.1, 1.1));
        assertEquals("Name cannot be blank.", iae.getMessage());
    }
    static Stream<String> argsProviderFactory() {
        return Stream.of("", " ", "\t", "\n");
    }

    @Test
    void testConstructorWithArgumentSecond(){
        IllegalArgumentException iae = assertThrows(IllegalArgumentException.class, ()->new Horse("Ara", -1.1, 1.1));
        assertEquals("Speed cannot be negative.", iae.getMessage());

    }
    @Test
    void testConstructorWithArgumentThird(){
        IllegalArgumentException iae = assertThrows(IllegalArgumentException.class, ()->new Horse("Ara", 1.1, -1.1));
        assertEquals("Distance cannot be negative.", iae.getMessage());

    }


    @org.junit.jupiter.api.Test
    void getName() {
        horse = new Horse("Any", 1.1);
        assertEquals("Any", horse.getName());
    }

    @org.junit.jupiter.api.Test
    void getSpeed() {
        horse = new Horse("Any", 1.1);
        assertEquals(1.1, horse.getSpeed());
    }

    @org.junit.jupiter.api.Test
    void getDistance() {
        horse = new Horse("Any", 1.1, 2.1);
        assertEquals(2.1, horse.getDistance());
        horse = new Horse("Any", 1.1);
        assertEquals(0, horse.getDistance());
    }

    @ParameterizedTest
    @ValueSource(doubles = { 1.1, 2.3, 3.8 })
    void move(double argument) {
        try (MockedStatic<Horse> utilities =  mockStatic(Horse.class)) {
            Horse horse1 = new Horse("A", 1.1, 3.7);
            horse1.move();
            utilities.verify(()->Horse.getRandomDouble(0.2, 0.9));

            assertEquals((3.7+1.1*argument), horse1.getDistance()+horse1.getSpeed()*argument);
        }
    }




}