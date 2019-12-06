
public class Node implements Comparable<Node>{
	
	private Node parent;
	private int x;
	private int y;
	private double g;
	private double h;
	
	Node(Node parent, int x, int y, double g, double h) {
		this.setParent(parent);
		this.setX(x);
		this.setY(y);
		this.g = g;
		this.h = h;
	}
	
	public int compareTo(Node node) {
		return (int) ((this.g + this.h) - (node.g + node.h));
	}

	public Node getParent() {
		return parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public void setG(Double g) {
		this.g = g;
	}
	public double getG() {
		return this.g;
	}
	
	public double getH() {
		return this.h;
	}
}
