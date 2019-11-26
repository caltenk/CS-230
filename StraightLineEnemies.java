
public class StraightLineEnemies extends Enemy{

	private Direction direction;

	public StraightLineEnemies(int xCoordinate, int yCoordinate, int insertionTime, int height, int width, Direction direction) {
		super(xCoordinate, yCoordinate, insertionTime, height, width);
		this.direction=direction;
	}
	

}
