import javafx.scene.image.Image;

public class TargetingEnemies extends Enemy{
	
	protected Player player;

	public TargetingEnemies(int x, int y, Image image, Player player) {
		this.player=player;
		super(x, y, image);
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
