
public class StraightLineEnemies extends Enemy{

	private String direction;

	public StraightLineEnemies(int xCoordinate, int yCoordinate, int insertionTime, int height, int width, String direction) {
		super(xCoordinate, yCoordinate, insertionTime, height, width);
		this.direction=direction;
	}
	

}
