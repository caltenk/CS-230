import javafx.scene.image.Image;

public class WallFollowingEnemy extends Enemy {
	
	/**
	 * The Constructor for the Wall Following Enemy.
	 * @param x The starting x co-ordinate.
	 * @param y The starting y co-ordiante.
	 * @param image The image that will represent the Enemy.
	 */
	public WallFollowingEnemy(int x, int y, Image image) {
		super(x,y,image);
	}
        
        /**
         * suggested to allow fileHandling -Dan
         * @param enemyData 
         */
        public WallFollowingEnemy(String wallEnemyData){
            super(wallEnemyData);
        }
        
        /**
         * suggested to allow fileHandling -Dan
         * @param enemyData 
         */
        @Override
        public String toString(){
            return EnemyType.WALL_FOLLOWING + ";" + super.toString();
        }
	
	/**
	 * Calculates the direction the Enemy will move in.
	 * @param board The Board the Enemy is moving on.
	 * @return The direction the enemy will move in.
	 */
	public Direction calculateDirection(Board board) {
		//Gets the cells around the enemy
		boolean down = super.isMoveValid(super.getNextCell(Direction.DOWN, board));
		boolean up = super.isMoveValid(super.getNextCell(Direction.UP, board));
		boolean left = super.isMoveValid(super.getNextCell(Direction.LEFT, board));
		boolean right = super.isMoveValid(super.getNextCell(Direction.RIGHT, board));
				
		if (!down) {
			if (!left) {
				if (!up) {
					return Direction.RIGHT;
				} else {
					return Direction.UP;
				}
			} else {
				return Direction.LEFT;
			}
			
		} else if (!left) {
			if (!up) {
				if (!right) {
					return Direction.DOWN;
				} else {
					return Direction.RIGHT;
					} 
				} else {
					return Direction.UP;
				}
			} else if (!up) {
				if (!right) {
					if (!down) {
						return Direction.LEFT;
					} else {
						return Direction.DOWN;
					}
				} else {
					return Direction.RIGHT;
				}
			} else if (!right) {
				if (!down) {
					if (!left) {
						return Direction.RIGHT;
					} else {
						return Direction.LEFT;
					}
				} else {
					return Direction.DOWN;
				}
			}
				
			return null; //he is stuck and can't move
		}
}
	
