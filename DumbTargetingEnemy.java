/**
 * A dumb targeting enemy. Always moves in a straight line towards the player, regardless of obstacles.
 * @author Liam Date
 * @version 1.0
 *
 */

public class DumbTargetingEnemy extends TargetingEnemy{
	private int xDifference;
	private int yDifference;

	public DumbTargetingEnemy(int x, int y, Image image, Player player) {
		super(x, y, image, player);
	}
	
	public int xDifference() {
		if (player.getXCoord() < enemy.getXCoord()) {
			xDifference = enemy.getXCoord() - player.getXCoord();
		}
		else {
			xDifference = player.getXCoord() - enemy.getXCoord();
		}
		return xDifference;	
	}
	
	public int yDifference() {
		if (player.getYCoord() < enemy.getYCoord()) {
			yDifference = enemy.getXCoord() - player.getYCoord();
		}
		else {
			yDifference = player.getYCoord() - enemy.getYCoord();
		}
		return yDifference;	
	}
	
	public Direction calcDirection (Board board) {
		if (xDifference >= yDifference) {
			if (player.getXCoord() > enemy.getXCoord()) {
				return Direction.RIGHT;
			}
			else if (player.getXCoord() < enemy.getXCoord()) {
				return Direction.LEFT;
			}
		}
		else if (xDifference < yDifference) {
			if (player.getYCoord() > enemy.getYCoord()) {
				return Direction.DOWN;
			}
			else if (player.getYCoord() < enemy.getYCoord()) {
				return Direction.UP;
			}
		}
	}
}
