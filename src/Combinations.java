import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Combinations {

	public static void main(String[] args) {
		Combinations comb = new Combinations();
		//System.out.println(comb.generatePossibleRolls(6));
		String test = "13119";
		System.out.println(hasNIndenticalChars(test, 4));


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

	//todo: write containsAllConsecutiveNumbers


//	public static ArrayList<String> returnAllFiveOfAKind(ArrayList<String> rolls){
//		ArrayList<String> fiveOfAKind = new ArrayList<>();
//		for (String roll : rolls){
//
//		}
//
//
//	}

	private static boolean hasFiveOfAKind(String candidate){
		char first = candidate.charAt(0);
		for (int i = 0; i < candidate.length(); i++){
			if (candidate.charAt(i) != first) return false;
		}
		return true;
	}

	private static boolean hasFourOfAKind(String candidate){
		String result = "";
		for (int i = 0; i < candidate.length() - 1; i++){

		}
		return true;
	}


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
			if (isElementPresent(a, Integer.parseInt(elements.substring(i, i + 1))) == true){
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




}
