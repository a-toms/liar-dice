import org.jetbrains.annotations.NotNull;

import java.util.*;


public class LiarDice {

	public static void main(String[] args) {
		LiarDice liarDice = new LiarDice();
		liarDice.addPlayers(2);
		liarDice.gameLoop();
	}

	// Dice remain in LiarDice

	int numberOfPlayers;
	int numberOfDice;
	ArrayList<Player> players;
	Scanner scanner;
	Dice dice;
	String previousAnnouncedHand;
	String newAnnouncedHand;
	RollClassifier rollClassifier;


	public LiarDice() {
		numberOfDice = 5;
		scanner = new Scanner(System.in);
		dice = new Dice(numberOfDice);
		rollClassifier = new RollClassifier(numberOfDice);
		previousAnnouncedHand = new String();
		newAnnouncedHand = new String();
		players = new ArrayList<>();
	}

	public void printNumberOfPlayers() {
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

	private Player getGameWinner() {
		if (atLeastTwoPlayersHaveOneLifeRemaining()) {
			return null;
		}
		for (Player player : players) {
			if (player.hasLivesLeft()) {
				return player;
			}
		}
		return null;
	}

	private String getAnnouncedHand() {
		if (!previousAnnouncedHand.isEmpty()) {
			showHandThatPreviousPlayerSaidHeHad();
		}
		dice.printDice();

		/* If there is no previous announced hand, the player cannot roll the
		dice. This is because it is the first throw of the new round.
		 */
		if (!previousAnnouncedHand.isEmpty() && playerWantsToRollDice()) {
			rollChosenDice();
		}
		return chooseHandToAnnounce();
	}

	private void showHandThatPreviousPlayerSaidHeHad() {
		System.out.printf("The previous player said that he had:\n%s\n", previousAnnouncedHand);
	}

	private boolean playerWantsToRollDice() {
		System.out.println("Press Y if you would like to roll any dice");
		String decision = scanner.next();
		return decision.toUpperCase().equals("Y");
	}

	// Todo: partition this method.
	private String chooseHandToAnnounce() {
		if (!previousAnnouncedHand.isEmpty()) {
			showHandThatPreviousPlayerSaidHeHad();
		}
		setNewAnnouncedHand();
		// Check for correct number of dice
		if (enteredIncorrectNumberOfDice()) {
			return chooseHandToAnnounce();
		}
		// Check newly announced hand is higher than the previous announced hand.
		if (newAnnouncedHandIsNotHigher()){
			return chooseHandToAnnounce();
		}
		clearScreen();
		return newAnnouncedHand;
	}

	private void setNewAnnouncedHand(){
		System.out.printf("Enter the %d dice to announce to the next player\n", numberOfDice);
		newAnnouncedHand = scanner.next();
	}

	private boolean enteredIncorrectNumberOfDice(){
		if (newAnnouncedHand.length() != numberOfDice) {
			System.out.printf("You did not enter %d dice. Asking again...\n", numberOfDice);
			return true;
		}
		return false;

	}

	public boolean newAnnouncedHandIsNotHigher() {
		if (!previousAnnouncedHand.isEmpty()) {
			if (rollClassifier.isFirstHandHigherThanSecond(
					previousAnnouncedHand, newAnnouncedHand)) {
				System.out.printf(
						"Unfortunately, the hand that you proposed to announce, " +
						"%s,\n does not have a higher rank than the hand that " +
						"the\n" + "previous player announced, %s. \n" +
						"Repeating question...\n\n",
						newAnnouncedHand, previousAnnouncedHand);
				return true;
			}
		}
		return false;
	}

	private void rollChosenDice(){
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

	public void gameLoop() {
		String announcedHand;
		String answer;
		String loser;

		while (atLeastTwoPlayersHaveOneLifeRemaining()) {

			Player announcer = getNextPlayerFrom(0);
			Player responder = getNextPlayerFrom(1);

			/*Rotate player order again if the announcer and responder
			are the same person.*/
			if (announcer == responder){
				Collections.rotate(players, -1);
				continue;
			}
			System.out.printf(
					"%s, enter any character to see the new dice\n",
					announcer.name
			);
			scanner.next();

			announcedHand = getAnnouncedHand();

			System.out.println(responder.name);
			answer = acceptOrReject(announcedHand, announcer);

			if (answer.equals("accept")){
				previousAnnouncedHand = announcedHand;
				Collections.rotate(players, -1);
				continue;
			}
			if (answer.equals("reject")){
				loser = findHandLoser(announcedHand);
				if (loser.equals("announcer")){
					announcer.loseLife();
					announcer.displayEliminationMessageIfEliminated();
				}
				else{
					responder.loseLife();
					responder.displayEliminationMessageIfEliminated();
				}
			}

			//method : resetForNewRound
			dice.rollAll();
			previousAnnouncedHand = new String();

			//Rotate player order for next round
			Collections.rotate(players, -1);

		}
		// If only one player remains.

		System.out.println("Game ends. ");
		System.out.println("The winner is " + getGameWinner().name);
	}

	public Player getNextPlayerFrom(int index){
		for (int i = 0; i < players.size(); i++) {
			if (players.get(index + i).hasLivesLeft()) {
				return players.get(index + i);
			}
		}
		return null;
	}

	public static void clearScreen() {
		for(int i = 0; i < 100; i++){
			System.out.println();
		}
	}

	private String acceptOrReject(String announcedHand, Player previousPlayer){
		System.out.printf(
				"%s asked you to accept the hand:\n%s\n",
				previousPlayer.name,
				announcedHand
		);
		if (!previousAnnouncedHand.isEmpty()){
			showHandThatPreviousPlayerSaidHeHad();
		}
		System.out.printf(
				"Do you accept %s from %s? " +
				"Press Y to accept, or anything else to reject\n",
				announcedHand,
				previousPlayer.name
		);
		String answer = scanner.next();
		if (answer.toUpperCase().equals("Y")){
			System.out.println("You accept the player's hand");
			previousAnnouncedHand = announcedHand;
			return "accept";
		}
		else{
			System.out.println("You reject the player's hand");
			return "reject";
		}
	}

	@NotNull
	private String findHandLoser(String announcedHand){
		if (announcedHandContainsALie(announcedHand)){
			System.out.printf("The real dice are %s. ", dice.getDice());
			return "announcer";
		}
		else{
			System.out.printf("The real dice are %s, ", dice.getDice());
			return "caller";
		}
	}

	private boolean announcedHandContainsALie(String announcedHand){
		/* Concludes that a hand is not a lie if the announcedHand <= realDice.
		This defines a "lie" based on rankings, rather than dice. Ask about this.
		 */
		return rollClassifier.isFirstHandHigherThanSecond(
				announcedHand,
				dice.getString()
		);
	}

	public static boolean containsOnlyIntegers(String s) {
		for (int i = 0; i < s.length(); i++) {
			Character character = s.charAt(i);
			if (!isInteger(character)) {
				return false;
			}
		}
		return true;
	}

	private static boolean isInteger(Character c) {
		boolean isValidInteger = false;
		try{
			Character.getNumericValue(c);
			isValidInteger = true;
		}
		catch (NumberFormatException ex) {
		}
		return isValidInteger;
	}
}

