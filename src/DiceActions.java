
import java.util.Arrays;
import java.util.Random;


// Todo: add DiceRanker as a parent to DiceActions
public class DiceActions {

	final int numberOfDice = 5; //todo: alter this line
	int[] dice = new int[numberOfDice];
	Random random = new Random();
	int rank = 0;


	public DiceActions(){
		rollAllDice();
	}

	public void rollAllDice(){
		for (int i = 0; i < numberOfDice; i++){
			rollDie(i);
		}
	}

	public void rollDie(int die){
		dice[die] = random.nextInt(6) + 1;
	}

	public void printDice(){
		System.out.print("DiceActions:\t");
		for (int die : dice){
			System.out.print(die + "\t");
		}
		System.out.println();
	}

	public String getDice(){
		return Arrays.toString(dice);
	}

	public static void main(String[] args) {
		DiceActions diceActions = new DiceActions();
		diceActions.printDice();
		System.out.println(diceActions.getDice());
	}

	// How to calculate dice score and update class attribute score?
	/* high number, pair, two pair, three of a kind, straight, four of a kind, five of a kind */

}
