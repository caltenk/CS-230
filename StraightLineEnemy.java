
import com.sun.javafx.scene.traversal.Direction;
import javafx.scene.image.Image;

public class StraightLineEnemy extends Enemy {

    private Direction direction;

    public StraightLineEnemy(int xCoordinate, int yCoordinate, Direction direction, Image image) {
        super(xCoordinate, yCoordinate, image);
        this.direction = direction;
    }

    /**
     * suggested to allow fileHandling -Dan
     *
     * @param enemyData
     */
    public StraightLineEnemy(String straightData) {
        super(straightData);
        String straightObjData = straightData.split(";")[3];
        String[] splitData = straightObjData.split("/");
        direction = Direction.valueOf(splitData[0]);
    }

    /**
     * suggested to allow fileHandling -Dan
     *
     * @param enemyData
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
