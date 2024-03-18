import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class HippodromeTest {
    private static Hippodrome hippodrome;
    private static ArrayList<Horse> horsesList;
    private static String message = "";

    HippodromeTest(){
        this.horsesList = new ArrayList<>();
    }
    void initHorsesList(int horsesCount){

        if(!horsesList.isEmpty()){horsesList.clear();}

        Random rnd = new Random();
        for (int i = 0; i < horsesCount ;i++){
            double speed = rnd.nextDouble(9.0  )+1.0;
            double dist = rnd.nextDouble(1.0  )+0.1;
            horsesList.add(new Horse("Horse" + i, speed, dist));
        }
    }
    void initHorsesMock(int countMock){

        if(!horsesList.isEmpty()){horsesList.clear();}

        for (int i = 0; i < countMock ;i++){
            horsesList.add(Mockito.mock(Horse.class));
        }

    }
    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {
    }


//== test methods == ++
    @Test
    void methodGetWinner(){
        initHorsesList(5);
        hippodrome = new Hippodrome(horsesList);
        Horse expectWinner = horsesList.stream().max(Comparator.comparing(Horse::getDistance)).get();
        Horse actualWinner = hippodrome.getWinner();
        assertEquals(expectWinner,actualWinner);
    }
    @Test
    void methodMove(){
        initHorsesMock(50);
        hippodrome = new Hippodrome(horsesList);
        hippodrome.move();
        for (Horse hoseMock: horsesList){
            Mockito.verify(hoseMock).move();
        }
    }
    @Test
    void methodGetHorses(){
        initHorsesList(30);
        hippodrome = new Hippodrome(horsesList);
        assertIterableEquals(horsesList,hippodrome.getHorses());
    }
//== test methods == --

//== test constructor == ++
    @Test
    void constructEmptyListCorrectMessage(){
        message = "Horses cannot be empty.";
        try{
            hippodrome = new Hippodrome(new ArrayList<>());
        }catch (IllegalArgumentException e){
            assertEquals(message, e.getMessage());
        }
    }
    @Test
    void constructEmptyListIllegalArgumentExeption(){
        assertThrows(IllegalArgumentException.class,
                    ()->new Hippodrome(new ArrayList<>()));
    }
    @Test
    void constructNullParamCorrectMessage(){
        message = "Horses cannot be null.";
        try{
            hippodrome = new Hippodrome(null);
        }catch (IllegalArgumentException e){
            assertEquals(message, e.getMessage());
        }
    }
    @Test
    void constructNullParamIllegalArgumentExeption(){
        assertThrows(IllegalArgumentException.class,
                    ()->new Hippodrome(null));
    }
//== test constructor == --

}