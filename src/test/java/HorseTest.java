import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(Parameterized.class)
class HorseTest {


    private static Horse horse;
    double dist;
    double speed;
    String horsName;

    HorseTest(){
        this.speed  = 3;
        this.dist   = 0.4;
        this.horsName = "Lucky";
    }
    @BeforeEach
    void setUp() {
        horse = new Horse(horsName,speed,dist);
    }

    @AfterEach
    void tearDown() {
    }

    //== test move method formula verify ==

    @ParameterizedTest
    @ValueSource(doubles = {0.2, 0.5, 0.8})
    void methodMoveFormulaVerify(double param){
        double expDistance = dist + speed * param;
        try(MockedStatic<Horse> horseMock = Mockito.mockStatic(Horse.class)){
            horseMock.when(()->Horse.getRandomDouble(0.2,0.9)).thenReturn(param);
            horse.move();
            double returnDistance = horse.getDistance();

            assertEquals(expDistance, returnDistance);
        }
    }

    //== test move method getRandomDouble verify ==
    @Test
    void methodMoveVerify(){
        try(MockedStatic<Horse> horseMock = Mockito.mockStatic(Horse.class)){
            this.horse.move();
            horseMock.verify(()->Horse.getRandomDouble(0.2,0.9));
        }
    }

    //== test getDistance method ==
    @Test
    void methodGetDistance(){
        double returnDistance = horse.getDistance();
        assertEquals(dist, returnDistance);
    }

    //== test getSpeed method ==
    @Test
    void methodGetSpeed(){
        double returnSpeed = horse.getSpeed();
        assertEquals(speed, returnSpeed);
    }

    //== test getName method  ==
    @Test
    void methodGetName(){
        String returnName = horse.getName();
        assertEquals(horsName, returnName);
    }

    //== test Negtive distance constructor exception (assertEquals) ==
    @Test
    void constructNegDistanceExceptionAssEquals(){
        dist     = -0.4;
        String message  = "Distance cannot be negative.";

        try{
            Horse hr = new Horse(horsName, speed, dist);
        }catch (IllegalArgumentException e){
            assertEquals(message, e.getMessage());
        }
    }

    //== test Negative distance constructor exception ==
    @Test
    void constructNegDistanceException(){
        assertThrows(IllegalArgumentException.class,
                ()->new Horse(horsName, speed, dist * -1));
    }

    //== test Negtive speed constructor exception (assertEquals) ==
    @Test
    void constructNegSpeedExceptionAssEquals(){

        String message  = "Speed cannot be negative.";

        try{
            Horse hr = new Horse(horsName, speed * -1, dist);
        }catch (IllegalArgumentException e){
            assertEquals(message, e.getMessage());
        }
    }

    //== test Negative Speed constructor exception ==
    @Test
    void constructNegSpeedException(){

        assertThrows(IllegalArgumentException.class,
                     ()->new Horse(horsName, speed * -1, dist));
    }

    //== test Empty Name constructor exception (assertEquals) ==
    @ParameterizedTest
    @ValueSource(
            strings = {"","\t","\n","\s","\f","\f","\u0085","\u2028","\u2029"}
    )
    void constructBlankNameExceptionAssEquals(String param){

        String message  = "Name cannot be blank.";

        try{
            horse = new Horse(param, speed, dist);
        }catch (IllegalArgumentException e){
            assertEquals(message, e.getMessage());
        }
    }

    //== test Empty Name constructor exception (IllegalArgumentException) ==
    @ParameterizedTest
    @ValueSource(
            strings = {"","\t","\n","\s","\f","\f","\u2028","\u2029"}
    )
    void constructEmptyNameExceptionIllegalArgument(String param){
        assertThrows(IllegalArgumentException.class,()->new Horse(param, speed, dist));
    }

    //== test null Name constructor exception (assertEquals) ==
    @Test
    void constructNullNameExceptionAssEquals(){
        String message  = "Name cannot be null.";
        try{
            horse = new Horse(null, speed, dist);
        }catch (IllegalArgumentException e){
                assertEquals(message, e.getMessage());
        }
    }

    //== test null Name constructor exception (assertThrows)==
    @Test
    void constructNullNameException(){
        assertThrows(IllegalArgumentException.class,
                     ()->new Horse(null, speed, dist));
    }

}