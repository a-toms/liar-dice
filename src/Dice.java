import java.util.*;



public class Dice extends RollClassifier {

	int numberOfDice;
	private ArrayList<Integer> realDice;
	Random random;


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

	public void setAllDice(String s) {
		// Primarily intended for testing.
		ArrayList<Integer> replacementDice = new ArrayList<>();
		for (int i = 0; i < s.length(); i++) {
			replacementDice.add(Character.getNumericValue(s.charAt(i)));
		}
		realDice = replacementDice;
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
