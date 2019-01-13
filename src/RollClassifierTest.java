import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class RollClassifierTest{

	RollClassifier rollClassifier = new RollClassifier(5);

	@Test
	void isFirstHandHigherThanSecond() {
		String lower = "11231";
		String higher = "13131";
		assertTrue(
				rollClassifier.isFirstHandHigherThanSecond(
						higher,
						lower
				)
		);
	}

	@org.testng.annotations.Test
	void getRank() {
	}

	@org.junit.jupiter.api.Test
	void getAllRollsContaining() {
	}

	@org.junit.jupiter.api.Test
	void getRoll() {
	}
}