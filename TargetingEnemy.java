
import javafx.scene.image.Image;

/**
 * a targeting enemy: Enemy that moves towards the position of the player
 * @author Ahmed Ajaj
 * @version 1.0
 */
public abstract class TargetingEnemy extends Enemy {

    protected Player player;
    /**
     * This method constructs a targeting enemy using the constructor in the super class
     * with addition of a reference to the player as a parameter
     * @param x The initial x co-ordinate.
     * @param y The initial y co-ordinate.
     * @param image The image used to present the moveable object
     * @param player Reference to the player
     */
    public TargetingEnemy(int x, int y, Player player) {
        super(x, y);
        this.player = player;
    }

    /**
     * constructs from a string of data containing all information about the
     * TargetingEnemy (including superclass data).
     *
     * @param smartData TargetingEnemy data and Enemy data.
     */
    public TargetingEnemy(String targEnemyData) {
        super(targEnemyData);
    }

    /**
     * generates a string from which a duplicate TargetingEnemy can be 
     * constructed, containing all information about the TargetingEnemy
     * and its super classes.
     *
     * @return the information as a string.
     */
    @Override
    public String toString() {
        return super.toString() + ";-";
    }
    /**
    *Gets the enemy's x coordinate 
    */
    public int getEnemyX() {
        return super.getXCoord();
    }
    /**
    *Gets the enemy's y coordinate 
    */
    public int getEnemyY() {
        return super.getYCoord();
    }
    /**
    *Gets the player's x coordinate 
    */
    public int getPlayerX() {
        return player.getXCoord();
    }
    /**
    *Gets the player's y coordinate 
    */
    public int getPlayerY() {
        return player.getYCoord();
    }
    /**
    * Gets the player
    */
    public Player getPlayer() {
        return player;
    }
}
