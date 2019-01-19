import org.apache.commons.lang3.StringUtils;

import java.util.Scanner;


public class Player {

	String name;
	Integer numberOfDice;
	private Integer livesLeft;
	Integer numberOfBluffs;
	StringSorter stringSorter;
	Scanner scanner;
	RollClassifier rollClassifier;

	public Player(String playerName){
		name = playerName;
		numberOfDice = 5;
		livesLeft = 1;
		numberOfBluffs = 0;
		stringSorter = new StringSorter();
		scanner = new Scanner(System.in);
		rollClassifier = new RollClassifier(numberOfDice);
	}

	private void chooseDice(){
		String dice = scanner.next();
	}

	private void printOptions(){
		System.out.println(
				"Options:\n" +
						"- Press 1 to choose which dice to roll\n" +
						"- Press 2 to announce the hand to pass to the next player\n"
		);

	}

	private Integer getRankOfDiceToBeat(String diceThatPreviousPlayerSaysHeHas){
		return rollClassifier.getRank(diceThatPreviousPlayerSaysHeHas);
	}


	private boolean isHigher(int possibleRank, int previousRank){
		return possibleRank > previousRank;
	}


	public Integer getLivesLeft(){
		return livesLeft;
	}

	public void loseLife(){
		livesLeft--;
		System.out.println(
				this.name + " lost a life"
		);
	}

	public boolean hasNoLivesLeft(){
		return livesLeft < 1;
	}

	public void displayEliminationMessageIfEliminated(){
		if (this.hasNoLivesLeft()){
			System.out.println(
					this.name +
					" has no lives left and is eliminated from the game."
			);
		}
	}

	public boolean hasLivesLeft(){
		return livesLeft >= 1;
	}

}
