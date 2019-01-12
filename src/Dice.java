import java.util.ArrayList;
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
		rollAll();
	}

	public void rollAll(){
		for (int i = 0; i < numberOfDice; i++){
			realDice.add(i, random.nextInt(6) + 1);
		}
	}

	public ArrayList<Integer> getDice() {
		return realDice;
	}

	public void setRealDice(ArrayList<Integer> testDice) {
		// Method for testing. This will not be used in the game.
		realDice = testDice;

	}

	public void roll(Integer die){
		Integer rolledDie = random.nextInt(6) + 1;
		System.out.printf(
				"Rolled %d to give %d\n", die, rolledDie
		);
		realDice.set(getIndexOf(die), rolledDie);
	}

	public int getIndexOf(Integer die){
		return realDice.indexOf(die);
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

	public boolean containsMoreThanOneOf(Integer die){
		return getIndicesOf(die).size() > 1;
	}


	public void printDice(){
		System.out.print("Dice:\t\t");
		for (int die : realDice){
			System.out.print(die + "\t");
		}
		System.out.println("\n");
	}


}
