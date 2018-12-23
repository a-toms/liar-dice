
import java.util.ArrayList;
import java.util.Random;

public class Dice {

	final int numberOfDice = 6;
	int[] dice = new int[numberOfDice];
	Random random = new Random();
	int score = 0;


	public Dice(){
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
		System.out.print("Dice:\t");
		for (int die : dice){
			System.out.print(die + "\t");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		Dice dice = new Dice();
		dice.printDice();
		dice.rollAllDice();
		dice.printDice();
	}

	// How to calculate dice score and update class attribute score?
	/* high number, pair, two pair, three of a kind, straight, four of a kind, five of a kind */

}
