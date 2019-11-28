
import javafx.scene.image.Image;

public class TargetingEnemy extends Enemy {

    protected Player player;

    public TargetingEnemy(int x, int y, Image image, Player player) {
        this.player = player;
        super(x, y, image);
    }

    /**
     * suggested to allow fileHandling -Dan note: does not set player
     *
     * @param enemyData
     */
    public TargetingEnemy(String targEnemyData) {
        super(targEnemyData);
    }

    /**
     * suggested to allow fileHandling -Dan note: does not set player
     *
     * @param enemyData
     */
    @Override
    public String toString() {
        return ";" + super.toString();
    }

    public int getEnemyX() {
        return super.getXCoord();
    }

    public int getEnemyY() {
        return super.getYCoord();
    }

    public int getPlayerX() {
        return player.super.getXCoord();
    }

    public int getPlayerY() {
        return player.super.getYCoord();
    }

    public Player getPlayer() {
        return player;
    }
}
