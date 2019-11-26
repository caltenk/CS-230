import javafx.scene.image.Image;

public class Enemy extends Moveable {
	
	
	public Enemy(int x, int y, Image image) {
		super(x, y, image);
	}

	//add isMoveValid method with parameters of board and direction
	
	
	public boolean isMoveValid(Cell cell) {
		CellType type = cell.getType();
		switch (type) {
			case  WALL:
				return false;
			case WATER:
					return false;
			case FIRE:
					return false;
			case RED_DOOR:
					return false;
			case BLUE_DOOR:
					return false;
			case GREEN_DOOR:
					return false;
			case TOKEN_DOOR:
					return false;
			default: 
				return true;
		
		}
	}
	
}
