
/**
 * A dumb targeting enemy. Enemy that always moves in a straight line towards the player, regardless of obstacles.
 * @author Ahmed Ajaj
 * @version 1.0
 */

public class DumbTargetingEnemy extends TargetingEnemy {

    private int xDifference;
    private int yDifference;

    /**
     * This method constructs a dumb targeting enemy using the constructor in the super class
     * @param x The initial x co-ordinate.
     * @param y The initial y co-ordinate.
     * @param image The image used to present the moveable object
     * @param player Reference to the player
     */
    public DumbTargetingEnemy(int x, int y, Image image, Player player) {
        super(x, y, image, player);
    }

    /**
     * Constructs from a string of data containing all information about the
     * dumbTargetingEnemy (including superclass data).
     * @param dumbEnemyData DumbTargetingEnemy data and TargetingEnemy data.
     */
    public DumbTargetingEnemy(String dumbEnemyData) {
        super(dumbEnemyData);
    }

    /**
     * Generates a string from which a duplicate DumbTargetingEnemy can be
     * constructed, containing all information about the dumbTargetingEnemy and
     * its super classes.
     *
     * @return the information as a string.
     */
    @Override
    public String toString() {
        return EnemyType.DUMB_TARGETING + ";" + super.toString();
    }

    /**
     * This method calculates the difference between the x coordinates of the enemy and the player
     * @return The difference between the x coordinates of the enemy and the player
     */
    public int xDifference() {
        if (player.getXCoord() < enemy.getXCoord()) {
            xDifference = enemy.getXCoord() - player.getXCoord();
        } else {
            xDifference = player.getXCoord() - enemy.getXCoord();
        }
        return xDifference;
    }
    /**
     * This method calculates the difference between the y coordinates of the enemy and the player
     * @return The difference between the y coordinates of the enemy and the player
     */
    public int yDifference() {
        if (player.getYCoord() < enemy.getYCoord()) {
            yDifference = enemy.getXCoord() - player.getYCoord();
        } else {
            yDifference = player.getYCoord() - enemy.getYCoord();
        }
        return yDifference;
    }

    /**
     * This method calculates the direction the dumb targeting enemy will move in
     * regardless of the validity of the move or obstacles
     * @param board Reference to the board class
     * @return The direction the enemy will move in
     */
    public Direction calcDirection(Board board) {
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
