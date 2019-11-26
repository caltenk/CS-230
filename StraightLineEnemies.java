
public class StraightLineEnemies extends Enemy{

	private Direction direction;

	public StraightLineEnemies(int xCoordinate, int yCoordinate, int insertionTime, int height, int width, Direction direction) {
		super(xCoordinate, yCoordinate, insertionTime, height, width);
		this.direction=direction;
	}
	
	public Direction calculateDirection(Board board) {
		Cell nextCell = super.super.getNextCell(this.direction, board);
		if (!super.isMoveValid(nextCell) {
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
		return this.direction;
}
