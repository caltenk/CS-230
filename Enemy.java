
import javafx.scene.image.Image;

/**
 * A general enemy which moves towards the player
 *
 * @author Ahmed Ajaj
 * @version 1.0
 */
public class Enemy extends Moveable {

    public Enemy(int x, int y, Image image) {
        super(x, y, image);
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
        return ";" + super.toString();
        //any added enemy data here
    }

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

    public Cell getNextCell(Direction direction, Board board) {
        super.getNextCell(direction, board);
    }

    public abstract Direction calculateDirection(Board board);
}
