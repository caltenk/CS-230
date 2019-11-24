

/**
 * A class that describes the board for each level
 * @author George Manning
 * @version 1.0
 */
public class Board {
	
	private Cell[][] board;
	private int sizeY;
	private int sizeX;
	
	public Cell[][] getBoard() {
		return this.board;
	}
	
	public int getSizeY() {
		return this.sizeY;
	}
	
	public int getSizeX() {
		return this.sizeX;
	}
	/**
	 * The constructor for a board object.
	 * @param board A 2d array of GameCells that make up the board.
	 * @param x The number of columns the board has.
	 * @param y The number of rows the board has.
	 */
	public Board(Cell[][] board, int x, int y) {
		this.board = board;
		this.sizeX = x;
		this.sizeY = y;
	}
	
	/**
	 * Updated the selected GameCell into a ground GameCell.
	 * @param x The x co-ordinate of the GameCell being updated.
	 * @param y The y co-ordinated of the GameCell being updated.
	 */
	public void updateCell(int x, int y) {
		board[y][x] = new Cell(CellType.GROUND);
	}
	
	/**
	 * Gets a GameCell from the board at a given location.
	 * @param x The x co-ordinate of the requested GameCell.
	 * @param y The y co-ordinate of the requested GameCell.
	 * @return The GameCell at the given co-ordinate
	 */
	public Cell getCell(int x, int y) {
		return this.board[y][x];
	}
}
