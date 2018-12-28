public class Player {

	String name;
	private Integer livesLeft;
	Integer numberOfBluffs;

	public Player(String name){
		this.name = name;
		livesLeft = 1;

	}

	public Integer getLivesLeft(){
		return livesLeft;
	}

	public void loseLife(){
		livesLeft--;
	}

}
