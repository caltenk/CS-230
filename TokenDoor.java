
public class TokenDoor extends Cell {
	private int numTokensNeeded;
	
	public TokenDoor (int numTokensNeeded) {
		this.numTokensNeeded = numTokensNeeded;
	}
	
	public int getNumTokensNeeded () {
		return this.numTokensNeeded;
	}
}
