import javafx.scene.image.Image;
/**a straight line enemy: Enemy that moves in a straight line towards the player
 * @author Ahmed Ajaj
 * @version 1.0
 */

public class StraightLineEnemy extends Enemy{

	private Direction direction;

	public StraightLineEnemy(int xCoordinate, int yCoordinate, Direction direction, Image image) {
		super(xCoordinate, yCoordinate, image);
		this.direction=direction;
	}
	
	public Direction calculateDirection(Board board) {
		Cell nextCell = super.getNextCell(this.direction, board);
		if (!super.isMoveValid(nextCell)) {
			switch (direction) {
				case LEFT:
					this.direction = Direction.RIGHT;
					break;
				case RIGHT:
					this.direction = Direction.LEFT;
					break;
				case UP:
					this.direction = Direction.DOWN;
					break;
				case DOWN:
					this.direction = Direction.UP;
					break;
				default: //do nothing
					break;
			}
		}
		
		return this.direction;
	}
}
