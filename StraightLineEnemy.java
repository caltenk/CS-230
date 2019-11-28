
import javafx.scene.image.Image;

/**
 * a straight line enemy: Enemy that moves in a straight line towards the player
 *
 * @author Ahmed Ajaj
 * @version 1.0
 */

public class StraightLineEnemy extends Enemy {

    private Direction direction;

    public StraightLineEnemy(int xCoordinate, int yCoordinate, Direction direction, Image image) {
        super(xCoordinate, yCoordinate, image);
        this.direction = direction;
    }

    /**
     * constructs from a string of data containing all information about the
     * StraightLineEnemy (including superclass data).
     *
     * @param smartData StraightLineEnemy data and Enemy data.
     */
    public StraightLineEnemy(String straightData) {
        super(straightData);
        String straightObjData = straightData.split(";")[3];
        String[] splitData = straightObjData.split("/");
        direction = Direction.valueOf(splitData[0]);
    }

    /**
     * generates a string from which a duplicate StraightLineEnemy can be 
     * constructed, containing all information about the StraightLineEnemy
     * and its super classes.
     *
     * @return the information as a string.
     */
    @Override
    public String toString() {
        String straightData = EnemyType.STRAIGHT_LINE + ";"
                + super.toString() + ";";
        straightData += direction;
        return straightData;
    }

    public Direction calculateDirection(Board board) {
        Cell nextCell = super.getNextCell(this.direction, board);
        if (!super.isMoveValid(nextCell)) {
            switch (direction) {
                case LEFT:
                    this.direction = Direction.RIGHT;
                    break;
                case RIGHT:
                    this.direction = Direction.LEFT;
                    break;
                case UP:
                    this.direction = Direction.DOWN;
                    break;
                case DOWN:
                    this.direction = Direction.UP;
                    break;
                default: //do nothing
                    break;
            }
        }

        return this.direction;
    }
}
