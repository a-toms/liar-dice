import org.apache.commons.lang3.StringUtils;

import javax.xml.crypto.dsig.keyinfo.KeyValue;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import java.util.Set;
import java.util.Comparator;
import java.util.TreeMap;
import org.apache.commons.lang3.StringUtils;



public class Combinations {

	public static void main(String[] args) {
		Combinations cb = new Combinations();
		ArrayList<String> rolls = cb.generatePossibleRolls(5);
		String test1 = "111122323";
		String test2 = "33";
		StringBuilder empty = new StringBuilder("");

	}

	public Combinations(){
	}





	private String getMostFrequent(String domain){
		// Removes avoid parameter.
		String highFreqString = "";
		int highN = 0;
		for (int i = 0; i < domain.length(); i++){
			int n = countCharOccurences(domain, domain.charAt(i));
			if (n > highN){
				highFreqString = String.valueOf(domain.charAt(i));
				highN = n;
			}
		}
		return StringUtils.repeat(highFreqString, highN);
	}



	public ArrayList<String> generatePossibleRolls(int number_of_dice){
		String start = "";
		String end = "";
		// Generate range of dice faces
		for (int i = 1; i <= number_of_dice; i += 1) {
			start += "1";
			end += "6";
		}
		// Generate possible rolls
		ArrayList<String> possible_rolls = new ArrayList<>();
		int notFacesOfADie = 7890;
		for (int i = Integer.parseInt(start); i <= Integer.parseInt(end); i++){
			if (areAnyDigitsOfBPresent(i, notFacesOfADie) == false){
				possible_rolls.add(String.valueOf(i));
			}
		}
		return possible_rolls;
	}


	private ArrayList<String> getFiveOfAKindRolls(
			ArrayList<String> rawRolls){
		ArrayList<String> fiveOfAKindRolls = new ArrayList<>();
		for (String roll : rawRolls){
			if (hasFiveOfAKind(roll)) fiveOfAKindRolls.add(roll);
		}
		return fiveOfAKindRolls;
	}

	private ArrayList<String> getFourOfAKindRolls(
			ArrayList<String> rawRolls){
		ArrayList<String> fourOfAKindRolls = new ArrayList<>();
		for (String roll : rawRolls){
			if (hasFourOfAKind(roll)) fourOfAKindRolls.add(roll);
		}
		return fourOfAKindRolls;
	}

	private ArrayList<String> getFullHouseRolls(
			ArrayList<String> rawRolls){
		ArrayList<String> fullHouseRolls = new ArrayList<>();
		for (String roll : rawRolls){
			if (hasFullHouse(roll)) fullHouseRolls.add(roll);
		}
		return fullHouseRolls;
	}

	private ArrayList<String> getStraightRolls(
			ArrayList<String> rawRolls){
		ArrayList<String> straightRolls = new ArrayList<>();
		for (String roll : rawRolls){
			if (hasStraight(roll)) straightRolls.add(roll);
		}
		return straightRolls;
	}

	private ArrayList<String> getThreeOfAKindRolls(
			ArrayList<String> rawRolls){
		ArrayList<String> threeOfAKindRolls = new ArrayList<>();
		for (String roll : rawRolls){
			if (hasThreeOfAKind(roll)) threeOfAKindRolls.add(roll);
		}
		return threeOfAKindRolls;
	}

	private ArrayList<String> getTwoPairRolls(
			ArrayList<String> rawRolls){
		ArrayList<String> twoPairRolls = new ArrayList<>();
		for (String roll : rawRolls){
			if (hasTwoPair(roll)) twoPairRolls.add(roll);
		}
		return twoPairRolls;
	}

	private ArrayList<String> getOnePairRolls(
			ArrayList<String> rawRolls){
		ArrayList<String> onePairRolls = new ArrayList<>();
		for (String roll : rawRolls){
			if (hasOnePair(roll)) onePairRolls.add(roll);
		}
		return onePairRolls;
	}



	private boolean hasFiveOfAKind(String candidate){
		return hasNIndenticalChars(candidate, 5);
	}

	private boolean hasFullHouse(String candidate){
		return hasNIndenticalChars(candidate, 3) &&
				hasNIndenticalChars(candidate, 2) ;
	}

	private boolean hasFourOfAKind(String candidate){
		return hasNIndenticalChars(candidate, 4);
	}

	private boolean hasStraight(String candidate){
		return areNumbersConsecutive(sortString(candidate), 0, 1);
	}

	private boolean hasThreeOfAKind(String candidate){
		return hasNIndenticalChars(candidate, 3) &&
				!hasNIndenticalChars(candidate, 2);
	}

	private boolean hasTwoPair(String candidate){
		return hasNPairs(candidate, 2);
	}

	private boolean hasOnePair(String candidate){
		return hasNPairs(candidate, 1) &&
				!hasNIndenticalChars(candidate, 3);
	}

	private boolean hasNPairs(String candidate, int n){
		String pairsRecord = "";
		for (int i = 0; i < candidate.length(); i++){
			String target = String.valueOf(candidate.charAt(i));
			if (countCharOccurences(candidate, candidate.charAt(i)) == 2 &&
					!pairsRecord.contains(target)){
				pairsRecord += target;
			}
		}
		return pairsRecord.length() == n;
	}

	private boolean hasNIndenticalChars(String candidate, int n){
		for (int i = 0; i < candidate.length(); i++){
			if (countCharOccurences(candidate, candidate.charAt(i)) == n){
				return true;
			}
		}
		return false;
	}

	private int countCharOccurences(String larger, char target){
		int count = 0;
		for (int i = 0; i < larger.length(); i++) {
			char a = larger.charAt(i);
			if (a == target) count++;
		}
		return count;
	}


	private String sortString(String unsorted){
		// Returns in ascending order
		char[] chars = unsorted.toCharArray();
		Arrays.sort(chars);
		String ascendingOrder = new String(chars);
		return ascendingOrder;
	}

	private String sortString(String unsorted, boolean descending){
		// Returns in descending order
		String ascending = sortString(unsorted);
		if (descending == true) {
			return new StringBuilder(ascending).reverse().toString();
		}
		else{
			return ascending;
		}
	}




	private boolean areAnyDigitsOfBPresent(int a, int b){
		// Todo: This seems unnecessarily complex. Perhaps use String or StringBulder compareTo method instead.
		String elements = String.valueOf(b);
		for (int i = 0; i < elements.length(); i++){
			if (isElementPresent(a, Integer.parseInt(elements.substring(i, i + 1)))
					== true){
				return true;
			}
		}
		return false;
	}

	private boolean isElementPresent(int candidate, int b){
		String element = String.valueOf(b);
		if (String.valueOf(candidate).contains(element)) return true;
		else return false;
	}


	private boolean areNumbersConsecutive(
			String candidate, int firstIndex, int secondIndex
	){
		if (secondIndex == candidate.length()) return true;
		if (Integer.valueOf(candidate.charAt(secondIndex))
				== Integer.valueOf(candidate.charAt(firstIndex)) + 1){
			return areNumbersConsecutive(
					candidate, firstIndex + 1, secondIndex + 1
			);
		}
		else return false;
	}






}
