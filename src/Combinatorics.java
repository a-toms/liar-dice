import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.paukov.combinatorics3.Generator;


public class Combinatorics {

	// Get groups of dice indices to roll when rolling a certain number of dice
	// find the possible rolls when rolling those groups of dice indices
	// ergo, calculate the probability of achieving the higher rank based on rolling those dice.

	//int diceFaces = number_of_dice - 1;


	private ArrayList<String> getAllDiceIndices(int numberOfDiceToRoll) {
		/*
		Get all of the combinations of the indices of the number of dice that
		you want to roll.
		*/
		ArrayList<String> diceIndices = (ArrayList<String>) Generator.combination(0, 1, 2, 3, 4, 5)
				.simple(numberOfDiceToRoll)
				.stream()
				.map(index -> String.valueOf(index))
				.collect(Collectors.toList());
		System.out.println(diceIndices);
		return diceIndices;
	}

	private ArrayList<String> getFilteredIndices(String indicesToAvoid, int numberOfDiceToRoll){
		/*
		Get the dice indices of the dice you want to roll, excluding the indices of the
		dice that you do not want to roll.
		*/
		ArrayList<String> filteredIndices = new ArrayList<>();
		ArrayList<String> allIndices = getAllDiceIndices(numberOfDiceToRoll);
		for (String ind : allIndices){
			if (!StringUtils.containsAny(ind, indicesToAvoid)){
				filteredIndices.add(ind);
			}
		}
		System.out.println(filteredIndices);
		return filteredIndices;
	}
}



