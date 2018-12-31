import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import java.util.ArrayList;
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
		testDice.add(1);
		testDice.add(4);
		testDice.add(2);
		testDice.add(3);
		testDice.add(6);
		dice.setRealDice(testDice);
		dice.printDice();
		String previouslyStatedDice = "44336";
		// Todo: put the above in a unit test

		testPlayer.chooseDiceToRollAutomatically(dice, previouslyStatedDice);

	}


	/*
	The current approach of rolling the die that will give the player the best hand is suboptimal.
	Todo: Instead, I want the bot to roll the dice/die that will give him the highest chance
	of exceeding the previous player's throw. There will be a way to calculate this.
	How do I calculate the optimal number of dice to throw to have the highest chance of beating the
	previous player's throw?

	*/


	private Integer getRankOfDiceToBeat(String diceThatPreviousPlayerSaysHeHas){
		return rollClassifier.getRank(
				diceThatPreviousPlayerSaysHeHas
		);
	}


	public void chooseDiceToRollAutomatically(
			Dice dice, String diceThatPreviousPlayerSaysHeHas
	){
		Integer rankOfDiceToBeat = getRankOfDiceToBeat(
				diceThatPreviousPlayerSaysHeHas
		);
		int possibleRank = calculateHighestPossibleRankByRollingOneDie(dice);
		isHigher(possibleRank, rankOfDiceToBeat);

		//create choose die to roll method


	}

	private boolean isHigher(int possibleRank, int previousRank){
		return possibleRank > previousRank;
	}



	private Integer calculateHighestPossibleRankByRollingOneDie(Dice dice){
		Integer highestRank = 0;
		for (int die = 0; die <= numberOfDice - 1; die++){
			Integer rank = calculateHighestPossibleRankByRollingDie(dice, die);
			if (rank > highestRank){
				highestRank = rank;
			}
		}
		return highestRank;
	}

	private Integer calculateHighestPossibleRankByRollingDie(Dice dice, int dieIndex){
		// I could alter this to find the dice to roll that has the best chance of
		// beating the previous hand, rather than getting the highest rank.
		ArrayList<Integer> currentDice = dice.getRealDice();
		Integer highestRank = 0;
		for (int i = 1; i <= 6; i++){
			ArrayList<Integer> possibleDice = (ArrayList<Integer>) dice.getRealDice().clone();
			possibleDice.set(dieIndex, i);
			Integer rank = rollClassifier.getRank(getString(possibleDice));
			if (rank > highestRank){
				highestRank = rank;
			}
		}
		return highestRank;
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
