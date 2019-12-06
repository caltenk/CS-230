import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/**
 * Implemets the A* algorithm to find the shortest path
 * @author George Manning
 *
 */
public class ShortestPath {
	
	private ArrayList<Node> open;
	private ArrayList<Node> closed;
	private ArrayList<Node> path;
	private Board board;
	private Node now;
	private int xStart;
	private int yStart;
	private int xEnd;
	private int yEnd;
	
	public ShortestPath(Board board, int xStart, int yStart, int xEnd, int yEnd) {
		this.open = new ArrayList<>();
		this.closed = new ArrayList<>();
		this.path = new ArrayList<>();
		this.board = board;
		this.now = new Node(null, xStart, yStart,0,0);
		this.xStart = xStart;
		this.yStart = yStart;
		this.xEnd = xEnd;
		this.yEnd = yEnd;
	}
	
	public ArrayList<Node> findPath() {
		this.closed.add(this.now);
		addNeighboursToOpenList();
		while(this.now.getX() != this.xEnd || this.now.getY() != this.yEnd) {
			if (this.open.isEmpty()) {
				return null;
			} 
			this.now = this.open.get(0);
			this.open.remove(0);
			this.closed.add(this.now);
			addNeighboursToOpenList();
		}
		
		this.path.add(0, this.now);
		while(this.now.getX() != this.xStart || this.now.getY() != this.yStart) {
			this.now = this.now.getParent();
			this.path.add(0,this.now);
		}
		
		return this.path;
	}
	
	
    private double distance(int dx, int dy) {
            return Math.abs(this.now.getX() + dx - this.xEnd) + Math.abs(this.now.getY() + dy - this.yEnd); 
    }
    
    private static boolean findNeighborInList(List<Node> array, Node node) {
        return array.stream().anyMatch((n) -> (n.getX() == node.getX() && n.getY() == node.getY()));
    }
	
	private void addNeighboursToOpenList() {
	    Node node;
        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
            	if (x != 0 && y != 0) {
            		continue; //stops diagonals from happening
            	}
                node = new Node(this.now, this.now.getX() + x, this.now.getY() + y, this.now.getG(), this.distance(x, y));
                if ((x != 0 || y != 0) // not this.now
                    && this.now.getX() + x >= 0 && this.now.getX() + x < this.board.getSizeX() // check board boundaries
                    && this.now.getY() + y >= 0 && this.now.getY() + y < this.board.getSizeY()
                    && this.board.getCell(this.now.getX() + x, this.now.getY() + y).getType().equals(CellType.GROUND) // check if enemy can move on cell
                    && !findNeighborInList(this.open, node) && !findNeighborInList(this.closed, node)) { // if not already done
                        node.setG(node.getParent().getG() + 1 );
 
                        this.open.add(node);
                }
            }
        }
        Collections.sort(this.open);
    }
 
}
