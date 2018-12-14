import java.util.ArrayList;
import java.util.Arrays;

public class Combinations {

	public static void main(String[] args) {
		Combinations comb = new Combinations();
		//System.out.println(comb.generatePossibleRolls(6));
		String test = "221133";
		System.out.println(hasNPairs(test, 2));

	}

	private static boolean hasFiveOfAKind(String candidate){
		return hasNIndenticalChars(candidate, 5);
	}

	private static boolean hasFullHouse(String candidate){
		return hasNIndenticalChars(candidate, 2) && hasNIndenticalChars(candidate, 3);
	}

	private static boolean hasFourOfAKind(String candidate){
		return hasNIndenticalChars(candidate, 4);
	}

	private static boolean hasStraight(String candidate){
		return areAllNumbersConsecutive(candidate, 0, 1);
	}

	private static boolean hasthreeOfAKind(String candidate){
		return hasNIndenticalChars(candidate, 3);
	}

	private static boolean hasTwoPairs(String candidate){
		return hasNPairs(candidate, 2);
	}

	private static boolean hasOnePair(String candidate){
		return hasNPairs(candidate, 1);
	}

	private static boolean hasNPairs(String candidate, int n){
		// Todo: add comment
		String pairs = "";
		for (int i = 0; i < candidate.length(); i++){
			String target = String.valueOf(candidate.charAt(i));
			if (countCharOccurences(candidate, candidate.charAt(i)) == 2 &&
					!pairs.contains(target)){
				pairs += target;
			}
		}
		return pairs.length() == n;
	}


	private static boolean hasNIndenticalChars(String candidate, int n){
		for (int i = 0; i < candidate.length(); i++){
			if (countCharOccurences(candidate, candidate.charAt(i)) == n){
				return true;
			}
		}
		return false;
	}



	private static int countCharOccurences(String larger, char target){
		int count = 0;
		for (int i = 0; i < larger.length(); i++) {
			char a = larger.charAt(i);
			if (a == target) count++;
		}
		return count;
	}




//	public static ArrayList<String> returnAllFiveOfAKind(ArrayList<String> rolls){
//		ArrayList<String> fiveOfAKind = new ArrayList<>();
//		for (String roll : rolls){
//
//		}
//
//
//	}


	private static String sortString(String start){
		char[] chars = start.toCharArray();
		Arrays.sort(chars);
		String ascendingOrder = new String(chars);
		String descendingOrder = new StringBuilder(ascendingOrder).reverse().toString();
		return descendingOrder;
	}


//
//	public static void returnAllFourOfAKind{
//
//	}
//
//	public static void returnAllFullHouse{
//
//	}
//
//	public static void returnAllStraights{
//
//	}
//
//	public static void returnAllThreeOfAKinds{
//
//	}
//
//	public static void returnAllTwoPairs{
//
//	}
//
//	public static void returnAllOnePairs{
//
//	}

	public ArrayList<String> generatePossibleRolls(int number_of_dice){
		String start = "";
		String end = "";
		for (int i = 1; i <= number_of_dice; i += 1) {
			start += "1";
			end += "6";
		}
		ArrayList<String> possible_rolls = new ArrayList<>();
		for (int i = Integer.parseInt(start); i <= Integer.parseInt(end); i++){
			if (areAnyDigitsOfBPresent(i, 7890) == false){
				possible_rolls.add(String.valueOf(i));
			}
		}
		return possible_rolls;
	}


	private static boolean areAnyDigitsOfBPresent(int a, int b){
		String elements = String.valueOf(b);
		for (int i = 0; i < elements.length(); i++){
			if (isElementPresent(a, Integer.parseInt(elements.substring(i, i + 1)))
					== true){
				return true;
			}
		}
		return false;
	}

	private static boolean isElementPresent(int candidate, int b){
		String element = String.valueOf(b);
		if (String.valueOf(candidate).contains(element)) return true;
		else return false;
	}

	private static boolean areAllNumbersConsecutive(
			String candidate, int firstIndex, int secondIndex
	){
		if (secondIndex == candidate.length()) return true;
		if (Integer.valueOf(candidate.charAt(secondIndex))
				== Integer.valueOf(candidate.charAt(firstIndex)) + 1){
			return areAllNumbersConsecutive(
					candidate, firstIndex + 1, secondIndex + 1
			);
		}
		else return false;
	}

	//private static ArrayList<String> filterRolls(ArrayList<String> rawRolls){
		//Todo: filter rolls
	//}




}
