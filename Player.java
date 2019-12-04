
import javafx.scene.image.Image;
import java.util.ArrayList;

/**
 * Class represents the character the user controls.
 *
 * @author George Manning
 * @version 1.0
 */
public class Player extends Moveable {

    private ArrayList<Item> inventory;
    private int tokenNum;

    /**
     * Comstructor for the player class.
     *
     * @param x The initial x co-ordinate of the player.
     * @param y The initial y co-ordinate of the player.
     * @param image The image used to represent the player.
     */
    public Player(int x, int y) {
        super(x, y);
        this.inventory = new ArrayList<Item>();
        this.tokenNum = 0;
    }

    /**
     * suggested to allow filehandling -Dan. note: if player image is to be set
     * here as suggested in Moveable() then that should be added here, not
     * tested.
     *
     * @param playerData
     */
    public Player(String playerData) {
        super(playerData);
        String playerObjData = playerData.split(";")[2];
        String[] splitData = playerObjData.split("/");

        tokenNum = Integer.parseInt(splitData[0]);

        this.inventory = new ArrayList<Item>();
        for (int i = 1; i < splitData.length; i++) {
            if (splitData[i] != null) {
                inventory.add(Item.valueOf(splitData[i]));
            }
        }
    }

    /**
     * suggested to allow filehandling -Dan. note: untested.
     *
     * @param playerData
     * @return
     */
    @Override
    public String toString() {
        String playerData = "PLAYER;" + super.toString() + ";";
        playerData += Integer.toString(tokenNum);

        for (int i = 0; i < inventory.size(); i++) {
            if (inventory.get(i) != null) {
                playerData += "/" + inventory.get(i).toString();
            }
        }
        return playerData;
    }

    /**
     * temporary method for testing purposes
     */
    public void giveStuff() {
        tokenNum = 5;
        inventory.add(Item.RED_KEY);
        inventory.add(Item.FIREBOOTS);
        inventory.add(Item.FLIPPERS);
    }

    /**
     * Moves the player in a user specified direction
     *
     * @param direction The direction to be moved to
     */
    public void move(Direction direction, Board board) {

        Cell nextCell = super.getNextCell(direction, board);
        if (this.isMoveValid(nextCell)) {

            super.move(direction);
            this.pickUpItem(nextCell.getType());
            this.removeItem(nextCell.getType());

            if (nextCell.getType().equals(CellType.TELEPORTER)) { //what happens when you step on a teleporter

                Teleporter teleporter = (Teleporter) board.getCell(this.xCoord, this.yCoord);
                //method names may be wrong
                super.setPosition(teleporter.getLinkedX(), teleporter.getLinkedY());
            } else if (nextCell.getType().equals(CellType.TOKEN_DOOR)) {

                TokenDoor tokenDoor = (TokenDoor) board.getCell(this.xCoord, this.yCoord);
                this.removeTokens(tokenDoor.getNumTokensNeeded());

            }

        }
    }

    /**
     * Works out if a move is valid.
     *
     * @return True is the move is valid, fakse otherwise.
     */
    public boolean isMoveValid(Cell cell) {
        CellType type = cell.getType();
        switch (type) {
            case WALL:
                return false;
            case WATER:
                if (this.hasItem(Item.FLIPPERS)) {
                    return true;
                } else {
                    return false;
                }
            case FIRE:
                if (this.hasItem(Item.FIREBOOTS)) {
                    return true;
                } else {
                    return false;
                }
            case RED_DOOR:
                if (this.hasItem(Item.RED_KEY)) {
                    return true;
                } else {
                    return false;
                }
            case BLUE_DOOR:
                if (this.hasItem(Item.BLUE_KEY)) {
                    return true;
                } else {
                    return false;
                }
            case GREEN_DOOR:
                if (this.hasItem(Item.GREEN_KEY)) {
                    return true;
                } else {
                    return false;
                }
            case TOKEN_DOOR:
                TokenDoor tCell = (TokenDoor) cell;
                if (tCell.getNumTokensNeeded() >= this.tokenNum) { //need token door to test
                    return true;
                } else {
                    return false;
                }
            default:
                return true;

        }
    }
    /**
     * Checks if the item exists in the player inventory.
     * @param item The item being checked.
     * @return True if the player item, false otherwise.
     */
    public boolean hasItem(Item item) {
        return this.inventory.contains(item);
    }
    
    /**
     * Gets method for the number of tokens the player has.
     * @return The number of tokens.
     */
    public int getTokenNum() {
        return this.tokenNum;
    }
    
    public ArrayList<Item> getInventory() {
    	return this.inventory;
    }

    /**
     * Adds one token to the player.
     */
    private void addToken() {
        tokenNum += 1;
    }

    /**
     * Removes the requested number of tokens.
     * @param n The number of tokens to be removed.
     */
    private void removeTokens(int n) {
        tokenNum -= n;
    }

    /**
     * Add the item to the players inventory.
     * @param type The type of the item cell being picked up.
     */
    private void pickUpItem(CellType type) {
        switch (type) {
            case FLIPPERS:
                inventory.add(Item.FLIPPERS);
                break;
            case FIREBOOTS:
                inventory.add(Item.FIREBOOTS);
                break;
            case RED_KEY:
                inventory.add(Item.RED_KEY);
                break;
            case BLUE_KEY:
                inventory.add(Item.BLUE_KEY);
                break;
            case GREEN_KEY:
                inventory.add(Item.GREEN_KEY);
                break;
            case TOKEN:
                addToken();
            default:
                break; //do nothing
        }
        //removes the item from the board
    }


    /**
     * Removes an item from the player.
     * @param type The type ofthe cell that needs the item.
     */
    private void removeItem(CellType type) {
        switch (type) {
            case RED_DOOR:
                inventory.remove(Item.RED_KEY);
                break;
            case BLUE_DOOR:
                inventory.remove(Item.BLUE_KEY);
                break;
            case GREEN_DOOR:
                inventory.remove(Item.GREEN_KEY);
                break;
            default:
                break; //do nothing
        }
    }

}
