package com.javarush;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.times;


class HorseTest {

    static Horse horse;
    static Horse horseWithTwoParameters;
    static String name = "Name";
    static double speed = 10.5;
    static double distance = 20.6;
    @BeforeAll
    static void setUp(){
        horse = new Horse(name, speed, distance);
        horseWithTwoParameters = new Horse(name, speed);
    }

    @Test
    void nullNameException(){
        assertThrows(
                IllegalArgumentException.class,
                () -> new Horse(null, 1, 1)
        );
    }

    @Test
    void nullNameExceptionMessage(){
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Horse(null, 1, 1)
        );
        assertEquals("Name cannot be null.", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\t", "\n", "   \t  "})
    void constructorShouldThrowExceptionForBlankName(String name){
        assertThrows(
                IllegalArgumentException.class,
                () -> new Horse(name, 1, 1)
        );
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\t", "\n", "   \t  "})
    void constructorShouldThrowExceptionForBlankNameMessage(String name){
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Horse(name, 1, 1)
        );
        assertEquals("Name cannot be blank.", exception.getMessage());
    }
    @Test
    void checkingForNegativeSpeed(){
        assertThrows(
                IllegalArgumentException.class,
                () -> new Horse(name, -1, 1)
        );
    }

    @Test
    void checkingForNegativeSpeedMessage(){
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Horse(name, -1, 1)
        );
        assertEquals("Speed cannot be negative.", exception.getMessage());
    }

    @Test
    void checkingNegativeDistance(){
        assertThrows(
                IllegalArgumentException.class,
                () -> new Horse(name, 1, -1)
        );
    }

    @Test
    void checkingNegativeDistanceMessage(){
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Horse(name, 1, -1)
        );
        assertEquals("Distance cannot be negative.", exception.getMessage());
    }

    @Test
    void getName() {
        assertEquals(name, horse.getName());
    }

    @Test
    void getSpeed() {
        assertEquals(speed, horse.getSpeed());
    }

    @Test
    void getDistance() {
        assertEquals(distance, horse.getDistance());
    }

    @Test
    void getDistanceStartingPoint() {
        assertEquals(0, horseWithTwoParameters.getDistance());
    }

    @Test
    void checkingMethodCall(){
        try (MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)){
            Horse horse = new Horse(name, speed, distance);
            horse.move();
            mockedStatic.verify(() -> Horse.getRandomDouble(0.2d, 0.9d));
        }
    }
}