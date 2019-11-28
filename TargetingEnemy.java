import javafx.scene.image.Image;
/**a targeting enemy: Enemy that moves towards the position of the player
 * @author Ahmed Ajaj
 * @version 1.0
 */
public abstract class TargetingEnemy extends Enemy{
	
	protected Player player;

	public TargetingEnemy(int x, int y, Image image, Player player) {
		super(x, y, image);
		this.player = player;
	}	
		
	public int getEnemyX() {
		return super.getXCoord();
	}
	public int getEnemyY() {
		return super.getYCoord();
	}
	public int getPlayerX() {
		return player.getXCoord();
	}
		
	public int getPlayerY() {
		return player.getYCoord();
	}

	public Player getPlayer() {
		return player;
	}

}
