
public class Enemy {
	//add isMoveValid method with parameters of board and direction 
	private int xCoordinate;
	private int yCoordinate;
	private int insertionTime;
	private int height;
	private int width;
	private String enemyType;
	private String Direction;
	private int playerXCoordinate;
	private int playerYCoordinate;
	
	public boolean isMoveValid(String board, String direction) {
		return true;
	}
	public int getxCoordinate() {
		return xCoordinate;
	}
	public void setxCoordinate(int xCoordinate) {
		this.xCoordinate = xCoordinate;
	}
	public int getyCoordinate() {
		return yCoordinate;
	}
	public void setyCoordinate(int yCoordinate) {
		this.yCoordinate = yCoordinate;
	}
	public int getInsertionTime() {
		return insertionTime;
	}
	public void setInsertionTime(int insertionTime) {
		this.insertionTime = insertionTime;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public String getEnemyType() {
		return enemyType;
	}
	public void setEnemyType(String enemyType) {
		this.enemyType = enemyType;
	}
	public String getDirection() {
		return Direction;
	}
	public void setDirection(String direction) {
		Direction = direction;
	}
	public int getPlayerXCoordinate() {
		return playerXCoordinate;
	}
	public int getPlayerYCoordinate() {
		return playerYCoordinate;
	}
}
