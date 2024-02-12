import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.ArrayList;
import java.util.List;
class HippodromeMethodsTest {

    @Test
    void getHorsesReturnListHorse() {
        List<Horse> horses = getHorseList(30);
        Hippodrome hippodrome = new Hippodrome(horses);
        Assertions.assertEquals(horses, hippodrome.getHorses());
    }

    @Test
    void moveCausesHorseMethodMove() {
        List<Horse> horses = getMockHorseList(50);
        Hippodrome hippodrome = new Hippodrome(horses);
        hippodrome.move();
        for (Horse horse : horses) {
            Mockito.verify(horse).move();
        }
    }

    @Test
    void getWinnerReturnHorseWithBiggestDistance() {
        List<Horse> horses = new ArrayList<>();
        horses.add(new Horse("1", 1, 5000));
        horses.add(new Horse("2", 1));
        Hippodrome hippodrome = new Hippodrome(horses);
        Assertions.assertEquals(horses.get(0).getDistance(), hippodrome.getWinner().getDistance());
    }
    private List<Horse> getHorseList(int numberOfHorses) {
        List<Horse> horseList = new ArrayList<>();
        for (int i = 0; i < numberOfHorses; i++) {
            horseList.add(new Horse(String.valueOf(i), i));
        }
        return horseList;
    }

    private List<Horse> getMockHorseList(int numberOfHorses) {
        List<Horse> mockHorseList = new ArrayList<>();
        for (int i = 0; i < numberOfHorses; i++) {
            mockHorseList.add(Mockito.mock(Horse.class));
        }
        return mockHorseList;
    }
}