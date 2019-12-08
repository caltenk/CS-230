
/**
 * A token door cell. Requires a certain number of tokens to open.
 *
 * @author Liam Date
 * @version 1.0
 */
public class TokenDoor extends Cell {

    private int numTokensNeeded;

    /**
     * Constructor for a TokenDoor object.
     *
     * @param numTokensNeeded The number of tokens required to unlock the door.
     */
    public TokenDoor(int numTokensNeeded) {
        super(CellType.TOKEN_DOOR);
        this.numTokensNeeded = numTokensNeeded;
    }

    /**
     * saves the token door cell as a string from which a copy can later be loaded,
     * used to save the token door cell to a text file.
     *
     * @return a string containing all information needed to load a copy of this
     * token door cell.
     */
    @Override
    public String toString() {
        String tokenDoorData = super.toString();
        tokenDoorData += "#" + Integer.toString(getNumTokensNeeded());
        return tokenDoorData;
    }

    /**
     * Get method for the number of tokens need to unlock the door.
     *
     * @return The number of tokens needed.
     */
    public int getNumTokensNeeded() {
        return this.numTokensNeeded;
    }
}
