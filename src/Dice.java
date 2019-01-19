import java.util.*;



public class Dice extends RollClassifier {

	int numberOfDice;
	private ArrayList<Integer> realDice;
	Random random;

	public Dice(int numberOfDice){
		super(numberOfDice);
		random = new Random();
		this.numberOfDice = numberOfDice;
		realDice = new ArrayList<>(this.numberOfDice);
		rollAll();
	}

	public void rollAll(){
		realDice = new ArrayList<>();
		System.out.println("Rolling all of the dice");
		for (int i = 0; i < numberOfDice; i++){
			realDice.add(i, random.nextInt(6) + 1);
		}
	}

	public ArrayList<Integer> getDice() {
		return realDice;
	}


	public String getString(){
		StringBuilder stringBuilder = new StringBuilder();
		for (Integer die: realDice){
			stringBuilder.append(die);
		}
		return stringBuilder.toString();
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
		System.out.print("Actual dice:\t\t");
		for (int die : realDice){
			System.out.print(die + "\t");
		}
		System.out.println("\n");
	}


}
