
/**
 * A smart targeting enemy. An enemy that always moves in the shortest route towards the player.
 *
 * @author Ahmed Ajaj
 * @version 1.0
 */
public class SmartTargetingEnemy extends TargetingEnemies {

    private int xDifference;
    private int yDifference;

    public SmartTargetingEnemy(int x, int y, Image image, Player player) {
        super(x, y, image, player);
    }

    /**
     * constructs from a string of data containing all information about the
     * SmartTargetingEnemy (including superclass data).
     *
     * @param smartData SmartTargetingEnemy data and TargetingEnemy data.
     */
    public SmartTargetingEnemy(String smartData) {
        super(smartData);
    }

    /**
     * generates a string from which a duplicate SmartTargetingEnemy can be 
     * constructed, containing all information about the SmartTargetingEnemy
     * and its super classes.
     *
     * @return the information as a string.
     */
    @Override
    public String toString() {
        return EnemyType.SMART_TARGETING + ";" + super.toString();
    }

    public int xDifference() {
        if (player.getXCoord() < enemy.getXCoord()) {
            xDifference = enemy.getXCoord() - player.getXCoord();
        } else {
            xDifference = player.getXCoord() - enemy.getXCoord();
        }
        return xDifference;
    }

    public int yDifference() {
        if (player.getYCoord() < enemy.getYCoord()) {
            yDifference = enemy.getXCoord() - player.getYCoord();
        } else {
            yDifference = player.getYCoord() - enemy.getYCoord();
        }
        return yDifference;
    }

    public Direction calcDirection(Board board) {
        WHILE(isMoveValid)
        {
            if (xDifference >= yDifference) {
                if (player.getXCoord() > enemy.getXCoord()) {
                    return Direction.RIGHT;
                } else if (player.getXCoord() < enemy.getXCoord()) {
                    return Direction.LEFT;
                }
            } else if (xDifference < yDifference) {
                if (player.getYCoord() > enemy.getYCoord()) {
                    return Direction.DOWN;
                } else if (player.getYCoord() < enemy.getYCoord()) {
                    return Direction.UP;
                }
            }
        }
    }
}
