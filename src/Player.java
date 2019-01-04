import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;


public class Player {

	String name;
	Integer numberOfDice;
	private Integer livesLeft;
	Integer numberOfBluffs;
	StringSorter stringSorter;
	Scanner scanner;
	RollClassifier rollClassifier;

	public Player(String playerName){
		name = playerName;
		numberOfDice = 5;
		livesLeft = 1;
		numberOfBluffs = 0;
		stringSorter = new StringSorter();
		scanner = new Scanner(System.in);
		rollClassifier = new RollClassifier(numberOfDice);
	}

	public static void main(String[] args) {
		Dice dice = new Dice(5);
		Player testPlayer = new Player("Alfred");
		ArrayList<Integer> testDice = new ArrayList<>();
		testDice.add(4);
		testDice.add(4);
		testDice.add(4);
		testDice.add(3);
		testDice.add(6);
		dice.setRealDice(testDice);
		dice.printDice();
		String previouslyStatedDice = "44336";
		// Todo: put the above in a unit test

		//testPlayer.chooseDiceToRollAutomatically(dice, previouslyStatedDice);
		System.out.println(testPlayer.rollClassifier.getAllRollsContaining("36"));
	}


	/* Todo: Interesting probability question

	The current approach of rolling the die that will give the player the best hand is suboptimal.

	Instead, I want the bot to roll the dice/die that will give him the highest chance
	of exceeding the previous player's throw. There will be a way to calculate this.

	How do I calculate the optimal number of dice to throw to have the highest chance of beating the
	previous player's dice ranking?


	*/

	//todo: continue here
	private void calculateNumberOfSuperiorPossibleRollsByRollingNDice(){
		// todo: get all combination  of the two die not to roll. Put this in separate method.

		// todo: find all combinations with the dice not to roll YES

		// todo: rank those combinations. Find the number of higher ranking combinations.

	}

	public void chooseDiceToRollAutomatically(
			Dice dice, String diceThatPreviousPlayerSaysHeHas){
		Integer rankOfDiceToBeat = getRankOfDiceToBeat(
				diceThatPreviousPlayerSaysHeHas
		);


		for (int dieIndexToRoll = 0; dieIndexToRoll < numberOfDice; dieIndexToRoll++) {
			Integer numberOfHigherRankingPossibilities = calculateNumberOfSuperiorPossibleRollsForOneDie(
					dice, dieIndexToRoll
			);
		}
	}


	private Integer getRankOfDiceToBeat(String diceThatPreviousPlayerSaysHeHas){
		return rollClassifier.getRank(diceThatPreviousPlayerSaysHeHas);
	}


	private boolean isHigher(int possibleRank, int previousRank){
		return possibleRank > previousRank;
	}


	private Integer calculateNumberOfSuperiorPossibleRollsForOneDie(Dice dice, int dieIndex){
		// This works but is limited to one die. I need an extensible solution.
		ArrayList<Integer> currentDice = dice.getRealDice();
		Integer currentDiceRank = rollClassifier.getRank(getString(currentDice));
		ArrayList<String> higherRankingDice = new ArrayList<>();
		ArrayList<Integer> possibleDice = (ArrayList<Integer>) dice.getRealDice().clone();

		for (int i = 1; i <= 6; i++){
			possibleDice.set(dieIndex, i);
			Integer possibleDiceRank = rollClassifier.getRank(getString(possibleDice));
			if (possibleDiceRank > currentDiceRank){
				higherRankingDice.add(getString(possibleDice));
			}
		}
		System.out.println(
				"Probability of increasing rank by rolling " + dieIndex +
						"= " + higherRankingDice.size()
		);
		return higherRankingDice.size();
	}




	public String getString(ArrayList<Integer> integerArrayList){
		StringBuilder stringBuilder = new StringBuilder();
		for (Integer integer : integerArrayList){
			stringBuilder.append(String.valueOf(integer));
		}
		return stringBuilder.toString();
	}

	public boolean decideToRollDice(){
		System.out.println(
				"Options:\n" +
						"- Press 1 to choose which dice to roll\n" +
						"- Press 2 to announce the hand to pass to the next player\n"
		);
		// Todo: modify the below so that non-int entries also re-prompt user.
		Integer command = scanner.nextInt();
		if (command.equals(1)){
			return true;
		}
		if (command.equals(2)){
			return false;
		}
		else {
			System.out.println("Invalid choice. Please choose again.");
			return decideToRollDice();
		}
	}

//	private void chooseDiceToRollManually(Dice dice){
//		dice.printDice();
//		System.out.println(
//				"Enter the dice that you would like to roll. " +
//				"Press X after you have selected the dice"
//		);
//		Integer[] diceToRoll = new Integer[0];
//		while (true){
//			if (diceToRoll.length == 5){
//				System.out.println("You entered 5 dice"); // Todo: allow player to finalise their dice selection
//				break;
//			}
//			String input = scanner.next();
//			if (input.toLowerCase().contains("x")){
//				break;
//			}
//			// Todo: complete the below and test
//			Integer dieToRoll = Integer.valueOf(input);
//			if (!dice.isDieInDice(dieToRoll)){
//				System.out.println(
//						dieToRoll + " is not present in the dice.");
//				continue;
//			}
//			ArrayUtils.add(diceToRoll, dieToRoll);
//		}
//	}


	public Integer getLivesLeft(){
		return livesLeft;
	}

	public void loseLife(){
		livesLeft--;
	}

}
