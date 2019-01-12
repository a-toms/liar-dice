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
	String handThatPreviousPlayerSaidHeHad;
	RollClassifier rollClassifier;

	public static void main(String[] args) {
		LiarDice liarDice = new LiarDice();
		liarDice.addPlayers(5);
		liarDice.dice.setAllDice("22315");
		liarDice.handThatPreviousPlayerSaidHeHad = "22231";
		liarDice.askPlayerForActions();


	}

	public LiarDice() {
		numberOfDice = 5;
		scanner = new Scanner(System.in);
		dice = new Dice(numberOfDice);
		rollClassifier = new RollClassifier(numberOfDice);
		handThatPreviousPlayerSaidHeHad = new String();
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

	private void askPlayerForActions() {
		if (!handThatPreviousPlayerSaidHeHad.isEmpty()){
			showHandThatPreviousPlayerSaidHeHad();
		}
		System.out.println("Player 1, the dice are:");
		dice.printDice();
		getActions();

	}

	private void showHandThatPreviousPlayerSaidHeHad(){
		System.out.printf(
				"The previous player said that he had:\n%s\n",
				handThatPreviousPlayerSaidHeHad
		);
	}

	private void getActions(){
		if (playerWantsToRollDice()){
			rollChoseDice();
		}
		chooseHandToAnnounce();
	}


	private boolean playerWantsToRollDice(){
		System.out.println("Press Y if you would like to roll any dice");
		String decision = scanner.next();
		return decision.toUpperCase().equals("Y");
	}

	private String chooseHandToAnnounce(){
		System.out.println(
				"The dice that you have are:"
		);
		dice.printDice();

		//Choose hand to announce
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

		//Confirmation
		System.out.println(
				"Press Y if the hand that you want to announce to " +
				"the next player is: " + announcedHand
		);
		String confirm = scanner.next();
		if (!confirm.toUpperCase().equals("Y")) {
			System.out.println(
					"You did not confirm that you want to announce the dice " +
					"to the next player. Repeating question..."
			);
			return chooseHandToAnnounce();
		};

		//Check announced hand higher than previous
		if (!handThatPreviousPlayerSaidHeHad.isEmpty()){
			if (rollClassifier.isHandHigher(handThatPreviousPlayerSaidHeHad, announcedHand)){
				System.out.printf(
						"Unfortunately, the hand that you proposed to announce," +
						"%s,\nmust have a higher rank than the hand that the\n" +
						"previous player announced, %s. Repeating question...\n",
						announcedHand, handThatPreviousPlayerSaidHeHad);
				return chooseHandToAnnounce();
			}
		}
		return announcedHand;
	}




	private void rollChoseDice(){
		System.out.println(
				"The dice that you have are:"
		);
		dice.printDice();
		System.out.println("Enter the dice that you want to roll");
		String chosenDice = scanner.next();

		//Put dice to roll in toRoll
		ArrayList<Integer> toRoll = new ArrayList<>();
		for (int i = 0; i < chosenDice.length(); i++) {
			Integer die = Character.getNumericValue(chosenDice.charAt(i));
			toRoll.add(die);
		}

		// roll all dice in toRoll
		for (Integer die : dice.getDice()){
			if (toRoll.contains(die)){
				dice.roll(die);
				toRoll.remove(die);
			}
		}

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
