
/**
 * A wall following enemy: Enemy that moves in the direction of walls towards
 * the player
 *
 * @author Ahmed Ajaj
 * @version 1.0
 */
public class WallFollowingEnemy extends Enemy {

    Direction currentDirection;
    boolean turning = true;

    /**
     * The Constructor for the Wall Following Enemy.
     *
     * @param x The starting x co-ordinate.
     * @param y The starting y co-ordiante.
     * @param image The image that will represent the Enemy.
     */
    public WallFollowingEnemy(int x, int y) {
        super(x, y);
    }

    /**
     * loads a WallFollowingEnemy character from a string as generated by
     * toString(), used to load data from persistent storage.
     *
     * @param wallEnemyData the string generated by toString().
     */
    public WallFollowingEnemy(String wallEnemyData) {
        super(wallEnemyData);
    }

    /**
     * saves the WallFollowingEnemy character as a string from which a copy can
     * later be loaded using the WallFollowingEnemy(String) constructor, used to
     * save the SWallFollowingEnemy character to a text file.
     *
     * @return a string containing all information needed to load a copy of this
     * WallFollowingEnemy character.
     */
    @Override
    public String toString() {
        return EnemyType.WALL_FOLLOWING + ";" + super.toString();
    }

    /**
     * Calculates the direction the Enemy will move in.
     *
     * @param board The Board the Enemy is moving on.
     * @return The direction the enemy will move in.
     */
    public Direction calculateDirection(Board board) {
        //Gets the cells around the enemy
        boolean down = super.isMoveValid(super.getNextCell(Direction.DOWN, board));
        boolean up = super.isMoveValid(super.getNextCell(Direction.UP, board));
        boolean left = super.isMoveValid(super.getNextCell(Direction.LEFT, board));
        boolean right = super.isMoveValid(super.getNextCell(Direction.RIGHT, board));
        Direction result = Direction.DOWN;
        if (currentDirection == null) {
            currentDirection = startDirection(board);
        }

        /*
        System.out.println("Enemy:" + xCoord + ", " + yCoord + ", " + currentDirection + " :\n"
                + turning + "\n"
                + up + " : " + down + " : " + left + " : " + right + " : ");
         */
        
        if (!up || !down || !left || !right || turning) {
            switch (currentDirection) {
                case DOWN:
                    if (left) {
                        currentDirection = Direction.LEFT;
                        result = Direction.LEFT;
                    } else if (down) {
                        result = Direction.DOWN;
                    } else if (right) {
                        currentDirection = Direction.RIGHT;
                        result = Direction.RIGHT;
                    } else if (up) {
                        currentDirection = Direction.UP;
                        result = Direction.UP;
                    } else {
                        result = null;
                    }
                    break;
                case LEFT:
                    if (up) {
                        currentDirection = Direction.UP;
                        result = Direction.UP;
                    } else if (left) {
                        result = Direction.LEFT;
                    } else if (down) {
                        currentDirection = Direction.DOWN;
                        result = Direction.DOWN;
                    } else if (right) {
                        currentDirection = Direction.RIGHT;
                        result = Direction.RIGHT;
                    } else {
                        result = null;
                    }
                    break;
                case UP:
                    if (right) {
                        currentDirection = Direction.RIGHT;
                        result = Direction.RIGHT;
                    } else if (up) {
                        result = Direction.UP;
                    } else if (left) {
                        currentDirection = Direction.LEFT;
                        result = Direction.LEFT;
                    } else if (down) {
                        currentDirection = Direction.DOWN;
                        result = Direction.DOWN;
                    } else {
                        result = null;
                    }
                    break;
                case RIGHT:
                    if (down) {
                        currentDirection = Direction.DOWN;
                        result = Direction.DOWN;
                    } else if (right) {
                        result = Direction.RIGHT;
                    } else if (up) {
                        currentDirection = Direction.UP;
                        result = Direction.UP;
                    } else if (left) {
                        currentDirection = Direction.LEFT;
                        result = Direction.LEFT;
                    } else {
                        result = null;
                    }
                    break;
            }
        } else {
            result = Direction.DOWN;
        }
        if (up && down && left && right) {
            turning = false;
        } else {
            turning = true;
        }
        //System.out.println(result + "\n");
        return result;
    }

    private Direction startDirection(Board board) {
        boolean down = super.isMoveValid(super.getNextCell(Direction.DOWN, board));
        boolean up = super.isMoveValid(super.getNextCell(Direction.UP, board));
        boolean left = super.isMoveValid(super.getNextCell(Direction.LEFT, board));
        boolean right = super.isMoveValid(super.getNextCell(Direction.RIGHT, board));
        Direction result = Direction.DOWN;

        if (!left && down) {
            result = Direction.DOWN;
        } else if (!up && left) {
            result = Direction.LEFT;
        } else if (!right && up) {
            result = Direction.UP;
        } else if (!down && right) {
            result = Direction.RIGHT;
        }

        return result;
    }
}
