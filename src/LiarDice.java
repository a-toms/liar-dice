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
	String diceThatPreviousPlayerSaysHeHas;

	public static void main(String[] args) {
		LiarDice liarDice = new LiarDice();
		liarDice.addPlayers(5);
//		liarDice.getPlayerAction();
//		liarDice.gameLoop();
		String test1 = "55446";
		String test2 = "44455";
		System.out.println(StringUtils.difference("44455", "55446"));
		// todo: perhaps sort the above by number before using difference
	}

	public LiarDice(){
		numberOfDice = 5;
		scanner = new Scanner(System.in);
		dice = new Dice(numberOfDice);
		diceThatPreviousPlayerSaysHeHas = new String();
		players = new ArrayList<>();

	}

	public void getNumberOfPlayers(){
		System.out.println("Enter the number of players in the game");
		numberOfPlayers = scanner.nextInt();
		addPlayers(numberOfPlayers);
		System.out.printf("The game has %d players", players.size());
	}

	private void addPlayers(int nPlayers){
		for (int i = 1; i <= nPlayers; i++){
			Player player = new Player("player" + String.valueOf(i));
			players.add(player);
		}
	}

	private boolean atLeastTwoPlayersHaveOneLifeRemaining(){
		int lifeCounter = 0;
		for (Player player : players){
			if (player.getLivesLeft() >= 1){
				lifeCounter++;
			}
		}
		return lifeCounter >= 2;
	}

	private void getPlayerAction(){
		System.out.println("Player X, press enter to show the dice");
		scanner.nextLine();
		dice.printDice();
		// Todo: continue
		//if (Player.decideToRollDice()){

			// choose which dice to roll. You may roll any of the dice.
		}
		//System.out.println("!true");
		// announce dice

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
