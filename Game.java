import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Game {

    private GraphicsContext gc;
    private Canvas canvas;
    private Player player;
    private int cellSize = 70;
    private final int GRID_SIZE = 9;
    private Image floor;

    private Cell floorCell = new Cell(CellType.FLOOR);
    private Cell wallCell = new Cell(CellType.WALL);
    private Cell[][] board = {
            {wallCell,wallCell,wallCell,wallCell,wallCell,wallCell,wallCell,wallCell,wallCell},
            {wallCell,floorCell,floorCell,floorCell,floorCell,floorCell,floorCell,floorCell,wallCell},
            {wallCell,floorCell,floorCell,floorCell,floorCell,floorCell,floorCell,floorCell,wallCell},
            {wallCell,floorCell,floorCell,floorCell,floorCell,floorCell,floorCell,floorCell,wallCell},
            {wallCell,floorCell,floorCell,floorCell,floorCell,floorCell,floorCell,floorCell,wallCell},
            {wallCell,floorCell,floorCell,floorCell,floorCell,floorCell,floorCell,floorCell,wallCell},
            {wallCell,floorCell,floorCell,floorCell,floorCell,floorCell,floorCell,floorCell,wallCell},
            {wallCell,floorCell,floorCell,floorCell,floorCell,floorCell,floorCell,floorCell,wallCell},
            {wallCell,wallCell,wallCell,wallCell,wallCell,wallCell,wallCell,wallCell,wallCell}
    };


    public Game(Canvas canvas, Player player) {
        this.canvas = canvas;
        this.player = player;
        this.gc = canvas.getGraphicsContext2D();
        this.floor = new Image("floor.png", 70, 70, true, true);
        player.setGame(this);
    }

    public void drawGame() {
        int gridMinY = player.getPlayerY() - 3;
        int gridMaxY = player.getPlayerY() + 3;
        int gridMinX = player.getPlayerX() - 3;
        int gridMaxX = player.getPlayerX() + 3;

        int centreGrid = 3 * cellSize;
        gc.clearRect(0,0, canvas.getWidth(), canvas.getHeight());
        for(int x = 0; x <= 6; x++) {
            for(int y = 0; y <= 6; y++) {
                if (gridMinX < 0) {
                    gc.drawImage(player.getImage(), centreGrid - (cellSize * (0 - gridMinX)), 3 * cellSize);
                    gridMinX = 0;
                }
                else if(gridMaxX > GRID_SIZE - 1){
                    gc.drawImage(player.getImage(), (3 * cellSize) + (cellSize * (gridMaxX - (GRID_SIZE - 1))), 3 * cellSize);
                    gridMinX = GRID_SIZE - 7;
                }
                if(gridMinY < 0) {
                    gc.drawImage(player.getImage(), 3 * cellSize, ((3 * cellSize) - (cellSize * (0 - gridMinY))));
                    gridMinY = 0;
                }
                else if(gridMaxY > GRID_SIZE - 1) {
                    gc.drawImage(player.getImage(), (3 * cellSize), (3 * cellSize) + (cellSize * (gridMaxX - (GRID_SIZE - 1))));
                    gridMinY = GRID_SIZE - 7;
                }
                else {
                    gc.drawImage(player.getImage(),3 * cellSize, 3 * cellSize);
                }
                gc.drawImage(board[y + gridMinY][x + gridMinX].getImage(), x * cellSize, y * cellSize);

            }
        }

    }

    public Canvas getCanvas() {
        return this.canvas;
    }

    public Cell[][] getBoard() {
        return this.board;
    }
}
