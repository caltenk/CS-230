import javafx.scene.image.Image;

public class Enemy extends Moveable {
	
	
	public Enemy(int x, int y, Image image) {
		super(x, y, image);
	}
        
        /**
         * suggested to allow fileHandling -Dan
         * @param enemyData 
         */
        public Enemy(String enemyData){
            super(enemyData);
        }
        
        /**
         * suggested to allow fileHandling -Dan
         * @param enemyData 
         */
        public String toString(){
            return ";" + super.toString();
            //any added enemy data here
        }
	
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
	
	public Cell getNextCell(Direction direction, Board board) {
		super.getNextCell(direction, board);
	}
	
	public abstract Direction calculateDirection(Board board) {
	
	}
	
}
