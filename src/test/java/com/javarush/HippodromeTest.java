package com.javarush;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockStatic;

class HippodromeTest {
    static  Hippodrome hippodrome;
    static List<Horse> horses ;

    @BeforeAll
    static  void setUp(){
        horses = new ArrayList<Horse>();
        for (int i = 0; i < 30; i++) {
            horses.add(new Horse("Конек горбунок", i , i));
        }
        hippodrome = new Hippodrome(horses);
    }

    @Test
    void createHippodromeNullName(){
        assertThrows(
                IllegalArgumentException.class,
                () -> new Hippodrome(null)
        );
    }

    @Test
    void createHippodromeNullNameMessage(){
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Horse(null, 1, 1)
        );
        assertEquals("Horses cannot be null.", exception.getMessage());
    }

    @Test
    void createHippodromeWithAnEmptyList(){
        assertThrows(
                IllegalArgumentException.class,
                () -> new Hippodrome(new ArrayList<Horse>())
        );
    }

    @Test
    void createHippodromeWithAnEmptyListMessage(){
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new ArrayList<Horse>()
        );
        assertEquals("Horses cannot be empty.", exception.getMessage());
    }

    @Test
    void getHorses() {
        assertIterableEquals(horses, hippodrome.getHorses());
    }


    @Test
    void move() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            horses.add(Mockito.mock(Horse.class));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        hippodrome.move();
        for (Horse horse : horses){
            Mockito.verify(horse).move();
        }
    }

    @Test
    void getWinner() {
        assertEquals(29,hippodrome.getWinner().getDistance());
    }
}