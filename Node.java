/**
 * A class for a very simple node. Used for a simple implemtation
 * of the A* algorithm.
 * @author George Manning
 * @version 2.0
 */
public class Node implements Comparable<Node>{
	
	private Node parent; //parent node
	private int x; //x co-ordinate
	private int y; //y co-ordinate
	private double g; //cost from start node
	private double h; //heuristic version of g
	
	/**
	 * Node constructor.
	 * @param parent The parent node.
	 * @param x The x co-ordinate of the node.
	 * @param y The y co-ordinate of the node.
	 * @param g	The cost to get to this node from the start node.
	 * @param h A heuristic estimate of the cost. 
	 */
	Node(Node parent, int x, int y, double g, double h) {
		this.parent = parent;
		this.x = x;
		this.y = y;
		this.g = g;
		this.h = h;
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Node node) {
		return (int) ((this.g + this.h) - (node.g + node.h));
	}
	
	/**
	 * Get method for the parent attribute.
	 * @return The parent node.
	 */
	public Node getParent() {
		return parent;
	}
	
	/**
	 * Set the x-coordinate of the node.
	 * @param the x co-ord the node is being set to.
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * Get the x co-ordinate of the node.
	 * @return The x co-ordinate.
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * Get the y-coordinate of the node.
	 * @return The y coordinate of the node.
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * Set the y coordinate of the node.
	 * @param y The y coordinate the node is being set to.
	 */
	public void setY(int y) {
		this.y = y;
	}
	
	/**
	 * Set the value of g (the cost to get too this node from the start node).
	 * @param g The cost from the start node to this node.
	 */
	public void setG(Double g) {
		this.g = g;
	}
	
	/**
	 * Get the value of g (the cost from the start node to this node).
	 * @return The cost to get to this node from the start node.
	 */
	public double getG() {
		return this.g;
	}
	
	/**
	 * Get the value if the h attribute, (an estimate of the cost).
	 * @return The estimate of the cost.
	 */
	public double getH() {
		return this.h;
	}
}
