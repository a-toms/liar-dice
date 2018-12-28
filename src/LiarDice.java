import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Scanner;

public class LiarDice {

	// Dice remain in LiarDice

	int nPlayers;
	ArrayList<Player> players;
	Scanner sc;
	Dice dice;

	public static void main(String[] args) {
		LiarDice liarDice = new LiarDice();
		liarDice.addNPlayers(5);
		liarDice.getPlayerAction();
//		liarDice.gameLoop();

	}

	public LiarDice(){
		sc = new Scanner(System.in);
		dice = new Dice(5);
		players = new ArrayList<>();
	}

	public void getNumberOfPlayers(){
		System.out.println("Enter the number of players in the game");
		nPlayers = sc.nextInt();
		addNPlayers(nPlayers);
		System.out.printf("The game has %d players", players.size());
	}

	private void addNPlayers(int nPlayers){

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
		sc.nextLine();
		dice.printDice();
		// Todo: continue
		if (decideToRollDice()){

			// choose which dice to roll. You may roll any of the dice.
		}
		System.out.println("!true");
		// announce dice




	}



	private boolean decideToRollDice(){
		System.out.println(
				"Options:\n" +
				"- Press 1 to choose which dice to roll\n" +
				"- Press 2 to announce the hand to pass to the next player\n"
		);
		// Todo: modify the below so that non-int entries also re-prompt user.
		Integer command = sc.nextInt();
		if (command.equals(1)){
			return true;
		}
		if (command.equals(2)){
			return false;
		}
		System.out.println("Invalid choice. Please choose again.");
		return decideToRollDice();
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
				int command = sc.nextInt();
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
