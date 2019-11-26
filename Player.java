import javafx.scene.image.Image;
import java.util.ArrayList;

/**
 * Class represents the character the user controls.
 * @author George Manning
 * @version 1.0
 */

public class Player extends Moveable {

    private ArrayList<Item> inventory;
    private int tokenNum;

    /**
     * Comstructor for the player class.
     * @param x The initial x co-ordinate of the player.
     * @param y The initial y co-ordinate of the player.
     * @param image The image used to represent the player.
     */
    public Player(int x, int y, Image image) {
        super(x, y, image);
        this.inventory = new ArrayList<Item>();
        this.tokenNum = 0;
    }

    /**
     * Moves the player in a user specified direction
     * @param direction The direction to be moved to
     */
    public void move(Direction direction, Board board) {

        Cell nextCell = super.getNextCell(direction, board);
        if (this.isMoveValid(nextCell)) {

            super.move(direction);
            this.pickUpItem(nextCell.getType());
            this.removeItem(nextCell.getType());

            if (nextCell.getType().equals(CellType.TELEPORTER)) { //what happens when you step on a teleporter

                TeleporterCell teleporter = (TeleporterCell) board.getCell(this.xCoord, this.yCoord);
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
     * @return True is the move is valid, fakse otherwise.
     */
    public boolean isMoveValid(Cell cell) {
        CellType type = cell.getType();
        switch (type) {
            case  WALL:
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
                } else
                return false;
            default:
                return true;

        }
    }

    private void addToken() {
        tokenNum += 1;
    }

    private void removeTokens(int n) {
        tokenNum -= n;
    }

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
    }


    private boolean hasItem(Item item) {
        return this.inventory.contains(item);
    }

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


