import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LiarDiceTest {

	LiarDice liarDice = new LiarDice();

	@Test
	void testNewAnnouncedHandIsNotHigher(){
		liarDice.newAnnouncedHand = "11111";
		liarDice.previousAnnouncedHand = "22222";
		assertTrue(liarDice.newAnnouncedHandIsNotHigher());
	}

	@Test
	void getNextPlayerFrom() {
	}

	@Test
	void clearScreen() {
	}

	@Test
	void containsOnlyIntegers() {
	}
}