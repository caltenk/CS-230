import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Implemets the A* algorithm to find the shortest path between two points.
 *
 * @author George Manning
 * @version 3.0
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

    /**
     * The constructor for the search.
     *
     * @param board  The board the algorithm runs on.
     * @param xStart The x coord of the start cell.
     * @param yStart The y coord of the start cell.
     * @param xEnd   The x coord of the end cell.
     * @param yEnd   The y coord of the end cell.
     */
    public ShortestPath(Board board, int xStart, int yStart, int xEnd, int yEnd) {
        this.open = new ArrayList<>();
        this.closed = new ArrayList<>();
        this.path = new ArrayList<>();
        this.board = board;
        this.now = new Node(null, xStart, yStart, 0, 0);
        this.xStart = xStart;
        this.yStart = yStart;
        this.xEnd = xEnd;
        this.yEnd = yEnd;
    }

    /**
     * Find the shortest path between the two specified points.
     *
     * @return An array list containing the shortest path.
     */
    public ArrayList<Node> findPath() {
        this.closed.add(this.now);
        addNeighboursToOpenList();


        while (this.now.getX() != this.xEnd || this.now.getY() != this.yEnd) {
            if (this.open.isEmpty()) {
                return null; //cant move
            }
            this.now = this.open.get(0); //get the first value in the open list
            this.open.remove(0);
            this.closed.add(this.now);
            addNeighboursToOpenList();
        }

        this.path.add(0, this.now);
        while (this.now.getX() != this.xStart || this.now.getY() != this.yStart) {
            this.now = this.now.getParent();
            this.path.add(0, this.now);
        }

        return this.path;
    }

    /**
     * Calculates a estimate of the distance from the start node.
     *
     * @param dx The possible distance in the x direction from the node before.
     * @param dy The possible distance in the y direction from the node before.
     * @return An estimate of the cost to this node, from the start node.
     */
    private double distance(int dx, int dy) {
        return Math.abs(this.now.getX() + dx - this.xEnd) + Math.abs(this.now.getY() + dy - this.yEnd);
    }

    /**
     * Works out if a node is in a list.
     *
     * @param array The list of nodes being checked.
     * @param node  The node being searched for in array.
     * @return True if node in array, false otherwise.
     */
    private static boolean findNeighborInList(List<Node> array, Node node) {
        return array.stream().anyMatch((n) -> (n.getX() == node.getX() && n.getY() == node.getY()));
    }

    /**
     * Add all valid neighbours of the current node (now) to the open list.
     */
    private void addNeighboursToOpenList() {
        Node node;
        //checks all nodes around the current node.
        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                if (x != 0 && y != 0) {
                    continue; //stops diagonals from happening
                }
                //create a new node to possibly add to the open list
                node = new Node(this.now, this.now.getX() + x, this.now.getY() + y, this.now.getG(), this.distance(x, y));
                if ((x != 0 || y != 0) //if x and y are equal to 0 then its the now node
                        && this.now.getX() + x >= 0 && this.now.getX() + x < this.board.getSizeX()
                        && this.now.getY() + y >= 0 && this.now.getY() + y < this.board.getSizeY() // Check values in board boundries
                        && this.board.getCell(this.now.getX() + x, this.now.getY() + y).getType().equals(CellType.GROUND) // check if enemy can move on cell
                        && !findNeighborInList(this.open, node) && !findNeighborInList(this.closed, node)) { //makes sure the node isnt already in the closed or open list.

                    node.setG(node.getParent().getG() + 1);//sets G to the correct distance
                    this.open.add(node); //add node to the open list
                }
            }
        }
        Collections.sort(this.open);
    }

}
