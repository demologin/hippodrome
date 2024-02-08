import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class HorseTest {

//    @BeforeAll
//    public static void init(){
//        System.out.println("BeforeAll init() method called");
//    }
    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    public void nullHorse() {

        String name = null;
        double speed = 10.0;
        double distance = 100.0;


        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Horse(name, speed, distance));
        assertEquals("Name cannot be null.", exception.getMessage());

    }


    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\t", "\n", "\r", "\t\n\r", "\t  \n"})
    public void emptyHorse(String name) {

        double speed = 10.0;
        double distance = 100.0;


        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Horse(name, speed, distance));
        assertEquals("Name cannot be blank.", exception.getMessage());

    }

    @Test
    public void negativeSpeedHorse() {

        String name = "Maksim";
        double speed = -10.0;
        double distance = 100.0;


        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Horse(name, speed, distance));
        assertEquals("Speed cannot be negative.", exception.getMessage());

    }



    @Test
    public void negativeDistanceHorse() {

        String name = "Maksim";
        double speed = 10.0;
        double distance = -100.0;


        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Horse(name, speed, distance));
        assertEquals("Distance cannot be negative.", exception.getMessage());

    }




    @Test
    void getName() {


        String name = "Maksim";
        double speed = 10.0;
        double distance = 100.0;

        Horse horse = new Horse(name, speed, distance);

        assertEquals(horse.getName(),"Maksim");

    }

    @Test
    void getSpeed() {
        String name = "Maksim";
        double speed = 10.0;
        double distance = 100.0;

        Horse horse = new Horse(name, speed, distance);

        assertEquals(horse.getSpeed(),10.0);
    }

    @Test
    void getDistance() {

        String name = "Maksim";
        double speed = 10.0;
        double distance = 100.0;

        Horse horse = new Horse(name, speed, distance);

        assertEquals(horse.getDistance(),100.0);
    }

    @Test
    void move() {
    }

    @Test
    void getRandomDouble() {
    }
}