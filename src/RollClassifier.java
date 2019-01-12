import org.apache.commons.lang3.StringUtils;

import java.util.*;

public class RollClassifier {

	private StringSorter freqSorter;
	private HashMap<Integer, String> rollRank;

	public RollClassifier(int n_dice){
		freqSorter = new StringSorter();
		rollRank = new HashMap<>();
		orderRollsByRank(n_dice);
	}

	public boolean isHandHigher(String handA, String handB){
		return getRank(handA) > getRank(handB);
	}

	public Integer getRank(String roll){
		for (Map.Entry<Integer, String> entry : rollRank.entrySet()){
			if (freqSorter.sortByFrequencyAndSize(roll).equals(
					entry.getValue())
			){
				return entry.getKey();
			}
		}
		System.out.println("Roll not found.");
		return 0;
	}

	public ArrayList<String> getAllRollsContaining(String target){
		ArrayList<String> rollsContaining = new ArrayList<>();
		for (Map.Entry<Integer, String> entry : rollRank.entrySet()){
			String roll = String.valueOf(entry.getValue());
			if (containsAll(roll, target)){
				rollsContaining.add(roll);
			}
		}
		return rollsContaining;
	}

	private boolean containsAll(String candidate, String target){
		/* Returns boolean based on whether the candidate contains
		at least one of each of the characters of target.
		 */
		for (int i = 0; i < target.length(); i++){
			if (!StringUtils.contains(candidate, target.charAt(i))){
				return false;
			}
		}
		return true;
	}

	public String getRoll(Integer rank){
		return rollRank.get(rank);
	}



	private void orderRollsByRank(int numberOfDice) {
		ArrayList<String> allRolls = generatePossibleRolls(numberOfDice);
		ArrayList<String> orderedRolls = new ArrayList<>();

		orderedRolls.addAll(getHighNumberRolls(allRolls));
		orderedRolls.addAll(getOnePairRolls(allRolls));
		orderedRolls.addAll(getTwoPairRolls(allRolls));
		orderedRolls.addAll(getThreeOfAKindRolls(allRolls));
		orderedRolls.addAll(getStraightRolls(allRolls));
		orderedRolls.addAll(getFullHouseRolls(allRolls));
		orderedRolls.addAll(getFourOfAKindRolls(allRolls));
		orderedRolls.addAll(getFiveOfAKindRolls(allRolls));

		for (int i = 0; i < orderedRolls.size(); i++) {
			rollRank.put(i + 1, orderedRolls.get(i));
		}
	}

	private ArrayList<String> generatePossibleRolls(int number_of_dice){
		// Generate range of dice faces
		String start = "";
		String end = "";
		for (int i = 1; i <= number_of_dice; i += 1) {
			start += "1";
			end += "6";
		}
		// Generate rolls
		ArrayList<String> possible_rolls = new ArrayList<>();
		int notFacesOfADie = 7890;
		for (int i = Integer.parseInt(start); i <= Integer.parseInt(end); i++){
			if (areAnyDigitsPresent(i, notFacesOfADie) == false){
				possible_rolls.add(String.valueOf(i));
			}
		}
		return possible_rolls;
	}

	private ArrayList<String> getFiveOfAKindRolls(ArrayList<String> rawRolls){
		ArrayList<String> fiveOfAKindRolls = new ArrayList<>();
		for (String roll : rawRolls){
			if (hasFiveOfAKind(roll)){
				fiveOfAKindRolls.add(roll);
			}
		}
		return getSortedRolls(fiveOfAKindRolls);
	}

	private ArrayList<String> getFourOfAKindRolls(ArrayList<String> rawRolls){
		ArrayList<String> fourOfAKindRolls = new ArrayList<>();
		for (String roll : rawRolls){
			if (hasFourOfAKind(roll)){
				fourOfAKindRolls.add(roll);
			}
		}
		return getSortedRolls(fourOfAKindRolls);
	}

	private ArrayList<String> getFullHouseRolls(ArrayList<String> rawRolls){
		ArrayList<String> fullHouseRolls = new ArrayList<>();
		for (String roll : rawRolls){
			if (hasFullHouse(roll)){
				fullHouseRolls.add(roll);
			}
		}
		return getSortedRolls(fullHouseRolls);
	}

	private ArrayList<String> getStraightRolls(ArrayList<String> rawRolls){
		ArrayList<String> straightRolls = new ArrayList<>();
		for (String roll : rawRolls){
			if (hasStraight(roll)){
				straightRolls.add(roll);
			}
		}
		return getSortedRolls(straightRolls);
	}

	private ArrayList<String> getThreeOfAKindRolls(ArrayList<String> rawRolls){
		ArrayList<String> threeOfAKindRolls = new ArrayList<>();
		for (String roll : rawRolls){
			if (hasThreeOfAKind(roll)){
				threeOfAKindRolls.add(roll);
			}
		}
		return getSortedRolls(threeOfAKindRolls);
	}

	private ArrayList<String> getTwoPairRolls(ArrayList<String> rawRolls){
		ArrayList<String> twoPairRolls = new ArrayList<>();
		for (String roll : rawRolls){
			if (hasTwoPair(roll)){
				twoPairRolls.add(roll);
			}
		}
		return getSortedRolls(twoPairRolls);
	}

	private ArrayList<String> getOnePairRolls(ArrayList<String> rawRolls){
		ArrayList<String> onePairRolls = new ArrayList<>();
		for (String roll : rawRolls){
			if (hasOnePair(roll)){
				onePairRolls.add(roll);
			}
		}
		return getSortedRolls(onePairRolls);
	}

	private ArrayList<String> getHighNumberRolls(ArrayList<String> rawRolls){
		ArrayList<String> highNumberRolls = new ArrayList<>();
		for (String roll : rawRolls){
			if (hasHighNumber(roll)){
				highNumberRolls.add(roll);
			}
		}
		return getSortedRolls(highNumberRolls);
	}

	private boolean hasFiveOfAKind(String candidate){
		return hasNIdenticalChars(candidate, 5);
	}

	private boolean hasFullHouse(String candidate){
		return hasNIdenticalChars(candidate, 3) &&
				hasNIdenticalChars(candidate, 2) ;
	}

	private boolean hasFourOfAKind(String candidate){
		return hasNIdenticalChars(candidate, 4);
	}

	private boolean hasStraight(String candidate){
		return areNumbersConsecutive(sortString(candidate), 0, 1);
	}

	private boolean hasThreeOfAKind(String candidate){
		return hasNIdenticalChars(candidate, 3) &&
				!hasNIdenticalChars(candidate, 2);
	}

	private boolean hasTwoPair(String candidate){
		return hasNPairs(candidate, 2);
	}

	private boolean hasOnePair(String candidate){
		return hasNPairs(candidate, 1) &&
				!hasNIdenticalChars(candidate, 3);
	}

	private boolean hasHighNumber(String candidate){
		for (int i = 1; i <= 5; i++){
			if (hasNPairs(candidate, i)){
				return false;

			}
			if(hasNIdenticalChars(candidate, i + 1)){
				return false;
			}
		}
		return !areNumbersConsecutive(sortString(candidate), 0, 1);
	}

	private boolean hasNPairs(String candidate, int n){
		String pairsRecord = "";
		for (int i = 0; i < candidate.length(); i++){
			if (countCharOccurrences(candidate, candidate.charAt(i)) == 2 &&
					!pairsRecord.contains(String.valueOf(candidate.charAt(i)))){
				pairsRecord += String.valueOf(candidate.charAt(i));
			}
		}
		return pairsRecord.length() == n;
	}

	private boolean hasNIdenticalChars(String candidate, int n){
		for (int i = 0; i < candidate.length(); i++){
			if (countCharOccurrences(candidate, candidate.charAt(i)) == n){
				return true;
			}
		}
		return false;
	}

	private int countCharOccurrences(String larger, char target){
		int count = 0;
		for (int i = 0; i < larger.length(); i++) {
			char a = larger.charAt(i);
			if (a == target){
				count++;
			}
		}
		return count;
	}


	private ArrayList<String> getSortedRolls(ArrayList<String> unsortedRolls){
		ArrayList<String> sortedRolls = new ArrayList<>();
		// Sort each string by char frequency and size
		for (String roll : unsortedRolls){
			String sorted = freqSorter.sortByFrequencyAndSize(roll);
			sortedRolls.add(sorted);
		}
		// Order strings by size compared to other strings
		Collections.sort(sortedRolls);
		// Remove duplicates
		Set<String> listToSet = new LinkedHashSet<>(sortedRolls);
		sortedRolls = new ArrayList<String>(listToSet);
		return sortedRolls;
	}

	private String sortString(String unsorted){
		// Returns in ascending order
		char[] chars = unsorted.toCharArray();
		Arrays.sort(chars);
		return String.valueOf(chars);
	}

	private boolean areAnyDigitsPresent(int a, int b){
		String elements = String.valueOf(b);
		for (int i = 0; i < elements.length(); i++){
			if (isElementPresent(
					a, Integer.parseInt(elements.substring(i, i + 1)))){
				return true;
			}
		}
		return false;
	}

	private boolean isElementPresent(int candidate, int b){
		String element = String.valueOf(b);
		if (String.valueOf(candidate).contains(element)){
			return true;
		}
		else {
			return false;
		}
	}

	private boolean areNumbersConsecutive(
			String candidate, int firstIndex, int secondIndex
	){
		if (secondIndex == candidate.length()){
			return true;
		}
		if (Integer.valueOf(candidate.charAt(secondIndex)).equals(
				candidate.charAt(firstIndex) + 1)){
			return areNumbersConsecutive(
					candidate, firstIndex + 1, secondIndex + 1
			);
		}
		else{
			return false;
		}
	}
}
