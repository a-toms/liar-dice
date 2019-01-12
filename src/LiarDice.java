import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Scanner;


public class LiarDice {

	// Dice remain in LiarDice

	int numberOfPlayers;
	int numberOfDice;
	ArrayList<Player> players;
	Scanner scanner;
	Dice dice;
	String HandThatPreviousPlayerSaysHeHas;

	public static void main(String[] args) {
		LiarDice liarDice = new LiarDice();
		liarDice.addPlayers(5);
		liarDice.getPlayerAction();


	}

	public LiarDice() {
		numberOfDice = 5;
		scanner = new Scanner(System.in);
		dice = new Dice(numberOfDice);
		HandThatPreviousPlayerSaysHeHas = new String();
		players = new ArrayList<>();

	}

	public void getNumberOfPlayers() {
		System.out.println("Enter the number of players in the game");
		numberOfPlayers = scanner.nextInt();
		addPlayers(numberOfPlayers);
		System.out.printf("The game has %d players", players.size());
	}

	private void addPlayers(int nPlayers) {
		for (int i = 1; i <= nPlayers; i++) {
			Player player = new Player("player" + String.valueOf(i));
			players.add(player);
		}
	}

	private boolean atLeastTwoPlayersHaveOneLifeRemaining() {
		int lifeCounter = 0;
		for (Player player : players) {
			if (player.getLivesLeft() >= 1) {
				lifeCounter++;
			}
		}
		return lifeCounter >= 2;
	}

	private void getPlayerAction() {
		System.out.println("Player X, press enter to show the dice");
		scanner.nextLine();
		dice.printDice();
		chooseDiceToRoll();
		String announcedHand = chooseHandToAnnounce();

	}

	private String chooseHandToAnnounce(){
		System.out.println(
				"The dice that you have are:"
		);
		dice.printDice();
		System.out.printf(
				"Enter the %d dice to announce to the next player\n",
				numberOfDice
		);
		String announcedHand = scanner.next();
		if (announcedHand.length() != numberOfDice) {
			System.out.printf(
					"You did not enter %d dice. Asking again...\n",
					numberOfDice
			);
			return chooseHandToAnnounce();
		}
		System.out.println(
				"Press Y if the dice that you want to announce to " +
				"the next player are: " + announcedHand
		);
		String confirm = scanner.next();
		if (!confirm.toUpperCase().equals("Y")) {
			System.out.println(
					"You did not confirm that you want to announce the dice " +
					"to the next player. Repeating question..."
			);
			return chooseHandToAnnounce();
		}
		System.out.println(
				"Got it. You will tell the other player that you have: "
				+ announcedHand
		);
		return announcedHand;
	}

	private void chooseDiceToRoll(){
		System.out.println(
				"The dice that you have are:"
		);
		dice.printDice();
		System.out.println("Enter the dice that you want to roll");
		String diceToRoll = scanner.next();


		for (int i = 0; i < diceToRoll.length(); i++){
			Integer chosenDieToRoll = Character.getNumericValue(
					diceToRoll.charAt(i)
			);
			System.out.println("Chosen dice to roll = " + chosenDieToRoll);



			if (dice.containsMoreThanOneOf(chosenDieToRoll)){
				//roll multiple dice


			}


			else{
				// roll one dice
				dice.rollDie(chosenDieToRoll);

			}

		}

		// Show dice after roll
		System.out.println("You rolled the dice to give:");
		dice.printDice();




	}




	public void gameLoop(){
		// create dice
		while (atLeastTwoPlayersHaveOneLifeRemaining()){
			ListIterator<Player> playersIterator = players.listIterator();
			while (playersIterator.hasNext()){
				if (!atLeastTwoPlayersHaveOneLifeRemaining()){
					break;
				}
				Player nextPlayer = playersIterator.next();
				/// Todo: Insert player decisions below


				System.out.println("Enter 1 to remove life from" + nextPlayer);
				int command = scanner.nextInt();
				if (command == 1){
					nextPlayer.loseLife();
					playersIterator.remove();
				}

			}


		}
		// select next player
		// press button to show the dice
		// choose any dice to roll
		// announce hand -> pass hand to the next person
		// next player accepts or rejects
		// if reject, subtract the life
		// next player's turn



	}

}
