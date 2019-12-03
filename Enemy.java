
import javafx.scene.image.Image;

/**
 * A general enemy which moves towards the player
 * @author Ahmed Ajaj
 * @version 1.0
 */
public abstract class Enemy extends Moveable {
    /** This method constructs an enemy using the constructor in the superclass
     * @param x The initial x co-ordinate.
     * @param y The initial y co-ordinate.
     * @param image The image used to present the moveable object
     */
    public Enemy(int x, int y) {
        super(x, y);
    }

    /**
     * constructs from a string of data containing all information about the
     * Enemy (including superclass data).
     *
     * @param enemyData Enemy data and Moveable data.
     */
    public Enemy(String enemyData) {
        super(enemyData);
    }

    /**
     * generates a string from which a duplicate Enemy can be constructed, 
     * containing all information about the Enemy and its super classes.
     *
     * @return the information as a string.
     */
    public String toString() {
        return super.toString() + ";-";
        //any added enemy data here
    }

    /**
     * This emthod computes whether the move the enemy wishes to make is valid
     * @param cell Reference to the cell on the board the enemy is trying to move to
     * @return Boolean declaring if the move is valid or not
     */
    public boolean isMoveValid(Cell cell) {
        CellType type = cell.getType();
        switch (type) {
            case WALL:
                return false;
            case WATER:
                return false;
            case FIRE:
                return false;
            case RED_DOOR:
                return false;
            case BLUE_DOOR:
                return false;
            case GREEN_DOOR:
                return false;
            case TOKEN_DOOR:
                return false;
            default:
                return true;

        }
    }

    /**
     * This method computes the next cell
     * @param direction The direction the enemy wishes to move in
     * @param board The board the enemy is currently on
     * @return The next cell
     */
    public Cell getNextCell(Direction direction, Board board) {
        return super.getNextCell(direction, board);
    }

    /**
     * Abstract method to calculate the direction the enemy will move in
     * @param board The board the enemy is on
     */
    public abstract Direction calculateDirection(Board board);
}
