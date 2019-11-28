/**
 * A token door cell. Requires a certain number of tokens to open.
 * @author Liam Date
 * @version 1.0
 *
 */
public class TokenDoor extends Cell {
	
	private int numTokensNeeded;
	
	/**
	 * Constructor for a TokenDoor object.
	 * @param numTokensNeeded The number of tokens required to unlock the door.
	 */
	public TokenDoor (int numTokensNeeded) {
		super(CellType.TOKEN_DOOR);
		this.numTokensNeeded = numTokensNeeded;
	}
	
	/**
	 * Get method for the number of tokens need to unlock the door.
	 * @return The number of tokens needed.
	 */
	public int getNumTokensNeeded () {
		return this.numTokensNeeded;
	}
}
