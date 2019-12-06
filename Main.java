import java.util.ArrayList;
public class Main {
	public static void main(String[] args) {
		Cell[][] grid = Board.blankBoard(10, 10);
		grid[5][3] = new Cell(CellType.WALL);
		grid[5][4] = new Cell(CellType.WALL);
		
		Player player = new Player(3,4);
		Enemy[] enemy = new Enemy[1];
		enemy[0] = new SmartTargetingEnemy(7,3,player);
        Board board = new Board(grid, 10, 10, 9, 3);
        
		ShortestPath path = new ShortestPath(board, 3,7,3,3);
		ArrayList<Node> pathPath = path.findPath();
		
		if (path != null) {
            pathPath.forEach((n) -> {
                System.out.print("[" + n.getX() + ", " + n.getY() + "] ");
            });
            System.out.printf("\nTotal cost: %.02f\n", pathPath.get(pathPath.size() - 1).getG());
		}
	}
}
