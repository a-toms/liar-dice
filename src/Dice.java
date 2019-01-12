
import org.apache.commons.lang3.ArrayUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;



public class Dice extends RollClassifier {

	int numberOfDice;
	private ArrayList<Integer> realDice;
	Random random;
	int rank;

	public static void main(String[] args) {
	}

	public Dice(int nDice){
		super(nDice);
		random = new Random();
		numberOfDice = nDice;
		realDice = new ArrayList<>(numberOfDice);
		rollAllDice();
	}

	public void rollAllDice(){
		for (int i = 0; i < numberOfDice; i++){
			rollDie(i);
		}
	}

	public ArrayList<Integer> getRealDice() {
		return realDice;
	}

	public void setRealDice(ArrayList<Integer> testDice) {
		// Method for testing. This will not be used in the game.
		realDice = testDice;

	}

	public void rollDie(int index){
		realDice.add(index, random.nextInt(6) + 1);
	}

	public int getIndexOf(Integer die){
		for (int i = 0; i < realDice.size(); i++) {
			if (realDice.get(i).equals(die)){
				return i;
			}
		}
		return -1;
	}
	public ArrayList<Integer> getIndicesOf(Integer die){
		ArrayList<Integer> indices = new ArrayList<>();
		for (int i = 0; i < realDice.size(); i++) {
			if (realDice.get(i).equals(die)){
				indices.add(i);
			}
		}
		return indices;
	}

	public boolean containsDie(Integer die){
		return realDice.contains(die);
	}


	public void printDice(){
		System.out.print("Dice:\t\t");
		for (int die : realDice){
			System.out.print(die + "\t");
		}
		System.out.println("\n");
	}


}
