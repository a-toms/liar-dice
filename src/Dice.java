
import java.util.Arrays;
import java.util.Random;


// Todo: add RollClassifier as a parent to Dice
public class Dice extends RollClassifier {

	int numberOfDice;
	int[] dice;
	Random random;
	int rank;

	public static void main(String[] args) {
		Dice dice = new Dice(5);

	}


	public Dice(int nDice){
		super(nDice);
		random = new Random();
		numberOfDice = nDice;
		dice = new int[numberOfDice];
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
		System.out.print("Dice:\t\t");
		for (int die : dice){
			System.out.print(die + "\t");
		}
		System.out.println("\n");
	}

	public String getDice(){
		return Arrays.toString(dice);
	}


}
