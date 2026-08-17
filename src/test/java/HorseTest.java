import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyDouble;

class HorseTest {
	private static final String NULL_NAME_ERROR = "Horse name cannot be null";
	private static final String BLANK_NAME_ERROR = "Name cannot be blank.";
	private static final String NEGATIVE_SPEED_ERROR = "Speed cannot be negative";
	private static final String NEGATIVE_DISTANCE_ERROR = "Distance cannot be negative.";

	private static final double VALID_SPEED = 1.0;
	private static final double NEGATIVE_SPEED = -2.0;
	private static final double VALID_DISTANCE = 1.0;
	private static final double NEGATIVE_DISTANCE = -2.0;
	private static final double MOCK_RANDOM_VALUE = 0.0;
	private static final double ZERO = 0.0;
	private static final String NULL_HORSE_NAME = null;
	private static final String VALID_HORSE_NAME = "Horse";

	@Test
	public void nameIsNullExceptionMessageTest() {
		IllegalArgumentException exception =  assertThrows(IllegalArgumentException.class, () -> {
			new Horse(NULL_HORSE_NAME, VALID_SPEED);
		});
		String expected = NULL_NAME_ERROR;
		String actual = exception.getMessage();
		assertEquals(expected, actual);
	}

	@ParameterizedTest
	@ValueSource( chars = { '\t',' ' } )
	public void emptyNameTest(char character) {
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
			new Horse(String.valueOf(character), VALID_SPEED);
		});
		String expected = BLANK_NAME_ERROR;
		String actual = exception.getMessage();
		assertEquals(expected, actual);
	}

	@Test
	public void speedIsNegativeTest() {
		IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> {
			new Horse(VALID_HORSE_NAME, NEGATIVE_SPEED);
		});

		String expected = NEGATIVE_SPEED_ERROR;
		String actual = e.getMessage();
		assertEquals(expected, actual);
	}

	@Test
	public void distanceIsNegativeTest() {
		IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> {
			new Horse(VALID_HORSE_NAME, VALID_SPEED , NEGATIVE_DISTANCE);
		});

		String expected = NEGATIVE_DISTANCE_ERROR;
		String actual = e.getMessage();
		assertEquals(expected, actual);
	}

	@Test
	public void getNameTest() {
		Horse horse = new Horse(VALID_HORSE_NAME, VALID_SPEED);
		assertEquals(VALID_HORSE_NAME, horse.getName());
	}

	@Test
	public void getSpeedTest() {
		Horse horse = new Horse(VALID_HORSE_NAME, VALID_SPEED);
		assertEquals(VALID_SPEED, horse.getSpeed());
	}
	@Test
	public void getDistanceTest() {
		Horse horse = new Horse(VALID_HORSE_NAME, VALID_SPEED);
		assertEquals(VALID_DISTANCE, horse.getDistance());
	}

	@ParameterizedTest
	@ValueSource( doubles = { ZERO } )
	public void moveTest(double ZERO) {
		Horse horse = new Horse(VALID_HORSE_NAME, VALID_SPEED, VALID_DISTANCE);
		try (MockedStatic<Horse> mockStaticHorse =  Mockito.mockStatic( Horse.class)) {
			mockStaticHorse.when(()->Horse.getRandomDouble(0.2,0.9)).thenReturn(ZERO);
			horse.move();
			mockStaticHorse.verify(()->Horse.getRandomDouble(0.2,0.9));
			double expected = VALID_DISTANCE;
			double actual = horse.getDistance();
			assertEquals(expected, actual, 0.0001);
		}
	}
}