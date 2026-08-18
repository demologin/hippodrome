import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class HippodromeTest {
	private final static String NULL_EXCEPTION_MESSAGE = "Horses cannot be null.";
	private final static String EMPTY_EXCEPTION_MESSAGE = "Horses cannot be empty.";
	private final static Integer HORSES_AMOUNT_MOCK = 50;
	private final static Integer HORSES_AMOUNT = 30;


	@Test
	public void argumentIsNullTest() {
		IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
		assertEquals(NULL_EXCEPTION_MESSAGE, e.getMessage());
	}

	@Test
	public void argumentIsEmptyTest() {
		IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
				() -> new Hippodrome(new ArrayList<>()));
		assertEquals(EMPTY_EXCEPTION_MESSAGE, e.getMessage());
	}

	@Test
	public void getHorsesTest() {
		List <Horse> horses = getHorses();
		Hippodrome h = new Hippodrome(horses);
		assertEquals(horses, h.getHorses());
	}

	private List<Horse> getHorses() {
		List<Horse> horses = new ArrayList<>();
		for (int i = 0; i < HORSES_AMOUNT; i++) {
			horses.add(new Horse("horse " + i, i + 0.0, i + 0.0));
		}
		return horses;
	}

	@Test
	public void moveTest() {
		List <Horse> horses = getMockHorses();
		Hippodrome h = new Hippodrome(horses);
		h.move();
		for (Horse horse : horses ){
			Mockito.verify(horse, Mockito.times(1)).move();
		}
	}

	private List<Horse> getMockHorses() {
		List<Horse> horses = new ArrayList<>();
		for (int i = 0; i < HORSES_AMOUNT_MOCK; i++) {
			Horse horseMock = Mockito.mock(Horse.class);
			horses.add(horseMock);
		}
		return horses;
	}

	@Test
	public void getWinnerTest() {
		List <Horse> horses = getHorses();
		Horse fastestHorse = horses.getLast();
		Hippodrome h = new Hippodrome(horses);
		assertEquals(fastestHorse, h.getWinner());
	}
}