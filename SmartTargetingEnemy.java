
import java.util.ArrayList;

/**
 * A smart targeting enemy. An enemy that always moves in the shortest route
 * towards the player.
 *
 * @author Ahmed Ajaj
 * @version 6.0
 */
public class SmartTargetingEnemy extends TargetingEnemy {

    private int xDifference;
    private int yDifference;

    /**
     * This method constructs a smart targeting enemy using the constructor in
     * the super class.
     *
     * @param x The initial x co-ordinate.
     * @param y The initial y co-ordinate.
     * @param player Reference to the player.
     */
    public SmartTargetingEnemy(int x, int y, Player player) {
        super(x, y, player);
    }

    /**
     * loads a smartTargetingEnemy character from a string as generated by
     * toString(), used to load data from persistent storage.
     *
     * @param smartData the string generated by toString().
     * @param player the player in the level to which this enemy is being added
     * to.
     */
    public SmartTargetingEnemy(String smartData, Player player) {
        super(smartData);
        this.player = player;
    }

    /**
     * saves the smartTargetingEnemy character as a string from which a copy can
     * later be loaded using the smartTargetingEnemy(String) constructor, used
     * to save the smartTargetingEnemy character to a text file.
     *
     * @return a string containing all information needed to load a copy of this
     * smartTargetingEnemy character.
     */
    @Override
    public String toString() {
        return EnemyType.SMART_TARGETING + ";" + super.toString();
    }

    /**
     * This method calculates the difference between the x coordinates of the
     * enemy and the player
     *
     * @return the difference between the x coordinates of the enemy and the
     * player
     */
    public int xDifference() {
        if (player.getXCoord() < getXCoord()) {
            xDifference = getXCoord() - player.getXCoord();
        } else {
            xDifference = player.getXCoord() - getXCoord();
        }
        return xDifference;
    }

    /**
     * This method calculates the difference between the y coordinates of the
     * enemy and the player
     *
     * @return the difference between the y coordinates of the enemy and the
     * player
     */
    public int yDifference() {
        if (player.getYCoord() < getYCoord()) {
            yDifference = getXCoord() - player.getYCoord();
        } else {
            yDifference = player.getYCoord() - getYCoord();
        }
        return yDifference;
    }

    /**
     * This method calculates the direction the smart targeting enemy will move
     * in by getting the shortest path to the player
     * and assigning the second node in the path as the next move to be made.
     *
     * @param board Reference to the board class.
     * @return The direction the enemy will move in.
     */
    public Direction calculateDirection(Board board) {
        ShortestPath sPath = new ShortestPath(board, getXCoord(), getYCoord(), player.getXCoord(), player.getYCoord());
        ArrayList<Node> path = sPath.findPath();
        Node nextMove;
        if (path != null) {
            nextMove = path.get(1);
        } else {
            return Direction.DOWN;
        }
        if (getXCoord() > nextMove.getX()) {
            return Direction.LEFT;
        } else if (getXCoord() < nextMove.getX()) {
            return Direction.RIGHT;
        } else if (getYCoord() > nextMove.getY()) {
            return Direction.UP;
        } else {
            return Direction.DOWN;
        }
    }
}

