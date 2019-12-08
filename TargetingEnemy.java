/**
 * A targeting enemy: Enemy that moves towards the position of the player.
 * @author Ahmed Ajaj
 * @version 1.0
 */
public abstract class TargetingEnemy extends Enemy {

    protected Player player;
    /**
     * This method constructs a targeting enemy
     * using the constructor in the super class,
     * with addition of a reference to the player as a parameter.
     *
     * @param x The initial x co-ordinate.
     * @param y The initial y co-ordinate.
     * @param player Reference to the player.
     */
    public TargetingEnemy(int x, int y, Player player) {
        super(x, y);
        this.player = player;
    }

    /**
     * loads a targetingEnemy character
     * from a string as generated by toString(),
     * used to load data from persistent storage.
     *
     * @param targEnemyData the string generated by toString().
     */
    public TargetingEnemy(String targEnemyData) {
        super(targEnemyData);
    }

    /**
     * Saves the targetingEnemy character as a string
     * from which a copy can later be loaded using
     * the targetingEnemy(String) constructor,
     * used to save the targetingEnemy character to a text file.
     *
     * @return A string to load a copy of this targetingEnemy character.
     */
    @Override
    public String toString() {
        return super.toString() + ";-";
    }
    /**
     * Gets the enemy's x coordinate.
     * @return The enemy's x coordinate.
     */
    public int getEnemyX() {
        return super.getXCoord();
    }
    /**
     * Gets the enemy's y coordinate.
     * @return The enemy's y coordinate.
    */
    public int getEnemyY() {
        return super.getYCoord();
    }
    /**
     * Gets the player's x coordinate.
     * @return The player's x coordinate.
    */
    public int getPlayerX() {
        return player.getXCoord();
    }
    /**
     * Gets the player's y coordinate.
     * @return The player's y coordinate.
    */
    public int getPlayerY() {
        return player.getYCoord();
    }
    /**
     * Gets the player.
     * @return Reference to the player.
    */
    public Player getPlayer() {
        return player;
    }
}
