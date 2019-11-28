
/**
 * A class that describes the board for each level
 *
 * @author George Manning
 * @version 1.0
 */
public class Board {

    private Cell[][] board;
    private int sizeY;
    private int sizeX;

    //location of the goal
    private int goalX;
    private int goalY;

    /**
     * The constructor for a board object.
     *
     * @param board A 2d array of GameCells that make up the board.
     * @param x The number of columns the board has.
     * @param y The number of rows the board has.
     * @param gx The x-coordinate of the goal.
     * @param gy The y-coordinate of the goal.
     */
    public Board(Cell[][] board, int x, int y, int gx, int gy) {
        this.board = board;
        this.sizeX = x;
        this.sizeY = y;
        this.goalX = gx;
        this.goalY = gy;
    }

    /**
     * suggested to allow filehandling -Dan. note: not yet compiled with Cell,
     * not tested.
     *
     * @param boardData
     */
    public Board(String boardData) {
        String[] splitData = boardData.split(";");
        String[] cellColumns = splitData[2].split("/");
        String[][] cells = null;

        for (int i = 0; i < cellColumns.length; i++) {
            cells[i] = cellColumns[i].split("|");
        }

        sizeX = cellColumns.length;
        sizeY = cells[0].length;

        for (int i = 0; i < sizeX; i++) {
            for (int j = 0; j < sizeY; j++) {
                //add checks and additional code here for special cells
                board[i][j] = new Cell(cells[i][j]);
            }
        }
    }

    /**
     * suggested to allow filehandling -Dan. note: not yet compiled with Cell,
     * not tested.
     *
     * @return
     */
    @Override
    public String toString() {
        String boardData = goalX + ";" + goalY + ";";
        for (int i = 0; i < sizeX; i++) {
            for (int j = 0; j < sizeY; j++) {
                switch (board[i][j].getType()) {
                    case TOKEN_DOOR:
                        boardData += ((TokenDoor) board[i][j]).toString();
                        break;
                    case TELEPORTER:
                        boardData += ((Teleporter) board[i][j]).toString();
                        break;
                    default:
                        boardData += board[i][j].toString();
                }
                
                if (j < sizeY - 1) {
                    boardData += "|";
                }
            }
            if (i < sizeX - 1) {
                boardData += "/";
            }
        }
        return boardData;
    }

    /**
     * Updated the selected GameCell into a ground GameCell.
     *
     * @param x The x co-ordinate of the GameCell being updated.
     * @param y The y co-ordinated of the GameCell being updated.
     */
    public void updateCell(int x, int y) {
        board[y][x] = new Cell(CellType.GROUND);
    }

    /**
     * Gets a GameCell from the board at a given location.
     *
     * @param x The x co-ordinate of the requested GameCell.
     * @param y The y co-ordinate of the requested GameCell.
     * @return The GameCell at the given co-ordinate
     */
    public Cell getCell(int x, int y) {
        return this.board[y][x];
    }

    /**
     * Get method for the board attribute of the Board class.
     *
     * @return The board attribute.
     */
    public Cell[][] getBoard() {
        return this.board;
    }

    /**
     * Get method for the number of rows the board has.
     *
     * @return The number of rows the board has.
     */
    public int getSizeY() {
        return this.sizeY;
    }

    /**
     * Get method for the number or columns the board has.
     *
     * @return The number of columns the board has.
     */
    public int getSizeX() {
        return this.sizeX;
    }

    /**
     * Get method for the x co-ordinate of the goal.
     *
     * @return The x co-ordinate of the goal.
     */
    public int getGoalX() {
        return this.goalX;
    }

    /**
     * Get method for the y co-ordinate of the goal/
     *
     * @return The y co-ordinate of the goal.
     */
    public int getGoalY() {
        return this.goalY;
    }

}
