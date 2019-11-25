import javafx.scene.image.Image;

/**
 * Class represents a character which can move in the game. 
 * @author George Manning
 * @version 1.0
 */

public abstract class Moveable {
	
	protected int xCoord;
	protected int yCoord;
	protected Board board;
	protected Image image; //Hold the sprite of the character.
	
	/**
	 * Constructor for the Moveable class.
	 * @param x The inital x co-ordinate.
	 * @param y The initial y co-ordinate.
	 * @param image The image used to present the moveable object
	 */
	public Moveable(int x, int y, Image image) {
		setPosition(x,y);
		this.image = image;
	}
	
	public void move(Direction direction) {
		switch (direction) {
			case UP:
				this.yCoord -= 1;
				break;
			case DOWN:
				this.yCoord += 1;
				break;
			case LEFT:
				this.xCoord -= 1;
				break;
			case RIGHT:
				this.xCoord += 1;
				break;
			default: 
				break; //do nothing
		}
	}
	
	/**
	 * Sets the position to a given location.
	 * @param x The x co-ordinate.
	 * @param y The y co-ordinate.
	 */
	public void setPosition(int x, int y) {
		this.xCoord = x;
		this.yCoord = y;
	}
	public int getXCoord() {
		return this.xCoord;
	}
	
	public int getYCoord() {
		return this.yCoord;
	}
	
	public Image getImage() {
		return this.image;
	}
	
	//THIS NEEDS BOARD AND CELL CLASSES TO BE TESTED.
	/**
	 * Gets the type of the cell that the moveable object wants to move to.
	 * @param direction The direction the the cell whose type is being found.
	 * @param board The board the moveable object is moving on.
	 * @return The cell type of the requested cell.
	 */
	protected Cell getNextCell(Direction direction, Board board) {
		
		int x = this.xCoord;
		int y = this.yCoord;
		
		switch (direction) {
			case UP:
				y -= 1;
				break;
			case DOWN:
				y += 1;
				break;
			case LEFT:
				x -= 1;
				break;
			case RIGHT:
				x += 1;
				break;
			default: 
				break; //do nothing
		}
		return board.getCell(x,y);
	}
	
	/**
	 * Works out if a move is valid
	 * @return True is the move is valid, False otherwise
	 */
	protected abstract boolean isMoveValid(Cell cell) {
		
	}

}