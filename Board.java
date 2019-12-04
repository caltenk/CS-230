
import java.io.File;
import javafx.scene.image.Image;

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
        String[] cellRows = splitData[2].split("/");
        String[] tempColumn = cellRows[0].split("'");

        String[][] cells = new String[tempColumn.length][cellRows.length];

        String[] specialCell;

        goalX = Integer.parseInt(splitData[0]);
        goalY = Integer.parseInt(splitData[1]);

        for (int i = 0; i < cellRows.length; i++) {
            tempColumn = cellRows[i].split("'");
            for (int j = 0; j < tempColumn.length; j++) {
                cells[j][i] = tempColumn[j];
            }
        }

        sizeX = cellRows.length;
        sizeY = cells.length;
        board = new Cell[sizeY][sizeX];

        for (int i = 0; i < sizeY; i++) {
            for (int j = 0; j < sizeX; j++) {
                specialCell = cells[i][j].split("#");
                switch (CellType.valueOf(cells[i][j].split("#")[0])) {
                    case TOKEN_DOOR:
                        board[i][j] = new TokenDoor(Integer.parseInt(specialCell[1]));
                        break;
                    case TELEPORTER:
                        board[i][j] = new Teleporter(Integer.parseInt(specialCell[1]),
                                Integer.parseInt(specialCell[2]));
                        break;
                    default:
                        board[i][j] = new Cell(cells[i][j]);
                }
            }
        }
    }

    /**
     * returns a Cell array with each edge Cell being a wall and all others
     * being ground cells.
     *
     * @param width width of the board.
     * @param height height of the board.
     * @return
     */
    public static Cell[][] blankBoard(int width, int height) {
        Cell[][] cells = new Cell[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (i == 0 || i == width - 1 || j == 0 || j == height - 1) {
                    cells[i][j] = new Cell(CellType.WALL);
                } else {
                    cells[i][j] = new Cell(CellType.GROUND);
                }
            }
        }
        return cells;
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
        for (int i = 0; i < sizeY; i++) {
            for (int j = 0; j < sizeX; j++) {
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

                if (j < sizeX - 1) {
                    boardData += "'";
                }
            }
            if (i < sizeY - 1) {
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
    public void updateCell(int x, int y, String theme) {
        board[y][x] = new Cell(CellType.GROUND);
        getCell(x,y).setImage(new Image(theme + "\\" + CellType.GROUND + ".png"));
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

    void setTheme(String themeLocation) {

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                try {
                    switch (board[i][j].getType()) {
                        case WALL:
                            board[i][j].setImage(new Image(themeLocation + "\\" + CellType.WALL + ".png"));
                            break;
                        case GROUND:
                            board[i][j].setImage(new Image(themeLocation + "\\" + CellType.GROUND + ".png"));
                            break;
                        case RED_DOOR:
                            board[i][j].setImage(new Image(themeLocation + "\\" + CellType.RED_DOOR + ".png"));
                            break;
                        case RED_KEY:
                            board[i][j].setImage(new Image(themeLocation + "\\" + CellType.RED_KEY + ".png"));
                            break;
                        case TELEPORTER:
                            board[i][j].setImage(new Image(themeLocation + "\\" + CellType.TELEPORTER + ".png"));
                            break;
                        case GOAL:
                            board[i][j].setImage(new Image(themeLocation + "\\" + CellType.GOAL + ".png"));
                            break;
                        case TOKEN:
                            board[i][j].setImage(new Image(themeLocation + "\\" + CellType.TOKEN + ".png"));
                            break;
                        default:
                            System.out.println("ERROR - cell type not recognised");
                            break;
                    }

                } catch (Exception e) {
                    System.out.println("ERROR - cell image not found");
                }
            }
        }
    }
}
