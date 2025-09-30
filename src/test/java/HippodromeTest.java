import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;





public class HippodromeTest {

    @Test
    public void NullHippodromeTest(){
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
    }
    @Test
    public void NullHippodromeTestMessage(){
        Throwable throwable = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
        assertEquals("Horses cannot be null.", throwable.getMessage());
    }

    @Test
    public void EmptyListHippodromeTest(){
        List<Horse> horses = new ArrayList<>();
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(horses));
    }
    @Test
    public void EmptyListHippodromeTestMessage(){
        List<Horse> horses = new ArrayList<>();
        Throwable throwable = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(horses));
        assertEquals("Horses cannot be empty.", throwable.getMessage());
    }

    @Test
    public void SameListHippodrome(){
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 30; i++) {

            horses.add(new Horse("hoh", 2, i));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        assertEquals(horses, hippodrome.getHorses());
    }


    @Test
    public void FiftyMoveTest(){
        List<Horse> horseList = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            Horse horseMock = mock(Horse.class);
            horseList.add(horseMock);

        }
        Hippodrome hippodrome = new Hippodrome(horseList);
        hippodrome.move();

        for (Horse horse:horseList){
            Mockito.verify(horse).move();
        }
    }
    @Test
    void getWinnerHorseWithMaxDistance() {
        Horse h1 = new Horse("A", 1.0, 10.0);
        Horse h2 = new Horse("B", 1.0, 20.0);
        Horse h3 = new Horse("C", 1.0, 15.0);

        Hippodrome hippodrome = new Hippodrome(List.of(h1, h2, h3));

        assertEquals(h2, hippodrome.getWinner());
    }
}