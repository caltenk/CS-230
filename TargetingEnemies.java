import javafx.scene.image.Image;

public abstract class TargetingEnemies extends Enemy{
	
	protected Player player;

	public TargetingEnemies(int x, int y, Image image, Player player) {
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
